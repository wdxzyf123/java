package ip.UDP_chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpReceive {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(6666);
        while(true) {
            //准备接受packet
            byte[] container = new byte[10];
            DatagramPacket packet = new DatagramPacket(container, 0, container.length);
            socket.receive(packet);//阻塞式接受
            byte[] data = packet.getData();
            String receiveData = new String(data, 0 , data.length);
            System.out.println(receiveData);
            if (receiveData.equals("bye")) {
                break;
            }
        }
        socket.close();
    }
}
