package com.tengen.M101jWeek2;

import java.util.Arrays;
import java.util.Date;

import com.mongodb.BasicDBObject;

public class DocumentRepresentationTest {

	public static void main(String[] args) {
		BasicDBObject doc = new BasicDBObject();
		doc.put("username",	"abc");
		doc.put("birthday", new Date(234832423));
		doc.put("programmer", true);
		doc.put("age", 8);
		doc.put("languages", Arrays.asList("Java","C#"));
		doc.put("address", new BasicDBObject("street","20 Main").append("town", "Westfield").append("zip", "56789"));
	}

}
