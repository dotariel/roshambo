import GameOption.Rock
import ProgramInterface._

import scala.util.{Left, Random, Right}

trait ProgramInterface {

  def run() = {
    val game = startGame(prompt(PLAY), List.empty)

    println("Good Bye")

    game
  }

  private def startGame(wantToPlay: String, history: History): History = if (enfocePlayerChoice(wantToPlay)) play(history) else history.reverse

  private def play(history: History) = {
    val u = playerChoice(prompt(OPTIONS))
    val p = computerChoice()
    outputSelection(u, p)

    val newHistory = processGameResults(u.beats(p), history)
    println(scoreMessage(newHistory.head._1, newHistory.head._2))

    startGame(prompt(PLAY_AGAIN), newHistory)
  }

  protected def computerChoice(): GameOption = GameOption(GameOption.codes(rnd.nextInt(3)), "pc").getOrElse(Rock("pc"))

  protected def rnd = new Random()
}

object ProgramInterface {
  def apply(): ProgramInterface = new ProgramInterface {}

  def playerChoice(playerInput: String): GameOption = GameOption(playerInput, "user").fold(_ =>  playerChoice(prompt(OPTIONS)), identity)

  def processGameResults(gameResult: Int, history: History): History = gameResult match {
    case 0  =>  println(TIE)
      (0, 0) :: history
    case -1 =>  println(COMPUTER_WINS)
      (0, 1) :: history
    case 1  =>  println(PLAYER_WINS)
      (1, 0) :: history
  }

   def outputSelection(u: GameOption, p: GameOption) = println(matchupMessage(u.code, p.code))

   def enfocePlayerChoice(input: String): Boolean = input match {
    case "yes"  =>  true
    case "no"   =>  false
    case _      =>  enfocePlayerChoice(prompt("Please enter Yes or No"))
  }

  def prompt(message: String) = io.StdIn.readLine(formatPrompt(message)).toLowerCase

  def formatPrompt(message: String) = message + ": "

  def scoreMessage(humanScore: Int, compScore: Int) = s"Score is You = $humanScore and the Machine = $compScore"

  def matchupMessage(playerChoice: String, computerChoice: String) = s"You chose $playerChoice and the computer chose $computerChoice"

  type RoundResult = (Int, Int)
  type History = List[RoundResult]

  implicit class ToScore(history: History) {
    def score(): RoundResult = history.foldLeft((0, 0))(sum)

    private def sum(x: (Int, Int), y: (Int, Int)) = (x._1 + y._1, x._2 + y._2)
  }

  val PLAY = "Would you like to play RoShamBo? Enter (Yes) or (No)"
  val PLAY_AGAIN = "Would you like to play again?"
  val OPTIONS = "Choose from either (R)ock, (P)aper, or (S)cissors"
  val COMPUTER_WINS = "The computer wins."
  val PLAYER_WINS = "You win"
  val TIE = "The result is a tie."
}
