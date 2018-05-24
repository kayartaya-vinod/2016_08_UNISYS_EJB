package training.ejb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig={
	@ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue"),
	@ActivationConfigProperty(propertyName="destination", propertyValue="java:queue/training"),
	@ActivationConfigProperty(propertyName="useJNDI", propertyValue="true"),
	@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
public class QueueRecieverBean implements MessageListener {

	@Override
	public void onMessage(Message message) {
		if(message instanceof TextMessage){
			// handle the incoming text message
			TextMessage tm = (TextMessage) message;
			
			// now this function can act as a client to other
			// session beans, and invoke methods on the same.
			
			// in this demo, we simply print the message
			try {
				System.out.println(">>>> Inside QueueRecieverBean.onMessage, got this: " 
						+ tm.getText());
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

}












