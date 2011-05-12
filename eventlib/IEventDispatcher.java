package eventlib;

import eventlib.event.IEvent;

public interface IEventDispatcher extends ITimeProvider {
	public void addEvent(IEvent evt);
	
	
	/**
	 * Removes event from queue
	 * @param evt
	 */
	public void removeEvent(IEvent evt);
	
	
	/**
	 * Starts working simply :)
	 */
	public void startWorking();
	
	

}
