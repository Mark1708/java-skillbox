import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.connection.ClusterSettings;
import org.bson.BsonDocument;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
//        db.createUser({user: "adminuser", pwd: "admin", roles: [ "root" ]})
        MongoCredential credential = MongoCredential.createCredential("adminuser", "admin", "admin".toCharArray());
        Block<ClusterSettings.Builder> localhost = builder -> builder.hosts(Collections.singletonList(new ServerAddress("localhost", 27017)));
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyToClusterSettings(localhost)
                .credential(credential)
                .build();
        MongoClient client = MongoClients.create(settings);

        MongoDatabase database = client.getDatabase("admin");

        // Создаем коллекцию
        MongoCollection<Document> collection = database.getCollection("TestSkillDemo");

        // Удалим из нее все документы
        collection.drop();

        List<Document> documents = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/mongo.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] courses = line.substring(line.indexOf("\"")+1, line.lastIndexOf("\"")).split(",");
                String[] values = line.substring(0, line.indexOf("\"")).split(",");
//                List<String> strings = Arrays.asList(values[2].replace("\"", "").split(","));
                documents.add(new Document()
                        .append("name", values[0])
                        .append("age", Integer.parseInt(values[1]))
                        .append("courses", Arrays.asList(courses))
                );
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Вставляем документ в коллекцию
        collection.insertMany(documents);

        System.out.println("общее количество студентов в базе — " + collection.countDocuments());

        BsonDocument query = BsonDocument.parse("{age: {$gt: 40}}");
        System.out.println("количество студентов старше 40 лет — " + collection.countDocuments(query));

        query = BsonDocument.parse("{age: 1}");
        System.out.println("имя самого молодого студента — " + collection.find().sort(query).first().get("name"));

        query = BsonDocument.parse("{age: -1}");
        System.out.println("список курсов самого старого студента — " + collection.find().sort(query).first().get("courses"));
    }
}
