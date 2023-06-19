package com.leon.mongo;

import java.io.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.MongoClients;
import com.mongodb.MongoWriteException;

public class MongoSaver
{
	public static void write(String json) throws IOException
	{
		String connectionURI = "mongodb://workbench_user:workbench_user@leonadeoyemongodbcluster-shard-00-01-gni1u.azure.mongodb.net:27017,leonadeoyemongodbcluster-shard-00-00-gni1u.azure.mongodb.net:27017,leonadeoyemongodbcluster-shard-00-02-gni1u.azure.mongodb.net:27017/admin?serverSelectionTimeoutMS=20000&readPreference=primary&ssl=true";
		com.mongodb.client.MongoClient client = MongoClients.create(connectionURI);

		MongoDatabase database = client.getDatabase("leon");
		MongoCollection<org.bson.Document> coll = database.getCollection("test");

		try
		{
			coll.insertOne(Document.parse(json));
			System.out.println("Inserted" + json);
		}
		catch (MongoWriteException e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			client.close();
		}
	}

	public static void main() throws IOException
	{
		String json = "{\"name\":\"Leon\",\"age\":30}";
		write(json);
	}
}