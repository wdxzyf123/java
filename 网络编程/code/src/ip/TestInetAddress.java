package ip;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestInetAddress {
    public static void main(String[] args) {
        try {
            //查询本机的IP地址 静态方法 不能new
            InetAddress ip1 = InetAddress.getByName("127.0.0.1");
            System.out.println(ip1);
            InetAddress ip2 = InetAddress.getByName("localhost");
            System.out.println(ip2);
            InetAddress ip3 = InetAddress.getLocalHost();
            System.out.println(ip3);

            //查询网站的IP地址
            InetAddress ip4 = InetAddress.getByName("www.baidu.com");
            System.out.println(ip4);

            //常用的方法
            System.out.println(ip4.getAddress());
            System.out.println(ip4.getCanonicalHostName());
            System.out.println(ip4.getHostAddress());//标准IP地址
            System.out.println(ip4.getHostName()); //域名
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
