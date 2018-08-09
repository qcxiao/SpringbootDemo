package accumulate.http;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.Map.Entry;

public class HttpRequest {

    private CloseableHttpClient httpclient = HttpClients.createDefault();

    static CookieStore cookieStore = null;
    static HttpClientContext context = null;

    /**
     * 发送 post请求访问本地应用并根据传递参数不同返回不同结果
     *
     * @return
     */
    public String post(String url, JSONObject json) {
        String result = null;
        // 创建默认的httpClient实例.
        httpclient = HttpClients.createDefault();
        HttpResponse res = null;
        // 创建httppost
        HttpPost httppost = new HttpPost(url);

        try {
            StringEntity s = new StringEntity(json.toString());
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");// 发送json数据需要设置contentType
            httppost.setEntity(s);

            res = httpclient.execute(httppost);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //HttpEntity entity = res.getEntity();
                result = EntityUtils.toString(res.getEntity());// 返回json格式：
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setContext();
        return result;
    }

    public static void setContext() {
        System.out.println("----setContext");
        context = HttpClientContext.create();
        Registry<CookieSpecProvider> registry = RegistryBuilder.<CookieSpecProvider>create()
                .register(CookieSpecs.BEST_MATCH, new BestMatchSpecFactory())
                .register(CookieSpecs.BROWSER_COMPATIBILITY, new BrowserCompatSpecFactory()).build();
        context.setCookieSpecRegistry(registry);
        context.setCookieStore(cookieStore);
    }

    /**
     * 发送 get请求访问本地应用并根据传递参数不同返回不同结果
     *
     * @return
     */
    public String get(String url) {
        String result = null;
        // 创建默认的httpClient实例.
        // 创建httpget
        HttpGet httpget = new HttpGet(url);

        try {
            HttpResponse res = httpclient.execute(httpget);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(res.getEntity());// 返回json格式：
            }
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        setContext();
        return result;
    }

    @SuppressWarnings("rawtypes")
    public static List<NameValuePair> getParam(Map parameterMap) {
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        Iterator it = parameterMap.entrySet().iterator();
        while (it.hasNext()) {
            Entry parmEntry = (Entry) it.next();
            param.add(new BasicNameValuePair((String) parmEntry.getKey(), (String) parmEntry.getValue()));
        }
        return param;
    }

    @SuppressWarnings("rawtypes")
    public String defaultpost(String url, Map parameterMap) {
        System.out.println("----testLogin");
        String result = null;
        HttpResponse res = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            UrlEncodedFormEntity postEntity = new UrlEncodedFormEntity(getParam(parameterMap), "UTF-8");
            httpPost.setEntity(postEntity);
            res = httpclient.execute(httpPost);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //HttpEntity entity = res.getEntity();
                result = EntityUtils.toString(res.getEntity());// 返回json格式：
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setContext();
        return result;
    }


    public void closehttpclient() {
        try {
            httpclient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String argv[]) {
        //String loginUrl = "http://localhost:8081/etermAPI/login/in";
        String url = "http://ats.alibaba-inc.com/toutiao/addtask/RunTaskWithDetail.json";
        Map<String, Object> body = new HashMap<String, Object>();
        HttpRequest http = new HttpRequest();

        //String result = http.defaultpost(loginUrl, body);
        //System.out.println(result);

        //body.put("token", result);
        /*body.put("urls", "[   //url 列表，可以一次提交多个\n" +
                "        {\n" +
                "            \"url\":\"http://market.m.taobao.com/apps/market/jiyoujia/index.html?wh_weex=true\",   //测试url，不可空\n" +
                "            \"testType\": \"2\",   //测试类型，uiTest评测为2，安卓适配测试为1，安卓weex性能测试为8，iOS适配测试：32， iOS weex性能测试：128 。如果测试多项相加即可，如 适配＋weex性能为：1+8＝9 ，传9即可，不可空\n" +
                "            \"uiTestCase\": \"274,265,287\",   // 执行用例指定，使用逗号隔开\n" +
                "            \"isAutoRun\":\"true\",  //自动运行\n" +
                "            \"mobileMode\":\"Amazon Kindle Fire HDX\",   //在 浏览器，手机等测试\n" +
                "            \"waitSenconds\":\"100\",  //延迟时间\n" +
                "            \"host\":\"10.101.73.189  g.alicdn.com\"    //绑定\n" +
                "        }\n" +
                "    ]");
        body.put("businessId","10019");
        body.put("operator","144767");
        System.out.println(http.defaultpost(url, body));*/

        JSONObject params = new JSONObject();
        params.put("businessId", "10019");
        params.put("operator", "144767");
        JSONArray array = new JSONArray();
        array.add("{\n" +
                "            \"url\":\"http://market.m.taobao.com/apps/market/jiyoujia/index.html?wh_weex=true\",   //测试url，不可空\n" +
                "            \"testType\": \"2\",   //测试类型，uiTest评测为2，安卓适配测试为1，安卓weex性能测试为8，iOS适配测试：32， iOS weex性能测试：128 。如果测试多项相加即可，如 适配＋weex性能为：1+8＝9 ，传9即可，不可空\n" +
                "            \"uiTestCase\": \"274,265,287\",   // 执行用例指定，使用逗号隔开\n" +
                "            \"isAutoRun\":\"true\",  //自动运行\n" +
                "            \"mobileMode\":\"Amazon Kindle Fire HDX\",   //在 浏览器，手机等测试\n" +
                "            \"waitSenconds\":\"100\",  //延迟时间\n" +
                "            \"host\":\"10.101.73.189  g.alicdn.com\"    //绑定\n" +
                "        }");
        params.put("urls",array);
        System.out.println(http.post(url, params));

        http.closehttpclient();
    }

}