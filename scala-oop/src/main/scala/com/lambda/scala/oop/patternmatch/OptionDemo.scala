package com.lambda.scala.oop.patternmatch

object OptionDemo {
  def main(args: Array[String]): Unit = {
    val map = Map("a"->1, "b"->2)
    val v = map.get("b")
    v match {
      case Some(i) => {
        val clazz = i.getClass
        println(s"class: $clazz, value: $i")
      }
      case None => {
        println(s"class: None, value: None")
      }
    }
    println(v)

    // 更好的方法
    val v1 = map.getOrElse("c", 0)
    println(v1)
  }
}
