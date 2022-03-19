package com.lambda.scala

object ExtendsDemo {
  def main(args: Array[String]): Unit = {
    val student = new Student("黄渤", 33, "1024")
    println(student.studentNo)
  }
}

class Person(name:String, age: Int) {
  println("Person: " + name + "\t" + age)
}

class Student(name:String, age:Int, var studentNo: String) extends Person(name, age) {
  println("Student: :"  + name + "\t" + age + "\t" + studentNo )
}
