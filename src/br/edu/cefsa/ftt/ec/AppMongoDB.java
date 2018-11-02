package br.edu.cefsa.ftt.ec;

import java.util.Date;
import java.util.Random;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class AppMongoDB {
	
    // Libs: /lib folder
	// https://mvnrepository.com/artifact/com.google.code.gson/gson/2.8.5
	// https://mvnrepository.com/artifact/org.mongodb/mongo-java-driver/3.8.2
	
	// Tutorial: https://www.mongodb.com/blog/post/getting-started-with-mongodb-and-java-part-i
	
	// Import data...
	// ./mongoimport --jsonArray --db ftt --collection people --file /home/engineer/people.json
	
	// Referência:
	// https://books.goalkicker.com/MongoDBBook/
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       System.out.println("App MongoDB");
       
       Random rand = new Random(); 
       
       MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
       MongoDatabase mongoDatabase = mongoClient.getDatabase("ftt");
       MongoCollection<Document> collection = mongoDatabase.getCollection("ec-docs");
       
       Document fttec = new Document()
    		   .append("date",    new Date().toString())
    		   .append("val",     rand.nextFloat())
    		   .append("sal",     rand.nextInt())
    		   .append("docType", "ftt-ec");

       collection.insertOne(fttec);
       
       System.out.println(collection.countDocuments());
       
       mongoClient.close();
	
	}

}
