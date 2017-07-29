package com.owen.MongoDB_HelloMongoDB;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try
		{
			MongoClient mongo = new MongoClient("localhost", 27017);
			
			// get all the dbs
			List<String> names = mongo.getDatabaseNames();
			for(String name : names)
				System.out.println(name);
			
			// get all the collections
			DB db = mongo.getDB("local");
			Set<String> tables = db.getCollectionNames();
			for(String coll : tables)
				System.out.println(coll);
			
			// save data into collection user
			DBCollection table = db.getCollection("user");
			BasicDBObject document = new BasicDBObject();
			document.put("name", "Owen");
			document.put("age", 24);
			document.put("createDate", new Date());
			table.insert(document);
			
			// update data in collection user
			BasicDBObject query = new BasicDBObject();
			query.put("name", "Owen");
			BasicDBObject newName = new BasicDBObject();
			newName.put("name", "Vincent");
			BasicDBObject setObj = new BasicDBObject();
			setObj.put("$set", newName);
			table.update(query, setObj);
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
    }
}
