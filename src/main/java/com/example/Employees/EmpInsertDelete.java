package com.example.Employees;

import org.apache.commons.text.WordUtils;

import static com.mongodb.client.model.Filters.eq;

//import com.example.Patients.PatientRecords;
//import com.example.Pharmacy.PharmacyRecord;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;

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

import java.util.List;
import java.util.Arrays;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

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
    public static void updateEmp(String name,String dept){
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        String uri = "mongodb+srv://pranav2408dhruv:eedCE0SG9zXLbLJ1@cluster0.zsfaz5c.mongodb.net/?retryWrites=true&w=majority";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("HOMS");
            MongoDatabase database2=mongoClient.getDatabase("HOMS").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Document> collection = database.getCollection("Employees");
            MongoCollection<EmployeeRecord> collection2=database2.getCollection("Employees",EmployeeRecord.class);
            //PatientRecords pham=collection2.find(eq("name",name)).first();
            Document query = new Document().append("department",  dept);
            EmployeeRecord emp=collection2.find(eq("department",dept)).first();
            List<String> assign=emp.getAssignments();
            assign.add(name);
            
            // Creates instructions to update the values of three document fields
            Bson updates = Updates.combine(
                    Updates.set("assignments", assign)
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
    public static String getAttending(String name,String dept){
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        // Replace the uri string with your MongoDB deployment's connection string
        String uri = "mongodb+srv://pranav2408dhruv:eedCE0SG9zXLbLJ1@cluster0.zsfaz5c.mongodb.net/?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("HOMS").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<EmployeeRecord> collection = database.getCollection("Employees", EmployeeRecord.class);
            FindIterable<EmployeeRecord> emp = collection.find(eq("department",dept ));
            
            String doctor="";
            for(EmployeeRecord em:emp){
                List<String> attending=em.getAssignments();
                for(String a:attending){
                    if(a!=null){
                    if(a.equals(name)){
                        doctor=em.getName();
                    }
                }
            }
            }
            mongoClient.close();
            return doctor;
        }
    }
    
}