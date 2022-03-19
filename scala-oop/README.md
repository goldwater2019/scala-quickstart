
## 单例对象

在某些应用场景下, 我们可能不需要创建对象, 而是想直接调用方法. 但是`Scala`语言并不支持静态成员, 没有静态方法和静态字段, `Scala`通过单例对象`object`来解决该问题

1. 存放工具方法和常量
2. 高效共享单个不可变的实例
3. 单例模式

总结
> 1. `object`里面的方法都是静态方法
> 2. `object`里面的字段都是静态字段
> 3. 它本身就是一个单例(因为不需要去`new`)


## 伴生对象  

在Scala的类中, 与类名相同的单例对象叫做伴生对象, 也就是说如果我们在`object Dog`所在的文件内定义了一个`class Dog`, 此时
1. `object Dog` 被成为`class Dog`的伴生对象
2. `class Dog` 被成为`object Dog`的伴生类
3. 类和伴生对象之间可以互相访问所有的私有方法和属性
4. 类和伴生对象之间不可访问对象使用`private[this]`修饰的方法和属性

总结:
> 伴生类和伴生对象之间可以互相访问对方的私有属性

## `Apply方法`
再将集合和数组的时候, 可以通过`val intList=List(1,2,3)`这种方式创建一个初始化列表对象, 其实它相当于调用`val intList = List.apply(1,2,3)`,
只不过`val intList=List(1,2,3)`这种创建方式更简洁一点, 但我们必须明确这种创建方式仍然避免不了`new`, 它后面的实现机制仍然是`new`的方式, 只不过我们自己在使用的时候可以省去`new`的操作.
通常我们会在类的伴生对象中定义`apply`方法, 当遇到`类名(参数1,..., 参数n)`时`apply`方法就会被调用


```scala
object ApplyDemo {
  def main(args: Array[String]): Unit = {
    // 调用了Array伴生对象的apply方法
    val arr1 = Array(5)
    println(arr1.toBuffer)
    
    // new了一个长度为5的array, 数组里面包含5个null
    val arr2 = new Array(5);
    println(arr2.toBuffer)
  }
}
```

## 应用程序对象App

Scala程序都必须从一个对象的main方法开始, 可以通过拓展`App`特质, 不写`main`方法

```scala
object AppObjectDemo extends App {
  // 不用写main方法
  println("I love scala")
}
```

> 无法为该类的执行从外部传入参数, 也即没有main方法的参数可用


## 抽象类
抽象类是一种不能被实例化的类, 抽象类中包含了若干不能完整定义的方法, 这些方法由子类去拓展定义自己的实现

1. 如果在父类中, 有某些方法无法立即实现, 而需要依赖不同的子类来覆盖, 重写实现自己不同的方法实现, 此时可以将父类中这些方法不给出具体的实现, 只有方法签名, 这种方法就是抽象方法
2. 而一个类中如果有一个抽象方法, 那么类就必须用`abstract`来声明为抽象类, 此时抽象类是不可实例化的
3. 在子类中覆盖抽象类的抽象方法时, 不需要使用`override`关键字, 也可以用


```scala

/**
 * 定义抽象类
 */
abstract class Animal {
  // 抽象字段(域)
  // 前面我们提到, 一般类中定义字段的话, 必须初始化
  // 而抽象类中则没有这要求
  val height: Int
  
  // 抽象方法
  def eat: Unit
}


/**
 * Person继承Animal
 * 1. 对eat方法进行了实现
 * 2. 通过主构造器对height参数进行了初始化
 * @param height
 */
class Person(val height: Int) extends Animal {

  /**
   * 对父类中的方法进行实现
   * 注意: 这里可以不加override关键字
   */
  override def eat: Unit = {
    println("eat by mouth")
  }
}

```

总结:
> `Scala`抽象类的使用方式和`Java`中的抽象类的概念一致


# Scala 继承

## 拓展类

在scala中拓展类的方式和Java一样, 都是使用`extends`关键字

1. Scala中, 让子类继承父类, 与`Java`一样, 也是使用`extends`关键字
2. 继承就代表, 子类可以从父类继承父类的`field`和`method`, 然后子类可以在自己内部放入父类没有但是子类所特有的`field`和`method`, 使用继承可以有效得复用代码
3. 子类可以覆盖父类得`field`和`method`, 但如果父类用`final`修饰, `则无法继承这个类
4. 如果父类得`field`或者`method`被`final`修饰, 子类无法覆盖

```scala
package com.lambda.scala

object ExtendsDemo {
  def main(args: Array[String]): Unit = {
    val student = new Student("黄渤", 33, "1024")
    println(student.studentNo)
  }
}

class Person(name:String, age: Int) {
  println("Person: " + name + "\t" + age)
}

class Student(name:String, age:Int, var studentNo: String) extends Person(name, age) {
  println("Student: :"  + name + "\t" + age + "\t" + studentNo )
}
```

## 重写方法(Override和Super)

关于`override`和`super`的要点
1. `scala`中, 如果子类要重写父类中的非抽象方法, 则必须使用`override`关键字
2. `override`关键字可以帮助我们尽早地发现代码里的错误, 比如: `override`修饰的父类方法的方法名我们拼写错了; 比如要覆盖的父类方法的参数我们写错了; 等等
3. 此外, 在子类覆盖父类方法之后, 如果我们在子类中要调用父类的被覆盖的方法, 可以使用`super`关键字, 显式地指定要调用的父类的方法

```scala
object OverrideDemo {
  def main(args: Array[String]): Unit = {
    val student22 = new Student222
    student22.eat()
  }
}

abstract class Person222 {
  def eat()
}

class Student222 extends Person222 {
  // 因为父类是抽象类, 所以override关键字可以不加

  override def eat(): Unit = {
    println("我要吃成金三胖")
  }
}
```

## 类型检查和转换

| 操作  | Scala | Java          |
|-----| ---- |---------------|
| 判断  | `obj.isInstanceOf[C]` | `obj instanceof C` |
| 转换 | `obj.asInstanceOf[C]` | `(C)obj`       |
| 获取 | `classOf[C]` | `C.class`     |

```scala
package com.lambda.scala

object TypeCheckDemo {
  def main(args: Array[String]): Unit = {
    val student1 = new Student11
    val person1 = new Person11

    println(student1.isInstanceOf[Student11])
    println(person1.isInstanceOf[Person11])
    println(classOf[Student11])
    println(classOf[Person11])
  }
}

class Person11 {}

class Student11 extends Person11 {}

```

## 超类的构造

```scala
package com.lambda.scala

object ClassDemo {
  def main(args: Array[String]): Unit = {
    val human = new Human
    println(human.fight())
  }
}

trait Flyable {
  def fly(): Unit = {
    println("I can fly")
  }

  def fight() : String
}

abstract class Animal1 {
  def run(): Int

  val name: String
}

class Human extends Animal1 with Flyable {
  val name = "abc";

  // 五个变量分别赋值一次, 那么 {}也就相当于执行五次
  val t1, t2, (a,b,c), t3, t4 = {
    println("ABC");
    (1,2,3)
  }

  println(a)
  println(t1._1)
  println(t1.hashCode() + "\t" + t2.hashCode())

  // 在scala 中重写一个非抽象方法必须使用overrde关键字
  def fight(): String = {
    "fight with 棒子"
  }

  override def run(): Int = {
    1
  }
}
```

# 特质`Trait`

## 特质的定义
`Scala`和`Java`语言一样, 采用了很强的限制策略, 避免了多继承的问题.  
在`Java`语言中, 之云希继承一个超类, 该类可以实现多个接口, 但Java接口有其自身的局限性
* 接口只能包括抽象方法, 不能包含字段, 具体方法
`Scala`语言利用了`trait`解决了该问题, 在`Scala`的`trait`中, 它不但可以包括抽象方法, 还可以包含具体字段和具体方法.

```scala
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

```

## `Trait`的使用

### `Trait` 使用概述

`Java`中
1. 接口是一个特殊的抽象类
2. 里面所有的方法都是抽象方法  

`Scala`中
1. `Trait`里面的方法既可以实现, 也可以不实现
2. 与抽象类的区别
   1. 优先使用`Trait`, 一个类拓展多个`Trait`是很方便的, 但却只能拓展一个抽象类
   2. 如果你需要构造函数参数, 使用抽象类. 因为抽象类可以定义带参数的构造函数, 而`Trait`不行. 例如: 你不能说`trait t(i: Int) {}`, 参数`i`是非法的
3. 抽象类, 我们用的是`extends`关键字, 我们只能单继承, 但是`trait`可以通过`with`来做到多实现
4. 实现`trait`, 如果我们没有继承其它类, 那么我们第一个特质使用`extends`, 后面使用`with`
> 所以如果有人说实现`trait`只能使用`with`, 这是不对的

`Trait`的几种不同使用方式
1. 当作Java接口使用的`trait`, 全是抽象字段和抽象方法
2. 带具体实现方法的`trait`
3. 带具体字段的`trait`

`Trait`的底层实现就是采用的`Java`的抽象类

### 将`Trait`作为接口使用

`Scala`中的`Trait`是一种特殊的概念

* 首先我们可以将`Trait`作为接口来使用, 此时`Trait`和`Java`中的接口非常类似
* 在`Trait`中也可以定义抽象方法, 就与抽象类中的而抽象方法一样, 不给出方法的具体实现
* 在`Trait`中也可以定义具体方法, 给出方法的具体实现
* 在`Trait`中也可以定义具体字段, 也可以定义抽象字段
* 类可以使用`extends`关键字继承`trait`, 注意, 这里不是`implement`, 而是`extends`, 在`scala`中没有`implement`的概念, 无论继承还是`trait`, 统一都是`extends`
* 类继承`trait`后, 必须实现其中的抽象方法, 实现时不需要使用`override`关键字

`Scala`不支持对类进行多继承, 但是支持多重继承`trait`, 使用`with`关键字即可


```scala
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
```

### 为实例对象混入`Trait`

有时我们可以在创建类的对象时, 指定该对象混入某个`Trait`, 这样, 就只有这个对象混入该`Trait`的方法, 而类的其它实例对象则没有

```scala
object MyLoggerTraitTest {
  def main(args: Array[String]): Unit = {
    val person1 = new Person123("刘德华")
    person1.sayHi()

    val person2 = new Person123("黄渤") with Logger_B
    person2.sayHi()
  }
}

trait MyLogger {
  def log(msg:String) {}
}

trait Logger_A extends MyLogger {
  override def log(msg: String): Unit = {
    println("test: " + msg)
  }
}

trait Logger_B extends MyLogger {
  override def log(msg: String): Unit = {
    println("log: " + msg)
  }
}

class Person123(val name:String) extends Logger_A {
  def sayHi(): Unit = {
    println("Hi, I'm " + name)
    log("sayHi is invoked")
  }
}
```


### `Trait`调用链

`Scala`中支持让类继承多个`Trait`后, 依次调用多个`Trait`中的同一个方法, 只要让多个`Trait`在同一个方法中, 最后都执行`super.方法`即可  
类中调用多个`Trait`都有的这个方法时, 首先会从最右边的`Trait`的方法开始执行, 然后一次往左执行, 形成一个调用链  
这种特性非常强大, 其实就相当于责任模式中的责任链模式的一种具体实现依赖 


```scala
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
```


# `Scala`的模式匹配

`Scala`有一个十分强大的模式匹配机制, 可以应用到很多场景: 如`switch`语句, 类型检查等  
并且`Scala`还提供了样例类, 对模式匹配进行了优化, 可以快速进行匹配

## 匹配字符串

```scala
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

```

## 类型匹配

```scala
import scala.util.Random

object CaseDemo02 {
  def main(args: Array[String]): Unit = {
    val arr = Array("hello", 1, 2.0, -1.0, CaseDemo)
    val v = arr(Random.nextInt(arr.length))
    println(v)
    v match {
      case x: Int => println("Int: " + x)
      case x: Double if (x >= 0) => println("Double: " + x)
      case x: String => println("String: " + x)
      case _ => throw new Exception("not match exception")
    }
  }
}

case class CaseDemo()

```

> `case y: Double if (y >= 0) => ....`
> 模式匹配的时候可以添加守卫条件. 如不符合守卫条件, 将掉入`case _`中


## 匹配数组, 元组, 集合

```scala
object CaseDemo03 {
  def main(args: Array[String]): Unit = {
    val arr = Array(1, 3, 5)
    arr match {
      case Array(1, x, y) => println(x + " " + y)
      case Array(0) => println("only 0")
      case Array(0, _*) => println("0 ...")
      case _ => println("something else")
    }

    val lst = List(3, -1)
    lst match {
      case 0 :: Nil => println("only 0")
      case x :: y :: Nil => println(s"x: $x, y: $y")
      case 0 :: tail => println(s"0 ... ")
      case _ => println("something else")
    }

    val tup = (2,3, 7)
    tup match {
      case (1, x, y) => println(s"1, $x, $y")
      case (_, z, 5) => println(z)
      case _ => println("something else")
    }

  }
}
```


> 在`Scala`中, 列表要么为空(`Nil`表示空列表), 要么是一个`head`元素加上一个`tail`列表
> `9::List(5,2)`, `::`操作符是将给定的头和为创建一个新的列表
> `::`操作符是右结合的, 如`9::5::2::Nil`相当于`9::(5::(2::Nil))`

## 样例类

在`Scala`中样例类是一种特殊的类, 可以用于模式匹配

* `case class`是多例的, 后面要更构造参数
* `case object`是单例的

```scala
import scala.util.Random

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

```

当一个类被声明为`case class`的时候, `scala`会帮助我们做下面几件事情
1. 构造器中的参数如果不被声明为`var`的话, 它默认的话是`val`类型的, 但是一般不推荐将构造器中的参数声明为`var`
2. 自动创建伴生对象, 同时在里面我们实现子`apply`方法, 使得我们在使用的手可以不直接显示得`new`对象
3. 伴生对象中同样会帮我们实现`unapply`方法, 从而可以将`case class`应用于模式匹配. `apply`方法接收参数返回对象, `unapply`方法接收对象返回参数
4. 实现自己的`toString`, `hashCode`, `copy`, `equals`方法
5. `case class`主构造函数里面没有修饰符, 默认的是`val`  

除此之外, `case class`与其它普通的`scala`类没有区别

```scala
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
```

## `Option`类型

在`Scala`中`Option`类型样例用来表示可能存在也可能不存在的值(`Option`的子类有`Some`和`None`). `Some`包装了某个值, `None`表示没有值

```scala
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

```

## 偏函数

被抱在花括号内没有match的一组case语句就是一个偏函数, 它是`PartialFunction[A,B]`的一个实例, `A`代表参数类型, `B`代表返回值类型. 常用作输入模式匹配

```scala
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
```

总结: 
__偏函数就是用来做模式匹配的__