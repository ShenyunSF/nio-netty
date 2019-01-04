package netty.echo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class DiscardServer {

	private int port;

	public DiscardServer(int port) {
		this.port = port;
	}

	public void run() throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap(); // (2)
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class) // (3)
					.childHandler(new ChannelInitializer<SocketChannel>() { // (4)
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							/*ch.pipeline().addLast(new FixedLengthFrameDecoder(1024));*/
							/*ch.pipeline().addLast(new FixedLengthFrameDecoder(2)); */
							//下面这两句就是配置以“$_”为分隔符的解码器，怎么样简单吧 
							/*ByteBuf delimiter = Unpooled.copiedBuffer("%".getBytes()); 
							//1024 是单条消息的最大长度，如果达到该长度后仍然没有找到分隔符就会抛出异常，这点大家要特别注意。delimiter就是我们的分隔符。
							ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));*/
	                        ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
							ch.pipeline().addLast(new StringDecoder()); 
							ch.pipeline().addLast(new ServerHandler());
						}
					}).option(ChannelOption.SO_BACKLOG, 128) // (5)
					.childOption(ChannelOption.SO_KEEPALIVE, true); // (6)

			// Bind and start to accept incoming connections.
			ChannelFuture f = b.bind(port).sync(); // (7)

			// Wait until the server socket is closed.
			// In this example, this does not happen, but you can do that to gracefully
			// shut down your server.
			f.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		int port =8085;
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		}

		new DiscardServer(port).run();
	}

}
