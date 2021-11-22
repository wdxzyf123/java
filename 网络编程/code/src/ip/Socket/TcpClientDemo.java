package ip.Socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class TcpClientDemo {
    public static void main(String[] args) {
        Socket socket = null;
        OutputStream os = null;
        try {
            //1 获取服务器的地址与端口号
            InetAddress severIP = InetAddress.getByName("127.0.0.1");
            int port = 9999;
            //2.创建一个socket连接
            socket = new Socket(severIP, port);
            //3.发送消息IO流
            os = socket.getOutputStream();
            os.write("I Love U! 亲爱的".getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
