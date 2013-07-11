package core
import sbt._
import Keys._

object NewProjectPlugin extends Plugin {
  override lazy val settings = Seq(commands += myCommand)

  lazy val myCommand = Command.command("new") { (state: State) =>
    val projectName = readInput("Enter Project Name: ")
    val sbtVersion = readInput("Enter SBT Version: ")
    val scalaVersion = readInput("Enter Scala Version: ")
    createProjectDir(projectName)
    generateFiles(projectName, scalaVersion, sbtVersion)
    state
  }

  def generateFiles(name: String, scalaVersion: String, sbtVersion: String) {
    val defOrg = "com.example.org"
    val quote = "\""
    val buildsbt = "name := " + quote + name + quote + "\n\n" + "organization := " + quote + defOrg + quote + "\n\n" +
      "version := " + quote + "1.0" + quote + "\n\n" + "scalaVersion := " + quote + scalaVersion + quote

    val buildProp = "sbt.version=" + sbtVersion

    val plugins = "addSbtPlugin(\"com.typesafe.sbteclipse\" % \"sbteclipse-plugin\" % \"2.2.0\")" + "\n\n" +
      "addSbtPlugin(\"org.ensime\" % \"ensime-sbt-cmd\" % \"0.1.1\")" + "\n\n" +
      "addSbtPlugin(\"com.github.mpeltonen\" % \"sbt-idea\" % \"1.4.0\")"

    text(buildProp, new File(name + "/" + "project/build.properties"))
    text(buildsbt, new File(name + "/" + "build.sbt"))
    text(plugins, new File(name + "/" + "project/plugins.sbt"))
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
    IO.createDirectory(new File(testDir + "resources"))
    IO.createDirectory(new File(testDir + "scala"))
    IO.createDirectory(new File(testDir + "java"))

    false
  }

  def readInput(message: String): String = {
    print(message)
    val response = scala.Console.readLine()
    println
    response
  }

}
