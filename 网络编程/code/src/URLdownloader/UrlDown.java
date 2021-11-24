package URLdownloader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UrlDown {
    public static void main(String[] args) throws IOException {
        //下载地址
        URL url = new URL("https://img-blog.csdnimg.cn/20200719000204619.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2hld2Vpd2VpNTIw,size_16,color_FFFFFF,t_70");

        //连接这个资源 HTTP
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        InputStream inputStream = urlConnection.getInputStream();
        FileOutputStream fos = new FileOutputStream("./src/URLdownloader/test.jpg");
        byte[] buffer = new byte[1024];
        int len;
        while((len = inputStream.read(buffer)) > 0) {
            fos.write(buffer, 0, len);
        }
        fos.close();
        inputStream.close();
        urlConnection.disconnect();//断开连接
    }
}
