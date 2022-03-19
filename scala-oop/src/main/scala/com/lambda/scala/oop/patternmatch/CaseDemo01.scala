package com.lambda.scala.oop.patternmatch

import scala.util.Random

object CaseDemo01 {
  def main(args: Array[String]): Unit = {
    val arr = Array("黄渤", "徐峥", "王宝强", "xxxxx");
    val name = arr(Random.nextInt(arr.length))

    // name模式匹配字符串
    name match {
      case "黄渤" => println("影帝黄渤来啦...........")
      case "徐峥" => println("光头徐峥来啦...........")
      case "王宝强" => println("实力干将来啦..........")
      case _ => println("谁..?????")
    }
  }
}
