package ip.UDP_chat;

import java.net.SocketException;

public class talkStudent {
    public static void main(String[] args) throws SocketException {
        //开启两个线程
        new Thread(new thread_chat_sender(7777, "localhost", 9999)).start();
        new Thread(new thread_chat_receive(8888, "teacher")).start();
    }
}
