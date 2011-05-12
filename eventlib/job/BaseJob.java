package eventlib.job;

import eventlib.event.IEvent;

/**
 * Base class for all jobs Predefines setEvent method, that sets event
 * containing given job
 * 
 * @author abc
 * 
 */
public abstract class BaseJob implements IJob {
	IEvent event;

	@Override
	public void setEvent(IEvent evt) {
		event = evt;

	}

}
