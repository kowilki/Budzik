package eventlib.event;

import eventlib.ITimeProvider;
import eventlib.ThreadStarter;
import eventlib.job.IJob;

public abstract class Event implements IEvent {
	public long nextTimeCall;

	ITimeProvider time_provider;

	IJobStarter job_starter = new ThreadStarter(); // by default ThreadStarted
													// is used

	IJob job;

	@Override
	public void setTimeProvider(ITimeProvider tp) {
		time_provider = tp;
	}

	@Override
	public ITimeProvider getTimeProvider() {
		return time_provider;
	}

	@Override
	public void setIJob(IJob _job) {
		job = _job;
	}

	@Override
	public int compareTo(IEvent o) {
		return new Long(getNearestCallTime()).compareTo(new Long(o.getNearestCallTime()));
	}


}
