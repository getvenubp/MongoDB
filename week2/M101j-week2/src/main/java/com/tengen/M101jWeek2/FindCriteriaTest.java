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

public class FindCriteriaTest {

public static void main(String[] args) throws UnknownHostException {
		
		MongoClient client = new MongoClient(new ServerAddress("localhost",27017) );
		
		DB database = client.getDB("course");
		DBCollection collection = database.getCollection("findCriteriaTest");
		
		collection.drop();
		
		for(int i=0; i < 10;i++){
			collection.insert(new BasicDBObject("x", new Random().nextInt(2))
			.append("y", new Random().nextInt(100)));
		}
		
		QueryBuilder builder = QueryBuilder.start("x").is(0)
				.and("y").greaterThan(10).lessThan(70);
		
		DBObject query = new BasicDBObject("x",0)
				.append("y", new  BasicDBObject("$gt", 10).append("$lt", 90));
		
		System.out.println("\nCount");
		//long count = collection.count(query);
		long count = collection.count(builder.get());
		System.out.println(count);
		
		System.out.println("\nFind Criteria");
		//DBCursor cursor = collection.find(query);
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
