package com.example.receiver;

import java.util.concurrent.atomic.AtomicInteger;

import javax.jms.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.example.demo.model.Confirmation;
import com.example.demo.model.Product;

@Component
public class Receiver {

private static final String MESSAGE_TOPIC = "message_topic";
private Logger logger = LoggerFactory.getLogger(Receiver.class);

private static AtomicInteger id = new AtomicInteger();

@Autowired
ConfirmationReceiver confirmationReceiver;
	
	@JmsListener(destination = MESSAGE_TOPIC,
			id = "comercial",
			subscription = MESSAGE_TOPIC,
			containerFactory = "connectionFactory")
	public void receiveMessage(Product product, Message message)
	{
		logger.info(" >> Original received message: " + message);
        logger.info(" >> Received product: " + product);
        
		System.out.println("Received " + product);
		
		confirmationReceiver.sendConfirmation(new Confirmation(id.incrementAndGet(), "User " +
		            product.getName() + " received."));
	}
}
