package com.examplechat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpChannel;

public class ChatClient {
	
	public static void main(String[] args) throws InterruptedException, IOException  {
      new ChatClient("localhost", 8000).run();

	}
	
	private final String host;
	private final int port;
	

	public ChatClient(String host, int port) {
		super();
		this.host = host;
		this.port = port;
	}


	public void run() throws InterruptedException, IOException {
  EventLoopGroup group = new NioEventLoopGroup();
  
  try {
	  Bootstrap bootstrap = new Bootstrap()
			  .group(group)
			  .channel(NioSctpChannel.class)
			  .handler(new ChatClientInitilizer()); 
	  
	  Channel channel = bootstrap.connect(host, port).sync().channel(); 
	  BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	 while(true) {
		 channel.write(in.readLine()+ "\r\n");
		 
	 }
		
	}
  finally {
	  group.shutdownGracefully();
  }
  
  }
  
	}
	
	

