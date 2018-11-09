package br.edu.cefsa.ftt.ec;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.*;

public class AppMongoDB {
	
    // Libs: /lib folder
	// https://mvnrepository.com/artifact/com.google.code.gson/gson/2.8.5
	// https://mvnrepository.com/artifact/org.mongodb/mongo-java-driver/3.8.2
	
	// Tutorial: https://www.mongodb.com/blog/post/getting-started-with-mongodb-and-java-part-i
	
	// Import data...
	// ./mongoimport --jsonArray --db ftt --collection people --file /home/engineer/people.json
	
	// Referência:
	// https://books.goalkicker.com/MongoDBBook/
	// https://mongodb.github.io/mongo-java-driver/
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       System.out.println("App MongoDB - " + new java.util.Date());
       
       Random rand = new Random(); 
       
       //MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
       //MongoClient mongoClient = new MongoClient(new MongoClientURI("mbd01","mbd02","mbd03"));
       MongoClient mongoClient = new MongoClient();
       MongoDatabase mongoDatabase = mongoClient.getDatabase("ftt");
       MongoCollection<Document> collection = mongoDatabase.getCollection("ec-docs");
       
       Document docFttec = new Document()
    		   .append("date",    new Date().toString())
    		   .append("val",     rand.nextFloat())
    		   .append("sal",     rand.nextInt())
    		   .append("docType", "ftt-ec");

       collection.insertOne(docFttec);
       
       //Document myDoc = collection.find().first();
       Document myDoc = collection.find(eq("_id",new ObjectId("5be4d35f570d815cf5b6dd69"))).first();
       System.out.println(myDoc.toJson());
       
       //eq = equals
       //Document myDoc = collection.find(eq("counter", 7)).first();
       //System.out.println(myDoc.toJson());
       
       // The $set operator replaces the value of a field with the specified value.
       //collection.updateOne(eq("counter", 1), new Document("$set", new Document("counter", 100)));
       
       //collection.updateOne(eq("counter", 0), new Document("$set", new Document("status", "ok")));
       
       //collection.deleteMany(new Document().append("docType", "ftt-ec-x"));
       
       //collection.updateMany(eq("docType","ftt-ec"),  new Document("$set", new Document("category", "approved")));
       
       System.out.println("Number of docs: " + collection.countDocuments());
             

   	   
       mongoClient.close();
	
       
	}
	
	static String convertStreamToString(java.io.InputStream is) {
	    java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}

}
