// Updates the first document that matches a query filter by using the Java driver

package com.example.Pharmacy;

import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
//import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static com.mongodb.client.model.Filters.eq;
//import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;



public class InStock {

    public static void updateMed(String name) {
        // Replace the uri string with your MongoDB deployment's connection string
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        String uri = "mongodb+srv://pranav2408dhruv:eedCE0SG9zXLbLJ1@cluster0.zsfaz5c.mongodb.net/?retryWrites=true&w=majority";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("HOMS");
            MongoDatabase database2=mongoClient.getDatabase("HOMS").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Document> collection = database.getCollection("Pharmacy");
            MongoCollection<PharmacyRecord> collection2=database2.getCollection("Pharmacy",PharmacyRecord.class);
            Document query = new Document().append("name",  name);
            PharmacyRecord pham=collection2.find(eq("name",name)).first();
            int stock=pham.getStock();
            // Creates instructions to update the values of three document fields
            Bson updates = Updates.combine(
                    Updates.set("stock", --stock)
                    );

            // Instructs the driver to insert a new document if none match the query
            //UpdateOptions options = new UpdateOptions().upsert(true);

            try {
                // Updates the first document that has a "title" value of "Cool Runnings 2"
                UpdateResult result = collection.updateOne(query, updates);

                // Prints the number of updated documents and the upserted document ID, if an upsert was performed
                pham=collection2.find(eq("name",name)).first();
                System.out.println("Updated value: "+pham.getStock());
                System.out.println("Modified document count: " + result.getModifiedCount());
                //System.out.println("Upserted id: " + result.getUpsertedId());
            
            // Prints a message if any exceptions occur during the operation
            } catch (MongoException me) {
                System.err.println("Unable to update due to an error: " + me);
            }
        }
    }
}
