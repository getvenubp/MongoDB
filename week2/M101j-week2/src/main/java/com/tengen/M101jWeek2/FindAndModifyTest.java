package com.tengen.M101jWeek2;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;
import com.mongodb.ServerAddress;

public class FindAndModifyTest {
	public static void main(String[] args) throws UnknownHostException {	
		DBCollection collection = createCollection();
		
		final String counterId = "abc";
		int first;
		int numNeeded;
		
		numNeeded = 2;
		first = getRange(counterId, numNeeded,collection);
		System.out.println("Range: "+ first + "-" + (first + numNeeded - 1));
		
		numNeeded = 3;
		first = getRange(counterId, numNeeded,collection);
		System.out.println("Range: "+ first + "-" + (first + numNeeded - 1));
		
		numNeeded = 10;
		first = getRange(counterId, numNeeded,collection);
		System.out.println("Range: "+ first + "-" + (first + numNeeded - 1));
		
		printCollection(collection);
	}

	private static int getRange(String id, int range,
			DBCollection collection) {
			DBObject doc = collection.findAndModify(new BasicDBObject("_id",id),null,null,false,
					new BasicDBObject("$inc",new BasicDBObject("counter",range)),
					true,true);
		return (Integer) doc.get("counter") - range +1;
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
