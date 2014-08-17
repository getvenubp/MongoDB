 package com.tengen.M101jWeek2;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class InsertTest {

	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient(new ServerAddress("localhost",27017) );
		
		DB database = client.getDB("course");
		DBCollection collection = database.getCollection("insertTest");
		
		collection.drop();
		
		BasicDBObject doc = new BasicDBObject("_id",new ObjectId());
		doc.put("username",	"abc");
		
		BasicDBObject doc2 = new BasicDBObject("_id",new ObjectId()).append("username", "xyz");
				
		System.out.println(doc);

		collection.insert(doc);
		collection.insert(doc2);
		
		System.out.println(doc); 
	}

}
