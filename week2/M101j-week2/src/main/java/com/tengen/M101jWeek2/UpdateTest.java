package com.tengen.M101jWeek2;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;
import com.mongodb.ServerAddress;

public class UpdateTest {
	public static void main(String[] args) throws UnknownHostException {	
		DBCollection collection = createCollection();
		
		List<String> names = Arrays.asList("alice","bobby","cathy","david","ethan");
		for(String name : names){
			collection.insert(new BasicDBObject("_id",name));
		}
		
		collection.update(new BasicDBObject("_id","alice"),
				new BasicDBObject("age",24));
		
		collection.update(new BasicDBObject("_id","frank"),
				new BasicDBObject("$set",new BasicDBObject("gender","M")),true,false);
		
		collection.update(new BasicDBObject(),
				new BasicDBObject("$set",new BasicDBObject("title","Dr.")),false,true);
		
		collection.remove(new BasicDBObject("_id","bobby"));
		
		printCollection(collection);
	}

	private static DBCollection createCollection() throws UnknownHostException {
		MongoClient client = new MongoClient(new ServerAddress("localhost",27017) );
		
		DB database = client.getDB("course");
		DBCollection collection = database.getCollection("UpdateTest");
		collection.drop();
		
		return collection;		
	}

	private static void printCollection(DBCollection collection) {
		QueryBuilder builder = QueryBuilder.start();
		DBCursor cursor = collection.find(builder.get());
		try {
			while(cursor.hasNext()){
				DBObject dbObject = cursor.next();
				System.out.println(dbObject);
			}			
		} finally {
			cursor.close();
		}	
	}	
}
