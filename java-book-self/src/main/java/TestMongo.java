import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import org.bson.Document;

import java.net.InetSocketAddress;

/**
 * @author dongzonglei
 * @description
 * @date 2019-10-20 18:17
 */
public class TestMongo {
    
    public static void main(String args[]) throws Exception {
        MongoServer server = new MongoServer(new MemoryBackend());

        // optionally: server.enableSsl(key, keyPassword, certificate);

        // bind on a random local port
        InetSocketAddress serverAddress = server.bind();

        MongoClient client = new MongoClient(new ServerAddress(serverAddress));
        MongoCollection collection = client.getDatabase("testdb").getCollection("testcollection");

        // creates the database and collection in memory and insert the object
        Document obj = new Document("_id", 1).append("key", "value");
        collection.insertOne(obj);
        
        System.out.println(collection.count());
        System.out.println(collection.find().first());
    }
}
