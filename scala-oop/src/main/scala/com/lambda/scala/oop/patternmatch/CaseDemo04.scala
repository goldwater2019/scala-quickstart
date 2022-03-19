package com.lambda.scala.oop.patternmatch

import scala.util.Random

/**
 * case class是多例的
 * case object是单例的
 */
object CaseDemo04 extends App{
  val arr = Array(CheckTimeOutTask, HeartBeat(123), SubmitTask("0001", "task1"))
  var element = arr(Random.nextInt(arr.length))

  element match {
    case SubmitTask(id, name) => {
      println(s"submit task: id: $id, name: $name")
    }
    case HeartBeat(time) => {
      println(s"heart beat : $time")
    }
    case CheckTimeOutTask => {
      println("check timeout task")
    }

    case _ => {
      println("啥都不是")
    }
  }
}

case object CheckTimeOutTask

case class SubmitTask(id:String, name:String);
case class HeartBeat(time:Long);

