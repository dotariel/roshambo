
sealed trait OptionException

case class InvalidOptionCode(code: String) extends OptionException
