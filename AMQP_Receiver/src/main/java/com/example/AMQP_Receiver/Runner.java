package com.example.AMQP_Receiver;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    private static final int WAIT_TIME = 10000;

    private final Receiver receiver;

    public Runner(Receiver receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
    }

    @Override
    public void run(String... args) throws Exception {
        while(true){
            receiver.getLatch().await(1000, TimeUnit.MILLISECONDS);
        }
    }
}