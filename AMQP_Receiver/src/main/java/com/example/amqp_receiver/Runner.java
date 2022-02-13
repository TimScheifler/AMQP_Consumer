package com.example.amqp_receiver;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    private final Receiver receiver;

    public Runner(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void run(String... args) throws Exception {
        while(true){
            receiver.getLatch().await(1000, TimeUnit.MILLISECONDS);
        }
    }
}