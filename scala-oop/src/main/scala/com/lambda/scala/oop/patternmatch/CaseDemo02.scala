package com.lambda.scala.oop.patternmatch

import scala.util.Random

object CaseDemo02 {
  def main(args: Array[String]): Unit = {
    val arr = Array("hello", 1, 2.0, -1.0, CaseDemo)
    val v = arr(Random.nextInt(arr.length))
    println(v)
    v match {
      case x: Int => println("Int: " + x)
      case x: Double if (x >= 0) => println("Double: " + x)
      case x: String => println("String: " + x)
      case _ => throw new Exception("not match exception")
    }
  }
}

case class CaseDemo()
