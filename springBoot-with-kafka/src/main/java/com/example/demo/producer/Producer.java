package com.example.demo.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

//don't transfer headers over Kafka because it doesn't support them.
//although in below producer code i have few headers but that is only to set partition id but normally kafka
//dont support any headersand KafkaHeaders.MESSAGE_KEY is for internal use

@Service
public class Producer {

	private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC = "users";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        logger.info(String.format("#### -> Producing message -> %s", message));
        this.kafkaTemplate.send(TOPIC, message);
    }
    
    public void sendFoo(String data){

        Message<String> message = MessageBuilder
                 .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "Secondusers")//mandatory topic should be provided users/secondusers , any one of them
               // .setHeader(KafkaHeaders.TOPIC, "users")
               // .setHeader(KafkaHeaders.MESSAGE_KEY, "999")
                 .setHeader(KafkaHeaders.PARTITION_ID, 0)
              //   .setHeader("X-Custom-Header", "Sending Custom Header with Spring Kafka")
                 .build();

        logger.info("sending message='{}' to topic='{}'", data, "Secondusers");
         this.kafkaTemplate.send(message);
     }
}
