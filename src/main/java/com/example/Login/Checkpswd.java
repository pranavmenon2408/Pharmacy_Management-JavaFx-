package com.example.Login;
import static com.mongodb.client.model.Filters.eq;



//import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
//import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
public class Checkpswd {
    public static LoginRecord returnLog(String username) {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        // Replace the uri string with your MongoDB deployment's connection string
        String uri = "mongodb+srv://pranav2408dhruv:eedCE0SG9zXLbLJ1@cluster0.zsfaz5c.mongodb.net/?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("HOMS").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<LoginRecord> collection = database.getCollection("Login", LoginRecord.class);
            LoginRecord log = collection.find(eq("username", username)).first();
            mongoClient.close();
            return log;
        }
    }
    public static void insertLog(String username,String password){
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        // Replace the uri string with your MongoDB deployment's connection string
        String uri = "mongodb+srv://pranav2408dhruv:eedCE0SG9zXLbLJ1@cluster0.zsfaz5c.mongodb.net/?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("HOMS").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<LoginRecord> collection = database.getCollection("Login", LoginRecord.class);
            LoginRecord loginRecord=new LoginRecord(username,password);
            collection.insertOne(loginRecord);
            mongoClient.close();
            
    }
}
}
