import GameOption.{Paper, Rock, Scissors}

import scala.util.Random

sealed trait GameOption {
  def player: String

  def code: String

  def beats(other: GameOption) = (this, other) match {
    case (Paper(_), Paper(_))       =>  0
    case (Paper(_), Rock(_))        =>  1
    case (Paper(_), Scissors(_))    =>  -1
    case (Rock(_), Rock(_))         =>  0
    case (Rock(_), Scissors(_))     =>  1
    case (Rock(_), Paper(_))        =>  -1
    case (Scissors(_), Scissors(_)) =>  0
    case (Scissors(_), Paper(_))    =>  1
    case (Scissors(_), Rock(_))     =>  -1
  }
}

object GameOption {

  def apply(code: String, player: String): Either[OptionException, GameOption] = code match {
    case "r"  =>  Right(Rock(player))
    case "s"  =>  Right(Scissors(player))
    case "p"  =>  Right(Paper(player))
    case x    =>  Left(InvalidOptionCode(x))
  }

  def random(player: String): GameOption = {
    val code = codes(Random.nextInt(codes.length))

    GameOption(code, player).getOrElse(Rock(player))
  }

  case class Rock(player: String) extends GameOption {
    override def code: String = "r"
  }

  case class Paper(player: String) extends GameOption {
    override def code: String = "p"
  }

  case class Scissors(player: String) extends GameOption {
    override def code: String = "s"
  }

  val codes = List("r", "s", "p")
}