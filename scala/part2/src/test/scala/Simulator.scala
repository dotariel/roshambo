import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

private class Simulator(playerChoice: String, computer: String) extends ProgramInterface {

  def runSimulation(): List[String] = {

    val in = new ByteArrayInputStream(s"yes\n${playerChoice}\nno\n".getBytes())
    Console.setIn(in)

    val out = new ByteArrayOutputStream()
    Console.setOut(out)

    run()

    new String(out.toByteArray).split("\n").toList
  }

  override def computerChoice(): String = computer
}

object Simulator {
  def runSimulation(playerChoice: String, computer: String): List[String] =
    new Simulator(playerChoice, computer).runSimulation()

  def runMultipleSimulationsWith(inputs: List[(String, String)]): (Int, Int) = {

    def runSimulationWith(inputs: List[(String, String)], totals: (Int, Int)): (Int, Int) = inputs match {
      case Nil          =>  totals
      case head :: tail =>  {
        val simulator = new Simulator(head._1, head._2)

        simulator.runSimulation()

        runSimulationWith(tail, (simulator.humanScore + totals._1, simulator.compScore + totals._2))
      }
    }

    runSimulationWith(inputs, (0, 0))
  }
}