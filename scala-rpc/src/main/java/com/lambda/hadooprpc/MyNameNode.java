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
