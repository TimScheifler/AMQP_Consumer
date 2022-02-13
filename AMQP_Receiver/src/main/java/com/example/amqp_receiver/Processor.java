package com.example.amqp_receiver;

import com.example.amqp_receiver.mongo.IMongoAPI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class Processor {

    private List<Document> commentDocuments = new ArrayList<>();
    private final MongoCollection<Document> timeObjectCollection;
    private final IMongoAPI mongoAPI;
    private int counter = 0;

    public Processor(final MongoDatabase mongoDatabase, final IMongoAPI mongoAPI){
        this.mongoAPI = mongoAPI;
        this.timeObjectCollection = mongoDatabase.getCollection("FINAL_3_amqp_lag_0_drop_20");
    }

    public void processTimeObject(TimeObject timeObject){
        counter++;
        commentDocuments.add(mongoAPI.writeTs(timeObject));

        if(counter > 10){
            timeObjectCollection.insertMany(commentDocuments);
            commentDocuments.clear();
        }
    }
}
