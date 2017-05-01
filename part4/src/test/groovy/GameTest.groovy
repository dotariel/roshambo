import org.junit.*
import groovy.mock.interceptor.*

class GameTest {

  @Test
  public void should_compute_score() {
    def game = new Game()

    game.addRound(new Round(new Rock("You"), new Scissors("Computer")))
    assert game.getScore("You") == 1
    assert game.getScore("Computer") == 0

    game.addRound(new Round(new Paper("You"), new Scissors("Computer")))
    assert game.getScore("You") == 1
    assert game.getScore("Computer") == 1

    game.addRound(new Round(new Paper("You"), new Paper("Computer")))
    assert game.getScore("You") == 1
    assert game.getScore("Computer") == 1
  }

  @Test
  public void should_provide_summary() {
    def game = new Game()

    game.addRound(new Round(new Rock("You"), new Scissors("Computer")))

    assert game.summary == 'Score is You: 1, Computer: 0'

  }
}