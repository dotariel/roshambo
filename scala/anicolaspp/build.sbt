name := "roshambo"

version := "1.0"

scalaVersion := "2.12.2"

lazy val roshambo = project.in(file("."))
  .dependsOn(part1, part2, part3, part4)
  .aggregate(part1, part2, part3, part4)

lazy val part1 = project.in(file("part1"))
lazy val part2 = project.in(file("part2"))
lazy val part3 = project.in(file("part3"))
lazy val part4 = project.in(file("part4"))
