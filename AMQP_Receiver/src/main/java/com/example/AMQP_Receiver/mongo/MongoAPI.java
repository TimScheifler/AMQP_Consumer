package com.example.AMQP_Receiver.mongo;

import com.example.AMQP_Receiver.TimeObject;
import com.mongodb.Block;
import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static com.mongodb.client.model.Accumulators.first;
import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.unset;

public class MongoAPI implements IMongoAPI {

    private final MongoCollection<Document> timeObject_collection;

    public MongoAPI(MongoDatabase mongoDatabase) {
        this.timeObject_collection = mongoDatabase.getCollection("timeObjects");

    }

    @Override
    public Document writeTs(TimeObject timeObject) {
        return new Document("start", timeObject.getStart())
                .append("end", timeObject.getEnd())
                .append("timeTaken", timeObject.getTimeTaken());
    }
}