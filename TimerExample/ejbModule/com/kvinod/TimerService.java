package com.kvinod;

import java.util.Date;

import javax.ejb.Remote;

@Remote
public interface TimerService {
	public void createTimer(long duration);
	public void executeOn(Date dt);
}
