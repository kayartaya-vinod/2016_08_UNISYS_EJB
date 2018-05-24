package com.kvinod;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(name = "hello-mdb", activationConfig = {
		 @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		 @ActivationConfigProperty(propertyName = "useJNDI", propertyValue = "true"),
		 @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/vinod"),
		 @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })

public class HelloMdb implements MessageListener {

	@Override
	public void onMessage(Message msg) {
		TextMessage tm = (TextMessage) msg;
		try {
			System.out.println(">>>>> Got this message: " + tm.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
