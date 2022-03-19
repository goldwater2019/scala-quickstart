package com.lambda.scala

object MyLoggerTraitTest {
  def main(args: Array[String]): Unit = {
    val person1 = new Person123("刘德华")
    person1.sayHi()

    val person2 = new Person123("黄渤") with Logger_B
    person2.sayHi()
  }
}

trait MyLogger {
  def log(msg:String) {}
}

trait Logger_A extends MyLogger {
  override def log(msg: String): Unit = {
    println("test: " + msg)
  }
}

trait Logger_B extends MyLogger {
  override def log(msg: String): Unit = {
    println("log: " + msg)
  }
}

class Person123(val name:String) extends Logger_A {
  def sayHi(): Unit = {
    println("Hi, I'm " + name)
    log("sayHi is invoked")
  }
}