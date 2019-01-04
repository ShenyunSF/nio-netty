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
		//��ӡ��Ϣ����Ҫע����ǿͻ��˷���������Ϣ���С�$_���ָ��������ǽ��յ���Ϣ��û������ָ�����˵��
		//DelimiterBaseFrameDecoder�Զ�����Ϣ���н��룬��ȥ���ˡ�$_��. 
		System.out.println("This is receive :["+body+"]");
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.close();
	}
}
