/**
 * @author dongzonglei
 * @description
 * @date 2019-06-20 14:21
 */
public class TestSQL {

    public static void main(String args[]) throws Exception {
        //String sql = "alter table mall_order_%s modify column order_type int(11) not null comment '订单类型';";
        //String sql = "update mall_order_%s set order_type = 180, modified = now() where order_id in (select order_id from mall_order_detail_%s where item_id between 36000000 and 38000000);";
        String sql = "ALTER TABLE `user_vip_service_%s` ADD INDEX `idx_time_status`(`service_end_time`, `service_status`);";
        for (int i = 0; i <= 255; i++) {
            System.out.println(String.format(sql, i, i));
        }
        
//        String sql = "WITH cte AS (SELECT order_id, user_id FROM t_order) INSERT INTO t_order (order_id, user_id) SELECT order_id, user_id FROM cte";
//        System.out.println(sql.indexOf("cte", 126));
    }
}
