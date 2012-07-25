package com.bluedavy.rpc;

import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import code.google.nfs.rpc.Codecs;
import code.google.nfs.rpc.netty.client.NettyClientInvocationHandler;
import code.google.nfs.rpc.protocol.RPCProtocol;

public class ClientTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		Map<String, Integer> methodTimeouts = new HashMap<String, Integer>();
        // so u can specialize some method timeout
        methodTimeouts.put("*", 1000);
        List<InetSocketAddress> servers = new ArrayList<InetSocketAddress>();
        servers.add(new InetSocketAddress("172.17.2.71", 18888));
        // Protocol also support Protobuf & Java,if u use Protobuf,u need call PBDecoder.addMessage first.
        HelloWorldService service = (HelloWorldService) Proxy.newProxyInstance(
        						ClientTest.class.getClassLoader(),
                                new Class<?>[] { HelloWorldService.class },
                                new NettyClientInvocationHandler(servers, 10, 1000, "helloworld", methodTimeouts, Codecs.HESSIAN_CODEC, RPCProtocol.TYPE));
        
        String result = service.sayHello("hello");
        
        System.out.println(result);
	}

}
