package com.lambda.scala

object OverrideDemo {
  def main(args: Array[String]): Unit = {
    val student22 = new Student222
    student22.eat()
  }
}

abstract class Person222 {
  def eat()
}

class Student222 extends Person222 {
  // 因为父类是抽象类, 所以override关键字可以不加

  override def eat(): Unit = {
    println("我要吃成金三胖")
  }
}
