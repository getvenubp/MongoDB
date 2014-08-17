package com.tengen.M101jWeek2;

import java.net.UnknownHostException;
import java.util.Random;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class FindTest {

	public static void main(String[] args) throws UnknownHostException {
		
		MongoClient client = new MongoClient(new ServerAddress("localhost",27017) );
		
		DB database = client.getDB("course");
		DBCollection collection = database.getCollection("findTest");
		
		collection.drop();
		
		for(int i=0; i < 10;i++){
			collection.insert(new BasicDBObject("x", new Random().nextInt(100)));
		}
		
		System.out.println("Find one");
		DBObject findOne = collection.findOne();
		System.out.println(findOne);
		
		System.out.println("\nFind all");
		DBCursor cursor = collection.find();
		try {
			while(cursor.hasNext()){
				DBObject dbObject = cursor.next();
				System.out.println(dbObject);
			}			
		} finally {
			cursor.close();
		}
		
		
		System.out.println("\nCount");
		long count = collection.count();
		System.out.println(count);
		 
	}

}
