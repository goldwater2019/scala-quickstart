package com.lambda.scala

object ClassDemo {
  def main(args: Array[String]): Unit = {
    val human = new Human
    println(human.fight())
  }
}

trait Flyable {
  def fly(): Unit = {
    println("I can fly")
  }

  def fight() : String
}

abstract class Animal1 {
  def run(): Int

  val name: String
}

class Human extends Animal1 with Flyable {
  val name = "abc";

  // 五个变量分别赋值一次, 那么 {}也就相当于执行五次
  val t1, t2, (a,b,c), t3, t4 = {
    println("ABC");
    (1,2,3)
  }

  println(a)
  println(t1._1)
  println(t1.hashCode() + "\t" + t2.hashCode())

  // 在scala 中重写一个非抽象方法必须使用overrde关键字
  def fight(): String = {
    "fight with 棒子"
  }

  override def run(): Int = {
    1
  }
}
