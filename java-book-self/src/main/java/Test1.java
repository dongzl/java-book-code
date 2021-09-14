import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author dongzonglei
 * @description
 * @date 2020/12/1 上午10:59
 */
public class Test1 {

    public static void main(String[] args) {
//        OkHttpClient client = new OkHttpClient().newBuilder().readTimeout(120, TimeUnit.SECONDS).build();
//        Request.Builder reqBuilder = new Request.Builder();
//        FormBody.Builder formBodyBuilder = new FormBody.Builder();
//        formBodyBuilder.add("aoData", "[{\"name\":\"sEcho\",\"value\":2},{\"name\":\"iColumns\",\"value\":7},{\"name\":\"sColumns\",\"value\":\",,,,,,\"},{\"name\":\"iDisplayStart\",\"value\":0},{\"name\":\"iDisplayLength\",\"value\":10},{\"name\":\"mDataProp_0\",\"value\":0},{\"name\":\"bSortable_0\",\"value\":false},{\"name\":\"mDataProp_1\",\"value\":1},{\"name\":\"bSortable_1\",\"value\":false},{\"name\":\"mDataProp_2\",\"value\":\"deptNo\"},{\"name\":\"bSortable_2\",\"value\":true},{\"name\":\"mDataProp_3\",\"value\":\"deptName\"},{\"name\":\"bSortable_3\",\"value\":true},{\"name\":\"mDataProp_4\",\"value\":\"sellerNo\"},{\"name\":\"bSortable_4\",\"value\":true},{\"name\":\"mDataProp_5\",\"value\":\"sellerName\"},{\"name\":\"bSortable_5\",\"value\":true},{\"name\":\"mDataProp_6\",\"value\":\"statusStr\"},{\"name\":\"bSortable_6\",\"value\":true},{\"name\":\"iSortCol_0\",\"value\":2},{\"name\":\"sSortDir_0\",\"value\":\"desc\"},{\"name\":\"iSortingCols\",\"value\":1}]");
//        reqBuilder.url("https://b.jclps.com/dept/queryDeptList.do?rand=0.0934128687415825");
//        Map<String, String> headMap = new HashMap<>();
//        headMap.put("cookie", "thor=A7D8FFE6962AEFE2B8674D9752656F0E805064301335FA7E838829760F5134531E5DE221841075B4EC94972A33DF3FF23AF164965D2076DBDC920F615E4E691ABDFDF50191CB9934D39323A680E69DE2477BC88B9A5FB73140DA47EE1AAA4A4344A4C034C135FFA4DC725479C18D612DD6CD77C5EB0E30960AA2AE9D43B6F38FA7DD7437746EFA98A0243C665023484D; pin=dzllikelsw; unick=dzllikelsw");
//        headMap.put(":authority", "b.jclps.com");
//        headMap.put("Accept", "application/json, text/javascript, */*; q=0.01");
//        reqBuilder.headers(Headers.of(headMap));
//        Request request = reqBuilder.post(formBodyBuilder.build()).build();
//        Response response;
//        try {
//            response = client.newCall(request).execute();
//            System.out.println(new String(response.body().bytes(), "utf-8"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        OkHttpClient client = new OkHttpClient().newBuilder().readTimeout(120, TimeUnit.SECONDS).build();
        Request.Builder reqBuilder = new Request.Builder();
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        reqBuilder.url("http://b.jclps.com/monitor/viewKey/test");
        Map<String, String> headMap = new HashMap<>();
        headMap.put("cookie", "thor=A7D8FFE6962AEFE2B8674D9752656F0EE7BFFB59DB4ABBDD2C04E269FCC16FE0C64AF3B9D090D7E3553807B64F3C398BE6C178E1FA61522AEC642D25ADA6BBBBCE7AD87C54A4C918A4CBEFF93906420C47ACCB778D439D9FE5317CBB9E5D9B8F0F0DFB9FCC4C5E570E9A3145E09D6A8503D1BCCD1ED14C799A25B54377226EC93ACE7889549F3FD2F8C8A35D12238609; pin=dzllikelsw; unick=dzllikelsw");
        headMap.put(":authority", "b.jclps.com");
        headMap.put("Accept", "application/json, text/javascript, */*; q=0.01");
        reqBuilder.headers(Headers.of(headMap));
        Request request = reqBuilder.post(formBodyBuilder.build()).build();
        Response response;
        try {
            response = client.newCall(request).execute();
            System.out.println(new String(response.body().bytes(), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
