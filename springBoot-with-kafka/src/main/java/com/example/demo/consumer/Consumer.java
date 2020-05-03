package com.example.demo.consumer;

import java.io.IOException;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.*;
import org.springframework.kafka.support.*;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

 //we can also configure POJO listeners with explicit topics and partitions (and, optionally, their initial offsets):
 //Since the initialOffset has been sent to 100 in this listener, all the previously consumed messages from partitions 1, will be re-consumed every time this listener is initialized. If setting the offset is not required   
   
    @KafkaListener(id = "bar", topicPartitions =
        { @TopicPartition(topic = "users", partitions = { "0", "1" }),
          @TopicPartition(topic = "Secondusers", partitions = "0",
             partitionOffsets = @PartitionOffset(partition = "1", initialOffset = "100"))
        })
    public void consume(@Payload String message, 
    		  @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
    		  @Header(KafkaHeaders.OFFSET) Long offset,
              @Header(KafkaHeaders.CONSUMER) KafkaConsumer<String, String> consumer,
              @Header(KafkaHeaders.TIMESTAMP_TYPE) String timestampType,
              @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
             // @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String messageKey,
              @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp
             // @Header("X-Custom-Header") String customHeader
              ) throws IOException {
        logger.info(String.format("#### -> Consumed message -> %s", message+ "from partition: " + partition));
        logger.info("received message='{}'", message);
        logger.info("consumer: {}", consumer);
        logger.info("topic: {}", topic);
      //  logger.info("message key: {}", messageKey);
        logger.info("partition id: {}", partition);
        logger.info("offset: {}", offset);
        logger.info("timestamp type: {}", timestampType);
        logger.info("timestamp: {}", timestamp);
       // logger.info("custom header: {}", customHeader);
    }
    
    
    @KafkaListener(topics = "users", groupId = "demo-group")
    public void consume(String message) throws IOException {
        logger.info(String.format("#### -> Consumed message -> %s", message));
    }
}
