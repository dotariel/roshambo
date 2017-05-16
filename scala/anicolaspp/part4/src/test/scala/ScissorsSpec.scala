import GameOption.{Paper, Rock, Scissors}
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by nperez on 5/13/17.
  */
class ScissorsSpec extends FlatSpec with Matchers {
  it should "tie self" in {
    Scissors("") beats Scissors("") should be (0)
  }

  it should "bet paper" in {
    Scissors("") beats Paper("") should be (1)
  }

  it should "not beat rock" in {
    Scissors("") beats Rock("") should be (-1)
  }
}
