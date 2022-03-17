
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