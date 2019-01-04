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
		// ����ͨ��
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		// ����Ϊ��������
		serverChannel.configureBlocking(false);

		// ���������
		ServerSocket socket = serverChannel.socket();

		// ����˰��˿�
		InetSocketAddress address = new InetSocketAddress(port);
		socket.bind(address);

		// ������·������
		Selector selector = Selector.open();

		/*
		 * ��ͨ��ע�ᵽ��·��������. �ڶ�������������һ����interest���ϡ�����˼����ͨ��Selector����
		 * Channelʱ��ʲô�¼�����Ȥ.���Լ������ֲ�ͬ���͵��¼���Connect,Accept,Read,Write
		 * ͨ��������һ���¼���˼�Ǹ��¼��Ѿ�������
		 * ���ԣ�ĳ��channel�ɹ����ӵ���һ����������Ϊ�����Ӿ�������
		 * һ��server socket channel׼�úŽ����½�������ӳ�Ϊ�����վ�����
		 * һ�������ݿɶ���ͨ������˵�ǡ�����������
		 * �ȴ�д���ݵ�ͨ������˵�ǡ�д��������
		 * 
		 */
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);//ֻҪServerSocketChannel��SocketChannel��Selectorע�����ض����¼���Selector�ͻ�����Щ�¼��Ƿ�����
		
		ByteBuffer.wrap(array);
	}
}
