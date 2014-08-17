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

public class FindSelectionTest {
	public static void main(String[] args) throws UnknownHostException {
		
		MongoClient client = new MongoClient(new ServerAddress("localhost",27017) );
		
		DB database = client.getDB("course");
		DBCollection collection = database.getCollection("findSelectionTest");
		collection.drop();
		Random random = new Random();
		
		for(int i=0; i < 10;i++){
			collection.insert(new BasicDBObject("x", random.nextInt(2))
			.append("y", random.nextInt(100))
			.append("z", random.nextInt(1000)));
		}
		
		QueryBuilder builder = QueryBuilder.start("x").is(0)
				.and("y").greaterThan(10).lessThan(70);
		
		DBCursor cursor = collection.find(builder.get(),new BasicDBObject("y",true).append("_id", false));
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
