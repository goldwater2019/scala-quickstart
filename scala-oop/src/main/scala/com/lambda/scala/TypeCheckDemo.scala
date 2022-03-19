package com.lambda.scala

object TypeCheckDemo {
  def main(args: Array[String]): Unit = {
    val student1 = new Student11
    val person1 = new Person11

    println(student1.isInstanceOf[Student11])
    println(person1.isInstanceOf[Person11])
    println(classOf[Student11])
    println(classOf[Person11])
  }
}

class Person11 {}

class Student11 extends Person11 {}
