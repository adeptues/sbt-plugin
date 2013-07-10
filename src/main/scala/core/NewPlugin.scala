package core
import sbt._
import Keys._

object NewProjectPlugin extends Plugin {
  override lazy val settings = Seq(commands += myCommand)

  lazy val myCommand = Command.command("hello") { (state: State) =>
    val projectName = readInput("Enter Project Name: ")
    val sbtVersion = readInput("Enter SBT Version: ")
    val scalaVersion = readInput("Enter Scala Version: ")
    createProjectDir(projectName)
    generateFiles(projectName,scalaVersion,sbtVersion)
    state
  }

  def generateFiles(name: String, scalaVersion: String, sbtVersion: String) {
    val defOrg = "com.example.org"
    val quote = "\""
    val buildsbt = "name := " + quote + name + quote + "\n\n" + "organization := " + quote + defOrg + quote + "\n\n" +
      "version := " + quote + "1.0" + quote + "\n\n" + "scalaVersion := " + quote + scalaVersion + quote

    val buildProp = "sbt.version=" + sbtVersion
    
    text(buildProp, new File(name + "/" + "project/build.properties"))
    text(buildsbt, new File(name + "/" + "build.sbt"))
    //build.scala

  }

  def text(s: String, file: File) {
    val out = new java.io.PrintWriter(file)
    try { out.print(s) }
    finally { out.close }
  }

  def createProjectDir(projectName: String): Boolean = {
    val homeDir = new java.io.File(projectName)
    IO.createDirectory(homeDir)
    IO.createDirectory(new File(projectName + "/" + "lib"))
    IO.createDirectory(new File(projectName + "/" + "project"))

    //source dir
    val main = projectName + "/" + "src/" + "main" + "/"
    IO.createDirectory(new File(main + "resources"))
    IO.createDirectory(new File(main + "scala"))
    IO.createDirectory(new File(main + "java"))

    //test
    val testDir = projectName + "/" + "src/" + "test" + "/"
    IO.createDirectory(new File(testDir+"resources"))
    IO.createDirectory(new File(testDir+"scala"))
    IO.createDirectory(new File(testDir+"java"))

    false
  }

  def readInput(message: String): String = {
    println(message)
    val response = scala.Console.readLine()
    println
    response
  }

}
