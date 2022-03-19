package com.lambda.scala

trait DAO {
  // 抽象字段
  val id: Int
  // 具体字段
  val name:String = "huangbo"
  // 带实现的具体方法
  def delete(id:String): Boolean = true
  // 定义一个抽象方法, 注意不需要要加abtract关键字, 加了反而会报错
  def add(o:Any): Boolean
  def update(o:Any): Int
  def query(id:String): List[Any]
}
