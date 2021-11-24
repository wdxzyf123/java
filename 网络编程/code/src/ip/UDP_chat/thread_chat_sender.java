package ip.UDP_chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class thread_chat_sender implements Runnable{
    DatagramSocket socket = null;
    DatagramPacket packet = null;
    BufferedReader reader = null;

    private int fromPort;
    private String toIP;
    private int toPort;

    public thread_chat_sender(int fromPort, String toIP, int toPort) {
        this.fromPort = fromPort;
        this.toIP = toIP;
        this.toPort = toPort;
        try {
            //控制台输入数据

            socket = new DatagramSocket(fromPort);
            reader = new BufferedReader(new InputStreamReader(System.in));
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        while(true) {
            try {
                String data = reader.readLine();
                byte[] datas = data.getBytes();
                packet = new DatagramPacket(datas, 0, datas.length, new InetSocketAddress(this.toIP, this.toPort));
                socket.send(packet);
                if(data.equals("bye")) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        socket.close();
    }
}
