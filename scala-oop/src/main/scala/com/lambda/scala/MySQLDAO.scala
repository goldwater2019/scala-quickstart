package com.lambda.scala

trait MySQLDAO {
  val id: Int
  def add(o:Any):Boolean
  def update(o:Any):Int
  def query(id:String):List[Any]
}

// 如果有多个trait的话, 则使用with关键字即可
class DaoImpl extends MySQLDAO with Serializable {
  // 给父类中的具体字段赋值
  override val id = 12
  // 实现具体方法
  def add(o:Any):Boolean=true

  override def update(o: Any): Int = 1

  override def query(id: String): List[Any] = List(1,2,3)
}
