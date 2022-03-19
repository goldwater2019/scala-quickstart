package com.lambda.scala.oop.patternmatch

object UnapplyTest {
  // 接收参数, 返回对象, 一般用作共产函数
  def apply(value: Double, unit:String) : Currency = {
    new Currency(value, unit)
  }

  // 接收对象, 返回参数, 一般用作模式匹配
  def unapply(currency: Currency): Option[(Double, String)] = {
    if (currency == null) {
      None
    } else {
      Some(currency.value, currency.unit)
    }
  }

  def main(args: Array[String]): Unit = {
    val currency = UnapplyTest(1, "lambda")
    println(s"apply function: currency: value: ${currency.value}, unit: ${currency.unit}")
  }
}

class Currency(val value: Double, val unit: String) {}
