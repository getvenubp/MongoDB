package com.tengen;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class HelloWorldSpark {

	public static void main(String[] args) {
		Spark.get(new Route("/"){

			public Object handle(Request request, Response response) {
				return "Hello World from spark";
			}
			
		});

	}

}
