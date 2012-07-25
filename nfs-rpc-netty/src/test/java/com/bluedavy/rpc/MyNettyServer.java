package com.bluedavy.rpc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import code.google.nfs.rpc.NamedThreadFactory;
import code.google.nfs.rpc.netty.server.NettyServer;
import code.google.nfs.rpc.protocol.RPCProtocol;
import code.google.nfs.rpc.server.Server;

public class MyNettyServer{
	public static void main(String[] args) throws Exception{
        Server server = new NettyServer();
        server.registerProcessor(RPCProtocol.TYPE, "helloworld", new HelloWorldComponent());
        ThreadFactory tf = new NamedThreadFactory("BUSINESSTHREADPOOL");
        ExecutorService threadPool = new ThreadPoolExecutor(20, 100,
                          300, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), tf);
        server.start(18888, threadPool);
        
        server.stop();
   }
 }
