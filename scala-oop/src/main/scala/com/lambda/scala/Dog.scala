package com.lambda.scala

class Dog {
  val id = 100
  private var name = "旺财"

  def printName(): Unit = {
    // 在Dog类中可以访问伴生对象Dog的私有属性
    println(Dog.CONSTANT + name)
  }
}

/**
 * 伴生对象
 */
object Dog {
  // 伴生对象中的私有属性
  private val CONSTANT:String = "汪汪汪...."

  def main(args: Array[String]): Unit = {
    val p = new Dog
    // 设置私有的字段
    p.name = "123"
    p.printName()
    println(p.id)
  }
}
