package com.examplechat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ChatServerHandler extends SimpleChannelInboundHandler<String> {
	
	private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception{
		Channel incoimg = ctx.channel();
		for(Channel channel: channels) {
			channel.write("[SERVER] -" + incoimg.remoteAddress() +"has Joined! \n" );
			}
		channels.add(ctx.channel());
	}
	
	
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception{
		Channel incoimg = ctx.channel();
		for(Channel channel: channels) {
			channel.write("[SERVER] -" + incoimg.remoteAddress() +"has left! \n" );
			}
		channels.remove(ctx.channel());
	}
	
	@Override
	public void messageReceived(ChannelHandlerContext arg0, String message) throws Exception{
		
		Channel incoming = arg0.channel();
		for(Channel channel: channels) {
			
			if(channel != incoming) {
				
				channel.write("[" + incoming.remoteAddress() + "] " + message + "\n");
			}
			
		}
		
		
	}

}
