

import scala.util.Random


trait ProgramInterface {

  var wantToPlay: String = _
  var playersChoice: String = _
  var computerPick: String = _
  var compScore = 0
  var humanScore = 0

  def run() = {
    wantToPlay = io.StdIn.readLine("Would you like to play RoShamBo? Enter Yes or No: ").toLowerCase()

    startGame()
  }

  def startGame(): Unit = {
    if (wantToPlay == "yes") {
      playersChoice = io.StdIn.readLine("Make a selection: r for rock, p for paper, s for scissors: ").toLowerCase()

      makeSelection()
    } else if (wantToPlay == "no") {
      println("Good Bye")
    }
    else {
      wantToPlay = System.console().readLine("Please Enter Yes or No: ").toLowerCase()
      startGame()
    }
  }

  def makeSelection(): Unit = {
    if (playersChoice == "r" || playersChoice == "p" || playersChoice == "s") {
      computerPick = computerChoice()

      println(s"You chose $playersChoice and the computer chose $computerPick")

      val gameResult = compareToWin(playersChoice, computerPick)

      if (gameResult == 0) {
        println("The result is a tie.")
      } else if (gameResult == -1) {
        println("The computer wins")
        compScore += 1
      } else {
        println("You win")
        humanScore += 1
      }


      println(s"Score is You = $humanScore and the Machine = $compScore")

      wantToPlay = io.StdIn.readLine("Would you like to play again? ").toLowerCase()

      if (wantToPlay == "yes" || wantToPlay == "no") {
        startGame()
      }
      else {
        wantToPlay = System.console().readLine("Please enter Yes or No").toLowerCase()
        startGame()
      }
    }  else{
      playersChoice = System.console().readLine("Your only choices are r,p, and s. Choose again: ").toLowerCase()
      makeSelection()
    }
  }

  def compareToWin(humanChoice: String, compChoice: String): Int = {
    if (humanChoice == "r" && compChoice == "p")  -1
    else if (humanChoice == "r" && compChoice == "s") 1
    else if (humanChoice == "p" && compChoice == "s")  -1
    else if (humanChoice == "p" && compChoice == "r")  1
    else if (humanChoice == "s" && compChoice == "r")  -1
    else if (humanChoice == "s" && compChoice == "p")  1
    else 0
  }

  def computerChoice() = {
    val i = Random.nextInt(3)

    if (i == 0) "r" else if (i == 1) "p" else "s"
  }
}

object Program extends ProgramInterface {

  def main(args: Array[String]): Unit = run()
}
