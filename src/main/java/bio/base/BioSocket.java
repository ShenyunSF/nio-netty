package bio.base;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class BioSocket {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("127.0.0.1", 8085);
		
		String str=" is a good start of netty:salkdhnalkdakldjaslkdsalkdjsalkdhsalkddhsalkjdhsal%";
		PrintWriter out=null;
		try {
			out = new PrintWriter(socket.getOutputStream(),true);
			/*out.println(str);
			out.println("");*/
			for(int i=0;i<13;i++)
			{
				out.println(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			//��Ϊ�������ӣ�����������Ժ�û�и��ཻ�������Կ��Թر�
            if(out!=null){
                out.close();
            }
		}
		
	
	}
}
