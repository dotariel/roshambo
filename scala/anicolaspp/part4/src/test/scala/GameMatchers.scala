import org.scalatest.matchers.{BePropertyMatchResult, BePropertyMatcher, MatchResult, Matcher}

import scala.util.{Left, Right}

trait GameMatchers {

  case class GameOptionMatcher(option: GameOption) extends Matcher[Either[OptionException, GameOption]] {
    override def apply(left: Either[OptionException, GameOption]): MatchResult = left match {
      case Left(value) => MatchResult(matches = false, value.toString, "")
      case Right(value) => {
        val result = (value.code == option.code && value.player == option.player)

        MatchResult(result, "Code does not match", "Code matches")
      }
    }
  }

  case object InvalidGameOptionMatcher extends Matcher[Either[OptionException, GameOption]] {
    override def apply(left: Either[OptionException, GameOption]): MatchResult = left match {
      case Left(value)  =>  MatchResult(matches = true, "", "")
      case Right(value) =>  MatchResult(matches = false, s"Expecting OptionException, but found $value", "")
    }
  }

  case object BeInvalid extends BePropertyMatcher[Either[OptionException, GameOption]] {
    override def apply(left: Either[OptionException, GameOption]): BePropertyMatchResult = {
      val value = InvalidGameOptionMatcher(left)

      BePropertyMatchResult(value.matches, "Is")
    }
  }

  def unwrapTo(option: GameOption) = GameOptionMatcher(option)

  def beInvalid() = InvalidGameOptionMatcher

  def Invalid = BeInvalid
}
