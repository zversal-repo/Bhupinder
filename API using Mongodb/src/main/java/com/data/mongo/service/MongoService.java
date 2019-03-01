package com.data.mongo.service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.mongo.config.MongoConfig;
import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.operation.BatchCursor;

@Service
public class MongoService {

	public MongoService() {}
		
	
	
	
 
	private MongoConfig mongoConfig = new MongoConfig();
   
	
	private String databaseName =mongoConfig.getDatabaseName();
	private String connection = mongoConfig.getUri();
	private String mycollections=mongoConfig.getCollection();
	 
	
	private MongoClientURI connectionString = new MongoClientURI(connection);
	private MongoClient mongoClient = new MongoClient(connectionString); 
	private MongoDatabase database = mongoClient.getDatabase(databaseName);
	  
	private MongoCollection<Document> collection =  database.getCollection(mycollections);
   
    public Document getData(String ticker) {
    	FindIterable<Document> document = collection
    			.find(new BasicDBObject("Ticker", ticker));
    	   		Document doc = document.first();
    	   		return doc;
        
			}
    public List<Document> getTicker(String channel) {
    	FindIterable<Document> document = collection
                .find(new BasicDBObject("Channel", channel))
                .projection(Projections.fields(Projections.include("Ticker"), Projections.excludeId()));
    	MongoCursor<Document> itr = document.iterator();
    	
    	List<Document> listDoc = new ArrayList<Document>();
        
    	while(itr.hasNext()) {
    	    
    	    listDoc.add(itr.next());
    	}
    	return listDoc;
    	
    }
}


