package com.lambda.scala.rpc.akkarpc1

import akka.actor.{Actor, ActorSystem, Props}
import com.typesafe.config.ConfigFactory;

class Master extends Actor {

  def doHello(): Unit = {
    println("我是Master, 我收到了worker的hello消息")
  }


  /**
   * 其实就是一个死循环, 接收消息
   * @return
   */
  override def receive: Receive = {
    case "hello" => {
      // 模拟的业务方法
      doHello()
      // sender() // 谁发过来消息这个就是谁
      sender() ! "Hi" // ! "hi" 给sender()发送一个hi的消息
    }
  }
}

object Master {
  def main(args: Array[String]): Unit = {
    var str =
      """
        |akka.actor.provider = "akka.remote.RemoteActorRefProvider"
        |akka.remote.netty.tcp.hostname = localhost
        |akka.remote.netty.tcp.port = 6790
        |""".stripMargin
    val config = ConfigFactory.parseString(str)

    //
    val actorSystem = ActorSystem("MasterActorSystem", config)

    actorSystem.actorOf(Props(new Master()), "MasterActor")
  }
}
