import GameOption.{Paper, Rock, Scissors}
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by nperez on 5/13/17.
  */
class PaperSpec extends FlatSpec with Matchers {
  it should "tie self" in {
    Paper("").beats(Paper("")) should be (0)
  }

  it should "beat rock" in {
    Paper("").beats(Rock("")) should be (1)
  }

  it should "not beat scissors" in {
    Paper("").beats(Scissors("")) should be (-1)
  }
}
