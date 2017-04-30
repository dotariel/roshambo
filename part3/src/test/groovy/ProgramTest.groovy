import java.io.*
import org.junit.*
import groovy.mock.interceptor.*

class ProgramTest {

  @Before
  public void before() {
    Program.humanScore = 0
    Program.compScore = 0
  }

  @After
  public void after() {
    Program.metaClass = null
    System.metaClass = null
    System.out.metaClass = null
  }
  
  @Test
  public void rock_should_beat_scissors() {
    runSimulation("r","s").with { output ->
      assert output[0] == 'You chose r and the computer chose s'
      assert output[1] == 'You win'
      assert output[2] == 'Score is You = 1 and the Machine = 0'
      assert output[3] == 'Keep beating the machine'
      assert output[4] == 'Good Bye'
    }

    reset()
  }

  @Test
  public void scissors_should_beat_paper() {
    runSimulation("s", "p").with { output ->
      assert output[0] == 'You chose s and the computer chose p'
      assert output[1] == 'You win'
      assert output[2] == 'Score is You = 1 and the Machine = 0'
      assert output[3] == 'Keep beating the machine'
      assert output[4] == 'Good Bye'
    }

    reset()
  }

  @Test
  public void paper_should_beat_rock() {
    runSimulation("p", "r").with { output ->
      assert output[0] == 'You chose p and the computer chose r'
      assert output[1] == 'You win'
      assert output[2] == 'Score is You = 1 and the Machine = 0'
      assert output[3] == 'Keep beating the machine'
      assert output[4] == 'Good Bye'
    }

    reset()
  }

  @Test
  public void same_should_tie_same() {
    runSimulation("p", "p").with { output -> assert output[1] == 'The result is a tie.' }
    runSimulation("r", "r").with { output -> assert output[1] == 'The result is a tie.' }
    runSimulation("s", "s").with { output -> assert output[1] == 'The result is a tie.' }

    reset()
  }

  @Test
  public void should_keep_score() {
    runSimulation("p", "p") // Tie
    runSimulation("r", "s") // Win
    runSimulation("s", "r") // Loss

    assert Program.humanScore == 1
    assert Program.compScore == 1

    reset()
  }

  private void reset() {
    Program.humanScore = 0
    Program.compScore = 0
  }

  private List<String> runSimulation(String playerChoice, String computerChoice) {

    // Mock the interaction with the user
    MockFor consoleMock = new MockFor(Console)
    consoleMock.demand.readLine { String s -> "yes" }           // Do you want to play?
    consoleMock.demand.readLine { String s -> playerChoice }    // Make your choice
    consoleMock.demand.readLine { String s -> "no" }            // Play again?
    def console = consoleMock.proxyInstance()

    // Intercept the call to console() and return our mocked console
    System.metaClass.'static'.console = { console }
    
    // Intercept the call to computerChoice() so that we can return the response we want to verify
    Program.metaClass.'static'.computerChoice = { return computerChoice } 

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
  public void should_format_prompt_message() {
    assert Program.formatPrompt("hello") == "hello: "
  }

  @Test
  public void should_read_console_input() {
    System.metaClass.'static'.console = { -> [ readLine: { String s -> "user input" } ] }
    assert Program.prompt("Would you like to play?") == 'user input'
  }

  @Test
  public void should_build_score_message() {
    assert Program.getScoreMessage(2,3) == "Score is You = 2 and the Machine = 3"
    assert Program.getScoreMessage(0,0) == "Score is You = 0 and the Machine = 0"
  }
}