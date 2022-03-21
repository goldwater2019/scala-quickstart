package com.lambda.scala.rpc.akkarpc1

import akka.actor.{Actor, ActorSystem, Props}
import com.typesafe.config.ConfigFactory


class Worker extends Actor {

  def doHi(): Unit = {
    println("我是Worker, 我接收到了Master的hi的消息")
  }

  // 如果actor一致性, 首先运行的就是这个方法, 只运行一次
  override def preStart(): Unit = {
    // 实现的是给Master发送消息 确定master地址
    val workerActor = context.actorSelection("akka.tcp://MasterActorSystem@localhost:6790/user/MasterActor")
    workerActor ! "hello"
  }

  override def receive: Receive = {
    case "hi" => {
      doHi()
    }
  }
}

object Worker {
  def main(args: Array[String]): Unit = {
    val str =
      """
        |akka.actor.provider = "akka.remote.RemoteActorRefProvider"
        |akka.remote.netty.tcp.hostname = localhost
        |""".stripMargin

    val config = ConfigFactory.parseString(str)
    val actorSystem = ActorSystem("WorkerActorSystem", config)
    actorSystem.actorOf(Props(new Worker()), "workerActor")
  }
}
