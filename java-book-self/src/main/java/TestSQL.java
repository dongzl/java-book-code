/**
 * @author dongzonglei
 * @description
 * @date 2019-06-20 14:21
 */
public class TestSQL {

    public static void main(String args[]) throws Exception {
        //String sql = "alter table mall_order_%s modify column order_type int(11) not null comment '订单类型';";
        String sql = "update mall_order_%s set order_type = 180, modified = now() where order_id in (select order_id from mall_order_detail_%s where item_id between 36000000 and 38000000);";
        for (int i = 0; i <= 255; i++) {
            System.out.println(String.format(sql, i, i));
        }
    }
}
