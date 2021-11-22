package ip.Socket;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;

public class TcpSeverDemo {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        ByteArrayOutputStream baos = null;
        InputStream is = null;
        try {
            // 1 需要创建一个服务器端口
            serverSocket = new ServerSocket(9999);
            //2 等待客户点链接
            socket = serverSocket.accept();
            //3. 读取客户端发送过来的IO流
            is = socket.getInputStream();

            //管道流， os 到 is 的一个中间媒介
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while((len=is.read(buffer)) != -1) {
                baos.write(buffer, 0 , len);
            }
            System.out.println(baos.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭资源
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
