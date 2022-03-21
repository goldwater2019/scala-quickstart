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
