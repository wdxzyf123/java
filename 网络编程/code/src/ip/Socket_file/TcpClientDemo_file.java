package ip.Socket_file;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClientDemo_file {
    public static void main(String[] args) throws IOException {
        //创建一个socket连接
        Socket socket = new Socket(InetAddress.getByName("localhost"), 9999);
        //创建一个输出流 ,用来写
        OutputStream os = socket.getOutputStream();
        //输入流 读取文件 用来读
        FileInputStream fis = new FileInputStream(new File("./src/ip/Socket_file/test.jpg"));
        //写出文件
        byte[] buffer = new byte[1024];
        int len;
        while((len=fis.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }
        //通知服务器我传输完毕
        socket.shutdownOutput();
        //先确定服务器接收完毕再断开，等待服务器传来接收结束消息
        InputStream inputStream = socket.getInputStream();
        //String byte[]
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer2 = new byte[1024];
        int len2;
        while((len2=inputStream.read(buffer2)) != -1) {
            baos.write(buffer2, 0, len2);
        }
        System.out.println(baos.toString());
//        System.out.println(new String(baos.toByteArray()));


        //关闭资源
        baos.close();
        inputStream.close();
        fis.close();
        os.close();
        socket.close();
    }
}
