package com.example.AMQP_Receiver.mongo;

import com.example.AMQP_Receiver.TimeObject;
import org.bson.Document;

public interface IMongoAPI {

    Document writeTs(TimeObject timeObject);

}
