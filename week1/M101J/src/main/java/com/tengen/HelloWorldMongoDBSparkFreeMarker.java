package com.tengen;

import java.io.StringWriter;
import java.net.UnknownHostException;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class HelloWorldMongoDBSparkFreeMarker {

	public static void main(String[] args) throws UnknownHostException {
		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(HelloWorldMongoDBSparkFreeMarker.class, "/");

		MongoClient client = new MongoClient(new ServerAddress("localhost",27017) );
		
		DB database = client.getDB("course");
		final DBCollection collection = database.getCollection("hello");
		
		Spark.get(new Route("/"){
			public Object handle(Request request, Response response) {
				StringWriter writer = new StringWriter();
				try {
					Template helloTemplate = configuration.getTemplate("hello.ftl");
					
					DBObject document = collection.findOne();
					
					helloTemplate.process(document, writer);					
				} catch (Exception e) {
					halt(500);
					e.printStackTrace();
				}			
				return writer;
			}			
		});


	}

}
