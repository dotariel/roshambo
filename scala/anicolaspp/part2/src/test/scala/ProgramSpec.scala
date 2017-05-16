import java.io.{ByteArrayInputStream, ByteArrayOutputStream, PrintStream}

import org.scalatest.{FlatSpec, Matchers}

class ProgramSpec extends FlatSpec with Matchers  {

  "rock" should "beat scissors" in {
    val output = Simulator.runSimulation("r", "s")

    output(0) should be ("Would you like to play RoShamBo? Enter Yes or No: Make a selection: r for rock, p for paper, s for scissors: You chose r and the computer chose s")
    output(1) should be ("You win")
    output(2) should be ("Score is You = 1 and the Machine = 0")
    output(3) should be ("Would you like to play again? Good Bye")

  }

  "scissors" should "beat paper" in {
    val output = Simulator.runSimulation("s", "p")

    output(0) should be ("Would you like to play RoShamBo? Enter Yes or No: Make a selection: r for rock, p for paper, s for scissors: You chose s and the computer chose p")
    output(1) should be ("You win")
    output(2) should be ("Score is You = 1 and the Machine = 0")
    output(3) should be ("Would you like to play again? Good Bye")
  }

  "paper" should "beat rock" in {
    val output = Simulator.runSimulation("p", "r")

    output(0) should be ("Would you like to play RoShamBo? Enter Yes or No: Make a selection: r for rock, p for paper, s for scissors: You chose p and the computer chose r")
    output(1) should be ("You win")
    output(2) should be ("Score is You = 1 and the Machine = 0")
    output(3) should be ("Would you like to play again? Good Bye")
  }

  "same" should "tie same" in {
    Simulator.runSimulation("p", "p")(1) should be ("The result is a tie.")
    Simulator.runSimulation("r", "r")(1) should be ("The result is a tie.")
    Simulator.runSimulation("s", "s")(1) should be ("The result is a tie.")
  }

  "program" should "keep score" in {

    val (humanScore, computerScore) = Simulator.runMultipleSimulationsWith(List(("p", "p"), ("r", "s"), ("s", "r")))

    humanScore should be (1)
    computerScore should be (1)
  }
}





