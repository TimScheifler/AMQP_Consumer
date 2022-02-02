package com.example.AMQP_Receiver;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Timestamp;
import java.util.concurrent.CountDownLatch;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.example.AMQP_Receiver.mongo.IMongoAPI;
import com.example.AMQP_Receiver.mongo.MongoAPI;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);
    private MongoDatabase mongoDatabase;
    private IMongoAPI mongoAPI;
    private Processor processor;

    public Receiver(){
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
        rootLogger.setLevel(Level.OFF);

        MongoClient mongoClient = new MongoClient("localhost", 27017);

        mongoDatabase = mongoClient.getDatabase("EventProcessing");
        mongoAPI = new MongoAPI(mongoDatabase);
        processor = new Processor(mongoDatabase, mongoAPI);

    }

    public void receiveMessage(byte[] data) throws IOException, ClassNotFoundException {
        Timestamp currentTs = new Timestamp(System.currentTimeMillis());
        Timestamp ts = (Timestamp) SerializationUtils.deserialize(data);

        TimeObject timeObject = new TimeObject(ts, currentTs);

        System.out.println("Received <" + ts + ">");
        processor.processTimeObject(timeObject);
        System.out.println("Timedifference: " + timeObject.getTimeTaken());
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}