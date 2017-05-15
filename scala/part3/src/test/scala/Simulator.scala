import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

import Simulator.SimulationResult

private class Simulator(playerChoice: String, computer: String) extends ProgramInterface {

  def runSimulation(): SimulationResult = {

    val in = new ByteArrayInputStream(s"yes\n${playerChoice}\nno\n".getBytes())
    Console.setIn(in)

    val out = new ByteArrayOutputStream()
    Console.setOut(out)

    val scores = run()

    SimulationResult(new String(out.toByteArray).split("\n").toList, scores)
  }

  override def computerChoice(): String = computer
}

object Simulator {
  def runSimulation(playerChoice: String, computer: String): List[String] =
    new Simulator(playerChoice, computer).runSimulation().output

  def runMultipleSimulationsWith(inputs: List[(String, String)]): (Int, Int) = {

    def runSimulationWith(inputs: List[(String, String)], totals: (Int, Int)): (Int, Int) = inputs match {
      case Nil          =>  totals
      case head :: tail =>  {
        val simulator = new Simulator(head._1, head._2)

        val result = simulator.runSimulation()

        runSimulationWith(tail, (result.scores._1 + totals._1, result.scores._2 + totals._2))
      }
    }

    runSimulationWith(inputs, (0, 0))
  }

  case class SimulationResult(output: List[String], scores: (Int, Int))
}