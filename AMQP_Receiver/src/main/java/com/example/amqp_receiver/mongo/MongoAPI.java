package com.example.amqp_receiver.mongo;

import com.example.amqp_receiver.TimeObject;
import org.bson.Document;


public class MongoAPI implements IMongoAPI {

    @Override
    public Document writeTs(TimeObject timeObject) {
        return new Document("start", timeObject.getStart())
                .append("end", timeObject.getEnd())
                .append("timeTaken", timeObject.getTimeTaken());
    }
}