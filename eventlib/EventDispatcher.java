package eventlib;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import eventlib.event.IEvent;

/**
 * Manages all events
 * 
 * Runs as a separate thread TODO: maybe parametrize {@link EventDispatcher}
 * with some objects that would determine manner of running EventDispatcher
 **/
public class EventDispatcher extends Thread implements IEventDispatcher {

	// Queue<IEvent> events = new PriorityQueue<IEvent>();// events are sorte
	Set<IEvent> events = new HashSet<IEvent>();

	public boolean wantsAdd = false;

	public static Boolean wantsAddLock = false;

	/**
	 * Calculates actual time
	 * 
	 * @return actual time as milliseconds from epoch
	 */
	public long getTime() {
		return Calendar.getInstance().getTimeInMillis();
	}

	public EventDispatcher() {

	}

	@Override
	public void addEvent(IEvent evt) {
		synchronized (EventDispatcher.wantsAddLock) {
			wantsAdd = true;
			doAddEvent(evt);
		}
	}

	private synchronized void doAddEvent(IEvent evt) {
		/*
		 * if (events.contains(evt)) { throw new NullPointerException(
		 * "Trying to add event that already is in the set"); }
		 */
		evt.setTimeProvider(this);
		events.add(evt);
		System.out.println("before notifyAll");
		wantsAdd = false;
		notifyAll();
		System.out.println("after notifyAll");
	}

	public synchronized void dOstartWorking() {
		try {
			while (true) {
				if (wantsAdd) {
					wait();
				}

				IEvent[] _events = (IEvent[]) events.toArray(new IEvent[events
						.size()]);
				Arrays.sort(_events);

				if (_events.length == 0) {
					/*
					 * if there are no elements - then make thread to wait for
					 * notification
					 */
					System.out.println("before wait");
					wait();
					System.out.println("after wait");
				}

				for (int i = 0; i < _events.length; i++) {
					IEvent evt = _events[i];

					long act_time = getTime();
					long evt_time = evt.getNearestCallTime();
					long diff = evt_time - act_time;

					if (diff <= 0) {
						evt.startJob();
						if (!evt.canBeRunInTheFuture()) {
							events.remove(evt);
						}
					} else {
						/*
						 * now we're sure that all events in
						 * _events[i:len(_events)] have to start later than now
						 */
						long sleep_time = getSleepTime(_events);
						sleep(sleep_time);

						/*
						 * very important to break after wake up :)
						 */
						break;
					}
				}
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	long getSleepTime(IEvent[] _events) {
		/*
		 * sort events so that the one with lowest next happening time would be
		 * first TODO: find the first that can be run in future (for events that
		 * should run once, but otoh it should be removed then in doWork..sth
		 */
		Arrays.sort(_events);

		/*
		 * take the event that has to happen in the nearest future
		 */
		IEvent __evt = _events[0];
		long __diff = __evt.getNearestCallTime() - getTime();
		return __diff < 0 ? 0 : __diff;
	}

	@Override
	public void run() {
		super.run();
		dOstartWorking();
	};

	@Override
	public void removeEvent(IEvent evt) {
		boolean ret = events.remove(evt);
		if (!ret) {
			throw new NullPointerException(
					"trying to remove event that is not at the set");
		}
	}

	@Override
	public void startWorking() {
		start();
	}

}
