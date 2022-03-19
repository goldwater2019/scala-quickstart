package com.lambda.scala

object TraitChainTest {
  def main(args: Array[String]): Unit = {
    val personHandlerChain = new PersonHandlerChain("张小龙")
    personHandlerChain.sayHello
  }
}

trait Handler {
  def handler(data:String) {}
}

trait HandlerA extends Handler {
  override def handler(data: String): Unit = {
    println("HandlerA: " + data)
    super.handler(data)
  }
}

trait HandlerB extends Handler {
  override def handler(data: String): Unit = {
    println("HandlerB: " + data)
    super.handler(data)
  }
}

trait HandlerC extends Handler {
  override def handler(data: String): Unit = {
    println("HandlerC: " +data)
    super.handler(data)
  }
}

class PersonHandlerChain(val name:String) extends HandlerC with HandlerB with HandlerA {
  def sayHello:Unit = {
    println("Hello: " + name)
    handler(name)
  }
}

