package training.ejb;

import java.util.Date;

import javax.ejb.Remote;

@Remote
public interface MailScheduler {

	public void sendMailOn(Date date);
	
	public void sendMailAfter(long duration); // duration in ms
	
}
