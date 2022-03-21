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
