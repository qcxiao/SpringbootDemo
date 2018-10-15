package utils.http;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.DefaultCookieSpecProvider;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * http实现免登的方法
 *
 * @Author: yaodao
 * @Date: 2018/8/10 16:45
 */
public class HttpClientManager {

    private CloseableHttpClient httpClient;
    private HttpContext httpClientContext;
    private CookieStore cookieStore;

    /**
     * 初始化
     */
    public HttpClientManager() {
        this.cookieStore = new BasicCookieStore();
        this.httpClient = getHttpClient(cookieStore);
        this.httpClientContext = HttpClientContext.create();
        this.httpClientContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
    }

    private CloseableHttpClient getHttpClient(CookieStore cookieStore) {
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            TrustStrategy anyTrustStrategy = new TrustStrategy() {
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    return true;
                }
            };
            SSLContext sslContext = SSLContextBuilder.create().useProtocol("TLS").loadTrustMaterial(trustStore, anyTrustStrategy).build();
            ConnectionSocketFactory plainSF = PlainConnectionSocketFactory.getSocketFactory();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
            //信任https
            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", plainSF)
                    .register("https", sslsf)
                    .build();
            PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(registry);
            //設置cookie策略
            Registry<CookieSpecProvider> cookieSpecRegistry = RegistryBuilder.
                    <CookieSpecProvider>create().
                    register(CookieSpecs.DEFAULT, new DefaultCookieSpecProvider()).
                    build();
            //设置跳转策略
            RequestConfig reqConfig = RequestConfig.copy(RequestConfig.DEFAULT).
                    setRedirectsEnabled(true).
                    setRelativeRedirectsAllowed(true).
                    setCircularRedirectsAllowed(true).
                    build();
            return HttpClientBuilder.create().
                    setDefaultCookieSpecRegistry(cookieSpecRegistry).
                    setConnectionManager(connManager).
                    setDefaultCookieStore(cookieStore).
                    setDefaultRequestConfig(reqConfig).
                    build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 发送get请求
     */
    public String sendGetRequest(String targetUrl) throws Exception {
        try {
            HttpGet httpGet = new HttpGet(targetUrl);
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet, httpClientContext);
            printResponseCookie(cookieStore);
            return EntityUtils.toString(httpResponse.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 发送post请求
     */
    public String sendPostRequest(String targetUrl, Map<String, String> params) throws Exception {
        HttpPost httpost = initHttpPost(targetUrl, params);
        try {
            HttpResponse response = httpClient.execute(httpost, httpClientContext);
            return EntityUtils.toString(response.getEntity());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpClient.close();
        }
        return null;
    }

    /**
     * 初始化post入参
     */
    public static HttpPost initHttpPost(String loginUrl, Map<String, String> params) {
        HttpPost httpost = new HttpPost(loginUrl);
        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            nvps.add(new BasicNameValuePair(key, params.get(key)));
        }
        try {
            httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return httpost;
    }

    private ResponseHandler<String> getResponseHandler() {
        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
            public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
                int status = response.getStatusLine().getStatusCode();
                if (status >= HttpStatus.SC_OK && status < HttpStatus.SC_MULTIPLE_CHOICES) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            }
        };
        return responseHandler;
    }

    /**
     * 打印response cookie
     */
    public static void printResponseCookie(CookieStore cookieStore) throws Exception {
        System.out.println(net.sf.json.JSONObject.fromObject(cookieStore));
        List<Cookie> cookies = cookieStore.getCookies();
        System.out.println("---------------------------cookies start---------------------------");
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName() + " = " + cookie.getValue());
        }
        System.out.println("---------------------------cookies end---------------------------");
    }

    /**
     * 测试调用
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        HttpClientManager manager = new HttpClientManager();
        // 免登种登录态，注意此处不会302
        manager.sendGetRequest(
                "https://www.xxxx.com/tokenLoginForSid.do?id=param0&site=param1&ssoKey=param2");
        // 访问需要登录态的业务页
        String response = manager.sendGetRequest(
                "https://www.xxxx.com/api/style/getCode");
        System.out.println(response);
    }

}

