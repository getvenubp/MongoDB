package com.tengen.M101jWeek2;

import java.net.UnknownHostException;
import java.util.Random;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;
import com.mongodb.ServerAddress;

public class SortSkipLimitTest {
	public static void main(String[] args) throws UnknownHostException {	
		MongoClient client = new MongoClient(new ServerAddress("localhost",27017) );
		
		DB database = client.getDB("course");
		DBCollection collection = database.getCollection("SortSkipLimitTest");
		collection.drop();
		Random random = new Random();
		
		for(int i=0; i < 10;i++){
			collection.insert(new BasicDBObject("_id", i)
					.append("start", new BasicDBObject("x", random.nextInt(90) + 10)
													.append("y", random.nextInt(90) + 10))
					.append("end", new BasicDBObject("x", random.nextInt(90) + 10)
													.append("y", random.nextInt(90) + 10))			
			);
		}
		
		DBCursor cursor = collection.find()
				.sort(new BasicDBObject("start.x",-1))
				.skip(2).limit(10);
		
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
