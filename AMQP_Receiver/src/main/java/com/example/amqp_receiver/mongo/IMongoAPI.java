package com.example.amqp_receiver.mongo;

import com.example.amqp_receiver.TimeObject;
import org.bson.Document;

public interface IMongoAPI {
    Document writeTs(TimeObject timeObject);
}
