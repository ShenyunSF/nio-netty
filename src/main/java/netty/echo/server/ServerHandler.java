package netty.echo.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;

public class ServerHandler extends SimpleChannelInboundHandler<Object> {

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
		// Close the connection when an exception is raised.
		cause.printStackTrace();
		ctx.close();
	}


	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		String body = (String) msg; 
		//打印消息，需要注意的是客户端发过来的消息，有“$_”分隔符，但是接收到消息并没有这个分隔符，说明
		//DelimiterBaseFrameDecoder自动对消息进行解码，并去掉了“$_”. 
		System.out.println("This is receive :["+body+"]");
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.close();
	}
}
