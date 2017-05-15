import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

import GameOption.{Paper, Rock, Scissors}
import org.scalatest.{FlatSpec, Matchers}

import scala.util.Random

class ProgramSpec extends FlatSpec with Matchers  {

  "rock" should "beat scissors" in {
    val output = Simulator.runSimulation("r", "s")

    output(0) should be ("Would you like to play RoShamBo? Enter (Yes) or (No): Choose from either (R)ock, (P)aper, or (S)cissors: You chose r and the computer chose s")
    output(1) should be ("You win")
    output(2) should be ("Score is You = 1 and the Machine = 0")
    output(3) should be ("Would you like to play again?: Good Bye")

  }

  "scissors" should "beat paper" in {
    val output = Simulator.runSimulation("s", "p")

    output(0) should be ("Would you like to play RoShamBo? Enter (Yes) or (No): Choose from either (R)ock, (P)aper, or (S)cissors: You chose s and the computer chose p")
    output(1) should be ("You win")
    output(2) should be ("Score is You = 1 and the Machine = 0")
    output(3) should be ("Would you like to play again?: Good Bye")
  }

  "paper" should "beat rock" in {
    val output = Simulator.runSimulation("p", "r")

    output(0) should be ("Would you like to play RoShamBo? Enter (Yes) or (No): Choose from either (R)ock, (P)aper, or (S)cissors: You chose p and the computer chose r")
    output(1) should be ("You win")
    output(2) should be ("Score is You = 1 and the Machine = 0")
    output(3) should be ("Would you like to play again?: Good Bye")
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

  it should "randomize computer choice" in {

    val randomStub = new Random() {
      var m = 0

      override def nextInt(n: Int): Int = {
        val r = m
        m += 1

        r
      }
    }

    new ProgramInterface {
      override def rnd: Random = randomStub

      computerChoice() should be (Rock("pc"))
      computerChoice() should be (Scissors("pc"))
      computerChoice() should be (Paper("pc"))
    }
  }

  it should "format prompt message" in {
    ProgramInterface.formatPrompt("hello") should be ("hello: ")
  }

  it should "read console input" in {

    val in = new ByteArrayInputStream(s"user input\n".getBytes())
    Console.setIn(in)

    ProgramInterface.prompt("Would you like to play?") should be ("user input")
  }

  it should "build score message" in {
    ProgramInterface.scoreMessage(2, 3) should be ("Score is You = 2 and the Machine = 3")
    ProgramInterface.scoreMessage(0, 0) should be ("Score is You = 0 and the Machine = 0")
  }
}




