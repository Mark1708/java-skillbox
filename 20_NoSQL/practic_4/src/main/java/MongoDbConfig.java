import com.mongodb.Block;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.connection.ClusterSettings;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.Collections;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoDbConfig {
    private static final String USER = "adminuser";
    private static final String PASS = "admin";
    private static final String DB = "admin";

    private static MongoClient client;

    public static MongoClient init() {
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        MongoCredential credential = MongoCredential.createCredential(USER, DB, PASS.toCharArray());
        Block<ClusterSettings.Builder> block = builder -> builder.hosts(Collections.singletonList(new ServerAddress("localhost", 27017)));
        MongoClientSettings settings = MongoClientSettings.builder()
                .codecRegistry(codecRegistry)
                .applyToClusterSettings(block)
                .credential(credential)
                .build();
        try {
            client = MongoClients.create(settings);
            Thread.sleep(1000);
        } catch (RuntimeException e) {
            System.err.println("Error db connection!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return client;
    }
}
