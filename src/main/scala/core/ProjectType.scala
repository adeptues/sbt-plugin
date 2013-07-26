package core

import java.io._

abstract trait ProjectType {
  def generateFiles(name: String, scalaVersion: String, sbtVersion: String): Unit
  def text(s: String, file: File):Unit
  def createProjectDir(projectName: String): Boolean
  def readInput(message: String): String = {
    print(message)
    val response = scala.Console.readLine()
    println
    response
  }
}
