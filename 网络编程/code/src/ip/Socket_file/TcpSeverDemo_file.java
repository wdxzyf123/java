package ip.Socket_file;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpSeverDemo_file {
    public static void main(String[] args) throws IOException {
        System.out.println(System.getProperty("user.dir"));
        //创建一个socket服务
        ServerSocket serverSocket = new ServerSocket(9999);
        //阻塞监听客户端请求
        Socket socket = serverSocket.accept();
        //获取输入流

        InputStream is = socket.getInputStream();
        //文件输出
        FileOutputStream fos = new FileOutputStream(new File("./src/ip/Socket_file//receive.jpg"));
        byte[] buffer = new byte[1024];
        int len;
        while((len=is.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
        }

        //通知客户端我接受完毕
        OutputStream os = socket.getOutputStream();
        os.write("receive finished".getBytes());
        //关闭资源
        os.close();
        fos.close();
        is.close();
        socket.close();
    }
}
