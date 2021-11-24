package URLdownloader;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class URLDown_Thread implements Runnable{
    private String url;
    private String name;

    public URLDown_Thread(String url, String name) {
        this.url = url;
        this.name = name;
    }
    //下载器
    class WebDownloader {
        public void downloader(String url, String name) throws IOException {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        }
    }
    @Override
    public void run() {
        try {
            WebDownloader webDownloader = new WebDownloader();
            webDownloader.downloader(url, name);
            System.out.println("下载文件" + name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(new URLDown_Thread("https://m801.music.126.net/20211124132518/8744a140e98346ee3dbd40da72c577b1/jdyyaac/obj/w5rDlsOJwrLDjj7CmsOj/11228998505/933a/3b69/d0c6/6d364864e593a826f306490a6e48f1be.m4a","yinyu.m4a")).start();
        new Thread(new URLDown_Thread("","")).start();
        new Thread(new URLDown_Thread("","")).start();

    }
}
