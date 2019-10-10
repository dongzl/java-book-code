package com.java.book.self.pg;

import org.postgresql.PGConnection;
import org.postgresql.PGProperty;
import org.postgresql.replication.PGReplicationStream;

import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author dongzonglei
 * @description
 * @date 2019-09-28 08:25
 */
public class Test {

    public static void main(String args[]) throws Exception {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        Properties props = new Properties();
        PGProperty.USER.set(props, "postgres");
        PGProperty.PASSWORD.set(props, "123456");
        PGProperty.ASSUME_MIN_SERVER_VERSION.set(props, "9.4");
        PGProperty.REPLICATION.set(props, "database");
        PGProperty.PREFER_QUERY_MODE.set(props, "simple");

        Connection con = DriverManager.getConnection(url, props);
        PGConnection replConnection = con.unwrap(PGConnection.class);

//        replConnection.getReplicationAPI().dropReplicationSlot("demo_logical_slot");
//
//        replConnection.getReplicationAPI()
//                .createReplicationSlot()
//                .logical()
//                .withSlotName("demo_logical_slot")
//                .withOutputPlugin("test_decoding")
//                .make();
//        Connection sqlConnection = con;
//        //some changes after create replication slot to demonstrate receive it
//        sqlConnection.setAutoCommit(true);
//        Statement st = sqlConnection.createStatement();
//        st.execute("insert into test_logic_table(pk, name) values(1, 'first tx changes')");
//        st.close();
//
//        st = sqlConnection.createStatement();
//        st.execute("update test_logic_table set name = 'second tx change' where pk = 1");
//        st.close();
//
//        st = sqlConnection.createStatement();
//        st.execute("delete from test_logic_table where pk = 1");
//        st.close();

        PGReplicationStream stream =
                replConnection.getReplicationAPI()
                        .replicationStream()
                        .logical()
                        .withSlotName("demo_logical_slot")
                        .withSlotOption("include-xids", false)
                        .withSlotOption("skip-empty-xacts", true)
                        .withStatusInterval(20, TimeUnit.SECONDS)
                        .start();

        while (true) {
            //non blocking receive message
            ByteBuffer msg = stream.readPending();

            if (msg == null) {
                TimeUnit.MILLISECONDS.sleep(10L);
                continue;
            }

            int offset = msg.arrayOffset();
            byte[] source = msg.array();
            int length = source.length - offset;
            System.out.println(new String(source, offset, length));

            //feedback
            stream.setAppliedLSN(stream.getLastReceiveLSN());
            stream.setFlushedLSN(stream.getLastReceiveLSN());
        }
    }

    void my_function(Connection connection) {
        System.out.println("My application name is " + ((PGConnection) connection).getParameterStatus("application_name"));
    }
}
