package com.example.auditlogsmongodb.repository;


import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@Service
public class DocumentService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    MongoClient mongoClient;


    public List<Document> getDocuments(String entityName){
        MongoDatabase database = mongoClient.getDatabase("auditlogs");
        MongoCollection<Document> table = database.getCollection("events");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("payload.ChangeEventHeader.entityName", entityName);
        FindIterable<Document> documents =  table.find(searchQuery);
        List<Document> documentList = new ArrayList<>();
        documents.forEach((Consumer<? super Document>) document -> documentList.add(document));
        return  documentList;
    }

    public List<Document> getDocuments(){
        MongoDatabase database = mongoClient.getDatabase("auditlogs");
        MongoCollection<Document> table = database.getCollection("events");
        FindIterable<Document> documents =  table.find();
        List<Document> documentList = new ArrayList<>();
        documents.forEach((Consumer<? super Document>) document -> documentList.add(document));
        return  documentList;
    }

}
