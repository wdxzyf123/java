package ip;

import java.net.InetSocketAddress;

public class Tets_SocketAddress {
    public static void main(String[] args) {
        InetSocketAddress socketAddress =  new InetSocketAddress("127.0.0.1", Integer.parseInt("8080"));
        InetSocketAddress socketAddress2 =  new InetSocketAddress("localhost", Integer.parseInt("8080"));
        System.out.println(socketAddress);
        System.out.println(socketAddress2);

        System.out.println(socketAddress2.getAddress());
        System.out.println(socketAddress2.getHostName());// hosts
        System.out.println(socketAddress2.getPort());
    }
}
