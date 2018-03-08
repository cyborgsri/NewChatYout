package com.examplechat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ChatClientHandler extends SimpleChannelInboundHandler<String> {
	
	@Override
	public void messageReceived(ChannelHandlerContext arg0, String arg1) {
		System.out.println(arg1);
	}

}
