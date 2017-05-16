import scala.util.Random

import ProgramInterface._

trait ProgramInterface {

  def run() = startGame(prompt(PLAY), (0, 0))

  def startGame(wantToPlay: String, totals: (Int, Int)): (Int, Int) = wantToPlay match {
    case "yes"  =>    makeSelection(prompt(OPTIONS), totals)
    case "no"   =>    println("Good Bye")
                      totals
    case _      =>    startGame(prompt("Please enter Yes or No"), totals)
  }

  def makeSelection(playersChoice: String, totals: (Int, Int)): (Int, Int) = if (playersChoice == "r" || playersChoice == "p" || playersChoice == "s") {
    val computerPick = computerChoice()

    println(matchupMessage(playersChoice, computerPick))

    val (humanScore, compScore) = processGameResults(compareToWin(playersChoice, computerPick), totals)
    println(scoreMessage(humanScore, compScore))

    startGame(prompt(PLAY_AGAIN), (humanScore, compScore))
  } else {
    makeSelection(prompt(OPTIONS), totals)
  }

  def compareToWin(humanChoice: String, compChoice: String): Int = (humanChoice, compChoice) match {
    case ("r", "p") => -1
    case ("r", "s") =>  1
    case ("p", "s") =>  -1
    case ("p", "r") =>  1
    case ("s", "r") =>  -1
    case ("s", "p") =>  1
    case _          =>  0
  }

  def computerChoice() = {
    val i = rnd.nextInt(3)

    if (i == 0) "r" else if (i == 1) "p" else "s"
  }

  def prompt(message: String) = io.StdIn.readLine(formatPrompt(message)).toLowerCase

  def formatPrompt(message: String) = message + ": "

  def scoreMessage(humanScore: Int, compScore: Int) = s"Score is You = $humanScore and the Machine = $compScore"

  def matchupMessage(playerChoice: String, computerChoice: String) = s"You chose $playerChoice and the computer chose $computerChoice"

  protected def rnd = new Random()

  private def processGameResults(gameResult: Int, totals: (Int, Int)): (Int, Int) = gameResult match {
    case 0  =>  println(TIE)
                totals
    case -1 =>  println(COMPUTER_WINS)
                (totals._1, totals._2 + 1)
    case 1  =>  println(PLAYER_WINS)
                (totals._1 + 1, totals._2)
  }
}

object ProgramInterface {
  def apply(): ProgramInterface = new ProgramInterface{}

  val PLAY            = "Would you like to play RoShamBo? Enter (Yes) or (No)"
  val PLAY_AGAIN      = "Would you like to play again?"
  val OPTIONS         = "Choose from either (R)ock, (P)aper, or (S)cissors"
  val COMPUTER_WINS   = "The computer wins."
  val PLAYER_WINS     = "You win"
  val TIE             = "The result is a tie."
}
