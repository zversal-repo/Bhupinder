package com.data.mongo.controller;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.data.mongo.service.MongoService;

@RestController
public class MongoController {
	@Autowired
	private MongoService mongoservice;
	
	@RequestMapping(value="/Data/{Ticker}",method=RequestMethod.GET)
	public Document getData(@PathVariable ("Ticker") String ticker) {
		Document doc = mongoservice.getData(ticker);
		return doc;
		}
	
	@RequestMapping(value="/Ticker/{Channel}",method=RequestMethod.GET)
	public List<Document> getTicker(@PathVariable ("Channel") String channel) {
		List<Document> doc = mongoservice.getTicker(channel);
	    return doc;
		}
	
}
