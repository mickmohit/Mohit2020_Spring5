package com.example.sender;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.model.Product;

@Component
public class Sender {

	@Autowired
    JmsTemplate jmsTemplate;
	
	private static final String MESSAGE_TOPIC = "message_topic";
 
    public void sendMessage() {
 
    	for (int i = 1; i <= 10; i++)
		{
			Product product = new Product();
			product.setProductId(i);
			product.setName("Laptop");
			product.setQuantity(10 + i);

			// Send a message
			System.out.println("Sending a product " + i);
			jmsTemplate.convertAndSend(new ActiveMQTopic(MESSAGE_TOPIC), product);
		}
       
    }
}
