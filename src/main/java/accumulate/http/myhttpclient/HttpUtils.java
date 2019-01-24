package accumulate.http.myhttpclient;

import org.apache.http.client.methods.HttpGet;

/**
 * @Author: yaodao
 * @Date: 2019/1/10 21:27
 */
public class HttpUtils {
    public static void get(String url){
        HttpGet httpget = new HttpGet(url);
        System.out.println(httpget.getClass());
        System.out.println(httpget.getURI());
    }

    public static void main(String[] args) {
        get("www.baidu.com");
    }
}
