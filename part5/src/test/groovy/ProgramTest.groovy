import java.io.*
import org.junit.*
import groovy.mock.interceptor.*

class ProgramTest {

  @After
  public void after() {
    Program.metaClass = null
    System.metaClass = null
    System.out.metaClass = null
  }
  
  @Test
  public void rock_should_beat_scissors() {
    runSimulation("r","s").with { output ->
      assert output[0] == 'Player chose r and Computer chose s'
      assert output[1] == 'Player wins.'
      assert output[2] == 'Score is Player: 1, Computer: 0'
      assert output[3] == 'Good Bye'
    }
  }

  @Test
  public void scissors_should_beat_paper() {
    runSimulation("s", "p").with { output ->
      assert output[0] == 'Player chose s and Computer chose p'
      assert output[1] == 'Player wins.'
      assert output[2] == 'Score is Player: 1, Computer: 0'
      assert output[3] == 'Good Bye'
    }
  }

  @Test
  public void paper_should_beat_rock() {
    runSimulation("p", "r").with { output ->
      assert output[0] == 'Player chose p and Computer chose r'
      assert output[1] == 'Player wins.'
      assert output[2] == 'Score is Player: 1, Computer: 0'
      assert output[3] == 'Good Bye'
    }
  }

  @Test
  public void same_should_tie_same() {
    runSimulation("p", "p").with { output -> assert output[1] == 'The result is a tie.' }
    runSimulation("r", "r").with { output -> assert output[1] == 'The result is a tie.' }
    runSimulation("s", "s").with { output -> assert output[1] == 'The result is a tie.' }
  }

  @Test
  public void should_keep_score() {
    runSimulation("p", "p") // Tie
    runSimulation("r", "s") // Win
    runSimulation("s", "r") // Loss
  }

  private List<String> runSimulation(String playerOption, String computerOption) {

    // Mock the interaction with the user
    MockFor consoleMock = new MockFor(Console)
    consoleMock.demand.readLine { String s -> "yes" }           // Do you want to play?
    consoleMock.demand.readLine { String s -> playerOption }    // Make your choice
    consoleMock.demand.readLine { String s -> "no" }            // Play again?
    def console = consoleMock.proxyInstance()

    // Intercept the call to console() and return our mocked console
    System.metaClass.'static'.console = { console }
    
    // Mock the OptionFactory to return what we want:
    OptionFactory.metaClass.random = { String p -> new OptionFactory().make(computerOption, p) }

    // Intercept the call to println() so that we can inspect the message returned
    def output = []
    
    System.out.metaClass.invokeMethod = { String methodName, args -> 
      if (methodName == 'println') output << args[0]
    }

    // Run program
    Program.main([] as String[])

    return output
  }

  @Test
  public void should_read_console_input() {
    System.metaClass.'static'.console = { -> [ readLine: { String s -> "user input" } ] }
    assert Program.prompt("Would you like to play?") == 'user input'
  }

  @Test
  public void should_format_prompt_message() {
    assert Program.formatPrompt("hello") == "hello: "
  }

  @Test
  public void should_show_available_options() {
    assert Program.availableOptions() == "Options: r=Rock, p=Paper, s=Scissors"
  }
}