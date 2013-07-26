package core
import sbt._
import Keys._

object NewProjectPlugin extends Plugin {
  override lazy val settings = Seq(commands += myCommand)

  lazy val myCommand = Command.command("new") { (state: State) =>
    val projectName = readInput("Enter Project Name: ")
    val sbtVersion = readInput("Enter SBT Version: ")
    val scalaVersion = readInput("Enter Scala Version: ")
    val projectType:ProjectType = true match {
      case _ => DefaultProject
    }

    projectType.createProjectDir(projectName)
    projectType.generateFiles(projectName, scalaVersion, sbtVersion)

    state
  }

  def readInput(message: String): String = {
    print(message)
    val response = scala.Console.readLine()
    println
    response
  }

}
