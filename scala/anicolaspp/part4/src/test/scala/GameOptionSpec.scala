import GameOption.{Paper, Rock, Scissors}
import org.scalatest.{FlatSpec, Matchers}

class GameOptionSpec extends FlatSpec with Matchers with GameMatchers {

  it should "make Option from string" in {
    GameOption("r", "fred") should unwrapTo (Rock("fred"))
    GameOption("p", "fred") should unwrapTo (Paper("fred"))
    GameOption("s", "fred") should unwrapTo (Scissors("fred"))
  }

  it should "return Left" in {
    GameOption("x", "") should be (Invalid) // (Left(InvalidOptionCode("x")))
    GameOption("x", "") should beInvalid() // (Left(InvalidOptionCode("x")))
  }

  it should "make random Option" in {
    GameOption.random("fred").player should be ("fred")
  }

  it should "provide list of options" in {
    GameOption.codes should contain only("r", "s", "p")
  }
}
