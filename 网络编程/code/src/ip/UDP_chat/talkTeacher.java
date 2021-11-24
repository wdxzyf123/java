package ip.UDP_chat;

import java.net.SocketException;

public class talkTeacher {
    public static void main(String[] args) throws SocketException {
        //开启两个线程
        new Thread(new thread_chat_sender(5555, "localhost", 8888)).start();
        new Thread(new thread_chat_receive(9999, "student")).start();
    }
}
