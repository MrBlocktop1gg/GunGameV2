package mrblock.gungamev2.mongoDB;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBManager {
  private static final String URI = "mongodb+srv://mrblockgg:vNlR9Ev0ilU0gHLq@gungamev2.xmrlyo6.mongodb.net/";

  public void connect() {
    try (MongoClient mongoClient = MongoClients.create(URI)) {
      MongoDatabase database = mongoClient.getDatabase("GunGameV2");
      MongoCollection<Document> collection = database.getCollection("GunGameData");
    }
  }
}
