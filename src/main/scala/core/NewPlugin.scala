package core
import sbt._
import Keys._

object NewProjectPlugin extends Plugin {
  override lazy val settings = Seq(commands += myCommand)

  lazy val myCommand = Command.command("hello") { (state: State) =>
    println("Hello world")
    state
  }
  /*  object SbtNew {
    val Config = config("newsbt")
    val hello = TaskKey[Unit]("hello", "Prints 'Hello World'") in Config
    val helloTask = hello := {
      println("Hello World")
    }
  }*/
}
