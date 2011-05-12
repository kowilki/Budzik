package eventlib.job;

import java.util.Date;

import eventlib.ITimeProvider;

public class PlayMidiJob extends BaseJob {

	String filepath;

	public PlayMidiJob(String _filepath) {
		filepath = _filepath;
	}

	@Override
	public void run() {
		ITimeProvider tp = event.getTimeProvider();
		long _time = tp.getTime();
		Date date = new Date(_time);
		System.out.println("Playing: " + filepath + " time: "
				+ date.toGMTString()+ " in milis: " + _time);
		 
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return filepath;
	}

}
