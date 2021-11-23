package ip.Socket_UDP;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class UdpClient {
    public static void main(String[] args) throws IOException {
        //不需要连接服务器, 其实也就没有客户端和服务器端的区别
        // 建立一个Socket
        DatagramSocket datagramSocket = new DatagramSocket();
        //建个包
        String msg = "您好啊， 服务器！";
        InetAddress localhost = InetAddress.getByName("localhost");
        int port = 9090;
        //建包 参数（数据， 数据的起止长度， 发送给谁）
        DatagramPacket datagramPacket = new DatagramPacket(msg.getBytes(), 0, msg.getBytes().length, localhost, port);
        //发送包
        datagramSocket.send(datagramPacket);
        //关闭资源
        datagramSocket.close();


    }
}
