package eventlib.job;

import eventlib.event.IEvent;

public interface IJob extends Runnable {
	
	public void setEvent(IEvent evt);

}
