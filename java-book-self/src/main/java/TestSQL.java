import org.apache.commons.codec.Charsets;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dongzonglei
 * @description
 * @date 2019-06-20 14:21
 */
public class TestSQL {

    public static void main(String args[]) throws Exception {
        //String sql = "alter table mall_order_%s modify column order_type int(11) not null comment '订单类型';";
        //String sql = "update mall_order_%s set order_type = 180, modified = now() where order_id in (select order_id from mall_order_detail_%s where item_id between 36000000 and 38000000);";
//        String sql = "alter table mall_order_%s add column relate_info VARCHAR(4000) DEFAULT NULL COMMENT '订单相关联信息内容';";
//        for (int i = 0; i <= 255; i++) {
//            System.out.println(String.format(sql, i, i));
//        }
        
//        String sql = "WITH cte AS (SELECT order_id, user_id FROM t_order) INSERT INTO t_order (order_id, user_id) SELECT order_id, user_id FROM cte";
//        System.out.println(sql.indexOf("cte", 126));

        // 创建HttpClient实例
        CookieStore cookieStore = new BasicCookieStore();
        cookieStore.addCookie(new BasicClientCookie("sso.jd.com", "BJ.F92227C42D9A78CEA7F181A73DDD70D84520210524115731"));
        HttpClient client =  HttpClientBuilder.create().setDefaultCookieStore(cookieStore).build();
        // 根据URL创建HttpGet实例
//        HttpGet get = new HttpGet("http://g.jsf.jd.local/com.jd.user.sdk.memphis.export.MemphisEnterpriseUserRelevanceService/userSdk-jimdb-yfb/queryBpinByCpin/4419?pin=cqf5323163");
//        get.setHeader("token", ".source:misc_jdf"); // 如果是token，在header里面加
        //HttpGet get = new HttpGet("11.48.1.253:1601/user/relevance/bpin");
        // 执行get请求，得到返回体
        HttpPost post = new HttpPost("http://dbsv4.jd.com/dbmanage/QueryConnNum/getMysqlConnNum");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("search_content", "172.20.79.123"));
        HttpResponse response = client.execute(post);
        System.out.println("aaa" + EntityUtils.toString(response.getEntity(), Charsets.UTF_8));
    }
}
