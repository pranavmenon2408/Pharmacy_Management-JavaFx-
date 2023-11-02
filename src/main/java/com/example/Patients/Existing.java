package com.example.Patients;

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

/*import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;*/

public class Existing {
    
    public static PatientRecords returnPat(String name) {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        // Replace the uri string with your MongoDB deployment's connection string
        String uri = "mongodb+srv://pranav2408dhruv:eedCE0SG9zXLbLJ1@cluster0.zsfaz5c.mongodb.net/?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("HOMS").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<PatientRecords> collection = database.getCollection("Patients", PatientRecords.class);
            PatientRecords pat = collection.find(eq("name", name)).first();
            mongoClient.close();
            return pat;
        }
    }
    public static void insertPat(String name,String address,String pno,String cd,int age,boolean checkup,boolean surgery){
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        // Replace the uri string with your MongoDB deployment's connection string
        String uri = "mongodb+srv://pranav2408dhruv:eedCE0SG9zXLbLJ1@cluster0.zsfaz5c.mongodb.net/?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("HOMS").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<PatientRecords> collection = database.getCollection("Patients", PatientRecords.class);
            PatientRecords pat=new PatientRecords(name, address, pno, cd, age, checkup, surgery);
            collection.insertOne(pat);
            mongoClient.close();
        }
    }
}