package com.lambda.scala.oop.patternmatch

object PartialFuncDemo {
  // 盘函数
  def func1: PartialFunction[String, Int] = {
    case "one" => 1
    case "two" => 2
    case _ => -1
  }

  // 普通函数的模式匹配实现
  def func2(num:String):Int = num match {
    case "one" => 1
    case "two" => 2
    case _ => -1
  }

  /**
   * 偏函数就是用来做模式匹配的
   * @param args
   */
  def main(args: Array[String]): Unit = {
    println(func1("one"))
    println(func2("one"))
  }
}
