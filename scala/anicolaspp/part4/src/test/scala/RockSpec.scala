import GameOption.{Paper, Rock, Scissors}
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by nperez on 5/13/17.
  */
class RockSpec extends FlatSpec with Matchers {
  it should "tie self" in {
    Rock("").beats(Rock("")) should be (0)
  }

  it should "beat scissors" in {
    Rock("").beats(Scissors("")) should be (1)
  }

  it should "not beat paper" in {
    Rock("") beats Paper("") should be (-1)
  }
}
