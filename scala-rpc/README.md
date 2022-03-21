# `RPC`远程调用概念

## `RPC`概念

`RPC(Remode Prodedure Call)`远程过程调用, 它是一种通过网络从远程计算机程序上请求服务, 而不需要了解底层网络技术的协议  
RPC协议中假定某些传输协议的存在, 如`TCP`和`UDP`, 为通信程序之间携带数据. 在`OSI`网络通信模型中, `RPC`跨越了传输层和应用层. `RPC`是的开发包括网络分布式多程序在内的应用程序更加容易.  

`OSI`七层路网络协议
* 第七层: 应用程. 定义了用于在网络中进行通信和传输数据的接口
* 第六层: 表示层. 定义不同的系统中数据的传输格式, 编码和解码规范等
* 第五层: 会话层. 管理用户的会话, 控制用户间逻辑连接的建立和终端
* 第四层: 传输层. 管理者网络中端到端的数据传输
* 第三层: 网络层. 定义网络设备之间如何传输数据.
* 第二层: 链路层. 讲上面的网络层的数据包封装成数据帧, 便于物理层传输
* 第一层: 物理层. 这一层主要就是传输这些二进制数据
 
> 实际应用过程中, 五层协议结构里面没有表示层和会话层. 应该说它们和应用层合并了. 我们应该将重点放在`应用层`和`传输层`这两个层面. 因为`HTTP`是应用层协议; `TCP`是传输层协议.


`RPC`采用C/S模式: 请求程序就是一个客户机, 而服务提供程序就是一个服务器.  
首先, 客户机调用进程发送一个有进程参数的调用信息到服务进程, 然后等待应答信息.  
在服务器端, 进程保持睡眠状态直到调用信息到达为止; 当一个调动信息到达, 服务器获得进程参数, 计算结构, 发送答复信息, 然后等待下一个调用信息  
最后, 客户端调用进程接收接收答复信息, 获得进程结果, 然后调用执行继续进行  

通俗的讲: `RPC`是指远程过程调用, 也就是说两台服务器A,B, 一个应用部署在A服务器, 想要调用B服务器提供的函数和方法, 由于不在一个内存空间, 不能直接调用, 需要通过网络来表达调用的予以和传达调用的数据. __也可以理解成不同进程之间的服务调用__  

比如一个方法是如下定义的:
```java
class Employee {
    Employee getEmployeeByName(String fullName);
}
```
并且被附属在B服务器上, 那么A服务器想要调用这个服务, 那么:
1. 要解决通讯问题, 主要是通过在客户端和服务端之间建立`TCP`连接, 远程过程调用的所有交换数据都在这个连接里面传输. 连接也可以按需连接, 调用结束后就断掉, 也可以是长连接, 多个远程过程调用共享同一个连接.
2. 要解决寻址的问题, 也就是说, A服务器上的用用怎么告诉底层的`RPC`框架, 如何连接到B服务器(如主机和IP地址)已经特定的端口, 方法的名称是什么, 这样才能完成调用
3. 当A服务器上的用用发起远程过程调用时, 方法的参数需要通过底层的网络协议如TCP传递到B服务器, 由于网络协议时基于二进制的, 内存中的参数的值要序列化成二进制的形式, 也就是序列化(Serialize)或编组(marshal), 通过寻址和传输将序列化的二进制发给B服务器
4. B服务器收到请求后, 需要对参数进行反序列化(序列化的逆操作), 恢复为内存中的表达方式, 然后找到对应的方法(寻址的一部分)进行本地调用, 然后得到返回值.
5. 返回值还要发送回给服务器A上的应用, 也要经过序列化的方式发送, 服务器A接到后, 再反序列化, 恢复为内存中的表达方式, 交给A服务器上的应用.  

## 为什么要有`RPC` 

RPC框架的职责是: 让调用方法感觉就像调用本地函数一样调用远端函数, 让服务提供方感觉就像实现一个本地函数一样来实现服务, 并且屏蔽编程语言的差异性  

RPC的主要功能目标是让构建分布式计算(应用)更容易, 在提供强大的远程调用能力时不损失本地调用的语义简洁性. 为实现该目标, RPC框架需提供一种通过调用机制让使用者不必显式得区分本地调用和远程调用  

简单地讲, 对于客户端A来说, 调用远程服务器B上的服务, 就跟调用A上的自身服务一样. 因为在客户端A上来说, 会生成一个服务器B的代理.  

## Java中流行的`RPC`框架

### RMI(远程方法调用)

Java自带的远程方法调用工具, 不过有一定的局限性, 毕竟是`Java`语言最开始时的设计, 后来很多框架的原理都基于`RMI`

### Hessian(基于HTTP的远程方法调用)
基于HTTP协议传输, 在性能方面还不够完美, 负载均衡和失效转移依赖于应用的负载均衡器, Hessian的使用则与RMI类似, 区别在于淡化了Registry的角色, 通过显示得地址和调用, 利用HessianProxyFactory根据配置得地址create一个代理对象, 另外还要引入Hessian得Jar包  

### Dubbo(阿里开源的基于TCP的RPC框架)
基于Netty的高性能RPC框架, 是阿里巴巴开源的, 大致的原理如下:  
> TODO 画图


> 其它的比较流行的RPC技术还有`FaceBook`的`Thrift`, `Google`的`GRPC`, 基于`Netty`的`HadoopRPC`, 基于`Actor`的`Akka`等  

# `Hadoop RPC`

## `Hadoop RPC`概述

同其它RPC序列化框架一样, `Hadoop RPC`分为四个部分:
1. 序列化层: `Client`与`Server`端通信传递的信息采用了`Hadoop`里提供的序列化类和自定义的`Writable`类型
2. 函数调用层: `Hadoop RPC`通过动态代理以及Java反射实现函数调用
3. 网络传输层: `Hadoop RPC`采用了基于`TCP/IP`的`socket`机制
4. 服务端框架层: `RPC Server`利用`Java NIO`以及采用了事件驱动的`I/O`模型, 提高`RPC Server`的并发处理能力

`Hadoop RPC`在整个`Hadoop`中应用非常广泛, `Client`, `DataNode`, `NameNode`之间的通讯全靠它.  

例如: 我们平时操作`HDFS`的时候, 使用的是`FileSystem`类, 它的内部有一个`DFSClient`对象, 这个对象负责与`NameNode`打交道. 在运行时, `DFSClient`在本地创建一个`NameNode`的代理, 然后就操作这个代理, 这个代理会通过网络, 远程调用到`NameNode`的方法, 也能返回值.

## `Hadoop RPC`涉及的技术

### 动态代理

动态代理可以提供对另一个对象的访问, 同时隐藏实际对象的具体事实, 代理对象对客户隐藏了实际对象. 目前Java开发包中提供了对动态代理的支持, 但现在只支持对接口的实现.

### 反射

动态加载类

### 序列化

在网络中传输数据必然会涉及到对象的序列化和反序列化

### 非阻塞的异步IO(NIO)

## Hadoop RPC对外提供的接口

`Hadoop RPC`对外主要提供了两种接口, 见类`org.apache.hadoop.ipc.RPC`, 分别是:
1. 构造一个客户端代理对象(该对象实现了某个协议), 用于向服务器发送RPC请求
```java
import org.apache.hadoop.ipc.RPC.*;
public static ProtocolProxy getProxy/watForProxy(...)
```
2. 为某个协议(实际上是Java接口)实例构造一个服务器对象, 用于处理客户端发送的请求
```java
import org.apache.hadoop.ipc.RPC.*;
public static Server RPC.Builder (Configuration).builder()
```

## 使用`Hadoop RPC`构建应用的步骤
1. 定义`RPC`协议
```text
RPC协议是客户端和服务端之间的通信接口, 它定义了服务器端对外提供的服务接口
```
2. 实现`RPC`协议
```text
HadoopRPC协议通常是一个Java接口, 用户需要实现该接口
```
3. 构造和启动`RPC SERVER`
```text
直接使用静态类Builder构造一个RPC Server, 并调用start()启动该Server
```
4. 构造`RPC Client`并发送请求
```text
使用静态方法getProxy构造客户端代理对象, 直接通过代理对象调用远端的方法
```

## 具体实现

### 定义协议

```java
package com.lambda.hadooprpc;

/**
 * RPC协议
 * 要实现VersionedProtocol这个接口： 不同版本的Server和Client之前是不能进行通信的
 */
public interface BusinessProtocol {
    void mkdir(String path);
    void hello(String name);
    String getName(String name);

    long versionID = 549579830L;
}

```

### 实现协议

```java
package com.lambda.hadooprpc.impl;

import com.lambda.hadooprpc.BusinessProtocol;


/**
 * 协议的实现
 */
public class BusinessProtocolImpl implements BusinessProtocol {
    public void mkdir(String path) {
        System.out.println("成功创建了文件夹: " + path);
    }

    public void hello(String name) {
        System.out.println("hello: " + name);
    }

    public String getName(String name) {
        return "HDFS: " + name;
    }
}
```

### 构造和启动RPC SERVER

```java
package com.lambda.hadooprpc;

import com.lambda.hadooprpc.impl.BusinessProtocolImpl;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.conf.Configuration;

import java.io.IOException;


/**
 * 模拟Hadoop构造一个server端
 */
public class MyNameNode {
    public static void main(String[] args) throws IOException {
        RPC.Server server = new RPC.Builder(new Configuration())
                .setProtocol(BusinessProtocol.class)
                .setInstance(new BusinessProtocolImpl())
                .setBindAddress("localhost")
                .setPort(6789)
                .build();

        server.start();
    }
}
``` 

### 构造RPC Client并使用

```java
package com.lambda.hadooprpc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * 构造RPC服务端
 */
public class MyDataNode {
    public static void main(String[] args) throws IOException {
        /**
         * 获取了服务端中暴露了的服务协议的一个代理
         * 客户端通过这个代理可以调用服务端的方法进行逻辑处理
         */
        BusinessProtocol proxy = RPC.getProxy(BusinessProtocol.class, BusinessProtocol.versionID,
                new InetSocketAddress(
                        "localhost",
                        6789
                ),
                new Configuration());


        /**
         * 在客户端调用了服务端的代码执行
         * 真正的代码执行是在服务端
         */
        proxy.hello("hadoop");
        proxy.mkdir("/home/hadoop/apps");
        String proxyName = proxy.getName("MyHDFS");
        System.out.println(proxyName);
    }
}
```