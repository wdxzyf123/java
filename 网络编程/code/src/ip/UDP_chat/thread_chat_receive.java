package ip.UDP_chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class thread_chat_receive implements Runnable {
    DatagramSocket socket = null;
    DatagramPacket packet = null;
    private int port;
    private String msgFrom;

    public thread_chat_receive(int port, String msgFrom) throws SocketException {
        this.port = port;
        this.msgFrom = msgFrom;
        socket = new DatagramSocket(port);
    }

    @Override
    public void run() {
        while(true) {
            try {
                //准备接受packet
                byte[] container = new byte[1024];
                packet = new DatagramPacket(container, 0, container.length);
                socket.receive(packet);//阻塞式接受
                byte[] data = packet.getData();
                String receiveData = new String(data, 0 , data.length);
                System.out.println(this. msgFrom + ":" + receiveData.trim());
//                System.out.println(receiveData);
                if (receiveData.equals("bye")) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        socket.close();
    }
}
