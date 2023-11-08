package com.example.Patients;

import static com.mongodb.client.model.Filters.eq;

//import com.example.Employees.EmployeeRecord;
import com.mongodb.MongoException;

//import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
//import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

//import java.util.List;

import org.apache.commons.text.WordUtils;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

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
            PatientRecords pat = collection.find(eq("name", WordUtils.capitalizeFully(name))).first();
            mongoClient.close();
            return pat;
        }
    }
    public static void insertPat(String name,String address,String email,String pno,String cd,int age,boolean illness,boolean injury,boolean surgery,String attending){
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        // Replace the uri string with your MongoDB deployment's connection string
        String uri = "mongodb+srv://pranav2408dhruv:eedCE0SG9zXLbLJ1@cluster0.zsfaz5c.mongodb.net/?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("HOMS").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<PatientRecords> collection = database.getCollection("Patients", PatientRecords.class);
            PatientRecords pat=new PatientRecords(WordUtils.capitalizeFully(name), address, email, pno, cd, age, illness, injury, surgery,false,attending);
            collection.insertOne(pat);
            mongoClient.close();
        }
    }
    public static void updatePat(String name){
        //CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        //CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        String uri = "mongodb+srv://pranav2408dhruv:eedCE0SG9zXLbLJ1@cluster0.zsfaz5c.mongodb.net/?retryWrites=true&w=majority";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("HOMS");
            //MongoDatabase database2=mongoClient.getDatabase("HOMS").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Document> collection = database.getCollection("Patients");
            //MongoCollection<PatientRecords> collection2=database2.getCollection("Patients",PatientRecords.class);
            //PatientRecords pham=collection2.find(eq("name",name)).first();
            Document query = new Document().append("name",  name);
            //PatientRecords pat=collection2.find(eq("name",name)).first();
            
            
            
            // Creates instructions to update the values of three document fields
            Bson updates = Updates.combine(
                    Updates.set("aptdone", true)
                    );

            // Instructs the driver to insert a new document if none match the query
            //UpdateOptions options = new UpdateOptions().upsert(true);

            try {
                // Updates the first document that has a "title" value of "Cool Runnings 2"
                UpdateResult result = collection.updateOne(query, updates);
                System.out.println(result);
                
            
            // Prints a message if any exceptions occur during the operation
            } catch (MongoException me) {
                System.err.println("Unable to update due to an error: " + me);
            }
            
        }
    }
}