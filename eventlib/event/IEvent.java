package eventlib.event;

import eventlib.ITimeProvider;
import eventlib.job.IJob;

/**
 * Event instances should check by themselves if the same event instance has
 * created a RUnnable object that is running at the moment - in other words,
 * the synchronization of many parallel event's running is to event instances
 * 
 * 
 */
public interface IEvent extends Comparable<IEvent>{

	/***
	 * Informs about nearest time that the event should be fired, as number of
	 * milliseconds since epoch
	 * 
	 * @return next time the event should be called
	 */
	public long getNearestCallTime();
	


	public boolean isRunning();
	/***
	 * Calculates new time of execution of the event
	 */
	public void calculateNextCall();
	
	/**
	 * starts a new job, in thread/process/same thread, depending on the implementation
	 */
	public void startJob();
	
	public void setTimeProvider(ITimeProvider tp);
	
	public ITimeProvider getTimeProvider();
	
	public void setIJob(IJob _job);
	
	/**
	 * Informs whether event is possible to be fired at the future
	 * @return
	 */
	public boolean canBeRunInTheFuture();
}
