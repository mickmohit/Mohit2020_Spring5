package com.example.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.example.demo.model.Confirmation;

@Component
public class ConfirmationReceiver {

	private Logger logger = LoggerFactory.getLogger(ConfirmationReceiver.class);

    @JmsListener(destination = "confirmationTopic",
    		id = "comercial_conf",
			subscription = "confirmationTopic",
    		containerFactory = "connectionFactory")
    public void sendConfirmation(Confirmation confirmation) {
        logger.info(" >>  Received confirmation: " + confirmation);
    }
}
