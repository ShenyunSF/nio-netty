package nio.base.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class NioBaseDemo1 {
	public void server(int port) throws IOException 
	{
		// 创建通道
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		// 设置为非阻塞的
		serverChannel.configureBlocking(false);

		// 开启服务端
		ServerSocket socket = serverChannel.socket();

		// 服务端帮点端口
		InetSocketAddress address = new InetSocketAddress(port);
		socket.bind(address);

		// 开启多路复用器
		Selector selector = Selector.open();

		/*
		 * 将通道注册到多路复用器上. 第二个参数，这是一个”interest集合“，意思是在通过Selector监听
		 * Channel时对什么事件感兴趣.可以监听四种不同类型的事件：Connect,Accept,Read,Write
		 * 通道触发了一个事件意思是该事件已经就绪。
		 * 所以，某个channel成功连接到另一个服务器称为”连接就绪“。
		 * 一个server socket channel准好号接收新进入的连接称为”接收就绪。
		 * 一个有数据可读的通道可以说是”读就绪“。
		 * 等待写数据的通道可以说是”写就绪“。
		 * 
		 */
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);//只要ServerSocketChannel及SocketChannel向Selector注册了特定的事件，Selector就会监控这些事件是否发生。
		
		ByteBuffer.wrap(array);
	}
}
