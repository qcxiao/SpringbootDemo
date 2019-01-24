package accumulate.http.myhttpclient;

import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @Author: yaodao
 * @Date: 2019/1/7 16:01
 */
public class URIUtils {

    /**
     *
     * @param scheme 协议
     * @param host 主机名称或者域名
     * @param port 端口
     * @param path 资源路径
     * @param params 参数
     * @return
     */
    public static URI create(String scheme, String host, Integer port, String path, Map<String, String> params){
        URIBuilder builder = new URIBuilder().setScheme(scheme).setHost(host).setPort(port).setPath(path);
        Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
        while (iterator.hasNext()){
            builder.setParameter(iterator.next().getKey(), iterator.next().getValue());
        }
        try {
            return builder.build();
        }catch (Exception e){

        }
        return null;
    }

    /**
     * 默认端口为80
     * @param scheme
     * @param host
     * @param path
     * @param params
     * @return
     */
    public static URI create(String scheme, String host, String path, Map<String, String> params){
        URIBuilder builder = new URIBuilder().setScheme(scheme).setHost(host).setPort(80).setPath(path);
        Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
        while (iterator.hasNext()){
            builder.setParameter(iterator.next().getKey(), iterator.next().getValue());
        }
        try {
            return builder.build();
        }catch (Exception e){

        }
        return null;
    }

}
