package com.example.amqp_receiver;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.concurrent.CountDownLatch;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.example.amqp_receiver.mongo.IMongoAPI;
import com.example.amqp_receiver.mongo.MongoAPI;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.stereotype.Component;

@Component
public class Receiver {


    private int counter = 1;
    private CountDownLatch latch = new CountDownLatch(1);
    private MongoDatabase mongoDatabase;
    private IMongoAPI mongoAPI;
    private Processor processor;

    public Receiver(){
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
        rootLogger.setLevel(Level.OFF);

        try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
            mongoDatabase = mongoClient.getDatabase("EventProcessing");
        }
        mongoAPI = new MongoAPI();
        processor = new Processor(mongoDatabase, mongoAPI);

    }

    public void receiveMessage(byte[] data) throws IOException, ClassNotFoundException {
        Timestamp currentTs = new Timestamp(System.currentTimeMillis());
        Timestamp ts = (Timestamp) SerializationUtils.deserialize(data);

        TimeObject timeObject = new TimeObject(ts, currentTs);

        processor.processTimeObject(timeObject);
        System.out.println("Timedifference: " + timeObject.getTimeTaken() + "|"+counter);
        counter++;
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}