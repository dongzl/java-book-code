/**
 * @author dongzonglei
 * @description
 * @date 2019-07-03 15:02
 */
public class TestPin {

    public static void main(String args[]) throws Exception {
        String pins = "8842649123_p,qiuye1233,zhangerjian,madcser,jd_6226d6c140fcc,jd_64cc0f7eb6ceb,jd_6949bb4385d19,jd_4fedf5d5f96e4,jd_7c099d7cba472,jd_6d01f45c703f1,jd_461a4dde162ee,jd_557ba1f35d4ce,zhxlqhag,jd_4c79dee98ddae,jDread读书测试199,525153575_m,jd_5ae5578165ed7,1169440180lp,yaomeiyouzhuya,memphis_0912,jd_46f8ea81f610b,jd_6192d42b95953,jd_7c5e4a082dcdc,380064547-81937990,jd_7038b3f0aa387,jd_45c848c4c7583,jd_787bca190bd67,jd_7ae57e1789019,jd_4d94207252df3,jd_zengqiuping,jd_52a16b7384052,艾茶语,parkwookdong,佳心悦露q,jd_479e4f126c153,68465284-260249,jd_4ed0025582a07,jd_7efe35a558556,zcl2770,jd_4f9012a007bb0,一盆栽,t2902371,zc1988830,mowen_gx,candywxh,feiyu478,红叶枫了没,dzllikelsw,jd_6456fbbbfd936,xiaocaileet,jd_483973ee9b1ec,jd_722e448f3b18d,307338746-77898054,一凡cici,loveourear_m,jd_4a148a5d8d639,jd_73463e8870793,jingli_19880404,13552372510_p,jd_6a37dcd8c0f34,jd_5ca12c9fe9127,jd_42991ba667426,jd_67abbced93e8f,诗诗南山,jd_6c7fad17ac577,jd_5605ec4bb289e,jd_5407e807b1b3f,jd_787aba60fcab5,jd_4ab8bde74d33d,mhy莫9428,jd_6ee4be9902c6c,jd_779db975a3c6e,jd_56155886f1524,15002257721_p,zzycgfans,chenqi_2_m";
        String[] array = pins.split(",");
        for (String pin : array) {
            System.out.println("'" + pin + "',");
        }
    }
}
