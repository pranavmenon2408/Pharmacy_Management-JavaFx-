package com.example.Employees;

import org.apache.commons.text.WordUtils;

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

import java.util.Arrays;

import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/*import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;*/

public class EmpInsertDelete {
    
    public static EmployeeRecord returnEmp(String name) {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        // Replace the uri string with your MongoDB deployment's connection string
        String uri = "mongodb+srv://pranav2408dhruv:eedCE0SG9zXLbLJ1@cluster0.zsfaz5c.mongodb.net/?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("HOMS").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<EmployeeRecord> collection = database.getCollection("Employees", EmployeeRecord.class);
            EmployeeRecord emp = collection.find(eq("name",WordUtils.capitalizeFully(name) )).first();
            mongoClient.close();
            return emp;
        }
    }
    public static void insertEmp(String name,String address,String pno,String dept,String email,int age,int empId,String[] assignments){
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        // Replace the uri string with your MongoDB deployment's connection string
        String uri = "mongodb+srv://pranav2408dhruv:eedCE0SG9zXLbLJ1@cluster0.zsfaz5c.mongodb.net/?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("HOMS").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<EmployeeRecord> collection = database.getCollection("Employees", EmployeeRecord.class);
            EmployeeRecord emp=new EmployeeRecord(WordUtils.capitalizeFully(name), age, dept, email, address, pno, empId, Arrays.asList(assignments));
            collection.insertOne(emp);
            mongoClient.close();
        }
    }
}