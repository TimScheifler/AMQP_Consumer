package com.example.AMQP_Receiver;

import com.example.AMQP_Receiver.mongo.IMongoAPI;
import com.example.AMQP_Receiver.mongo.MongoAPI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import jdk.jfr.Timespan;
import org.bson.Document;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Processor {

    private List<Document> commentDocuments = new ArrayList<>();
    private final MongoCollection<Document> timeObject_collection;
    private final IMongoAPI mongoAPI;
    private int counter = 0;

    public Processor(final MongoDatabase mongoDatabase, final IMongoAPI mongoAPI){
        this.mongoAPI = mongoAPI;
        this.timeObject_collection = mongoDatabase.getCollection("timeObjects");
    }

    public void processTimeObject(TimeObject timeObject){
        counter++;
        commentDocuments.add(mongoAPI.writeTs(timeObject));

        if(counter > 10){
            timeObject_collection.insertMany(commentDocuments);
            commentDocuments.clear();
        }
    }
}
