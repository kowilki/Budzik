package controller;

import eventlib.EventDispatcher;
import eventlib.IEventDispatcher;
import eventlib.event.PeriodicEvent;
import eventlib.job.IJob;
import eventlib.job.LogMemoryUsage;
import eventlib.job.LogProcessorUsage;
import eventlib.job.PlayMidiJob;

public class EventManager {
	static EventManager _instance = null;
	private IEventDispatcher dispatcher;

	private EventManager() {
		dispatcher = new EventDispatcher();
		dispatcher.startWorking();
	}

	public static EventManager getInstance() {
		if (_instance == null) {
			_instance = new EventManager();

		}
		return _instance;
	}

	/**
	 * Adds new event for playing midi file at some interval
	 * 
	 * @param seconds
	 *            interval in seconds
	 * @param filepath
	 *            path to the file
	 */
	public void addMidi(int seconds, String filepath) {
		IJob job = new PlayMidiJob(filepath);
		PeriodicEvent evt = new PeriodicEvent(job);
		job.setEvent(evt);
		evt.nextTimeCall = dispatcher.getTime();
		evt.period = seconds;
		dispatcher.addEvent(evt);
	}
	
	public void logMemoryUsage(int interval){
		IJob job = new LogMemoryUsage();
		PeriodicEvent evt = new PeriodicEvent(job);
		job.setEvent(evt);
		evt.nextTimeCall = dispatcher.getTime();
		evt.period = interval;
		dispatcher.addEvent(evt);		
	}
	public void logProcessorUsage(int interval){
		IJob job = new LogProcessorUsage();
		PeriodicEvent evt = new PeriodicEvent(job);
		job.setEvent(evt);
		evt.nextTimeCall = dispatcher.getTime();
		evt.period = interval;
		dispatcher.addEvent(evt);		
	}
}
