package eventlib.event;

import eventlib.job.IJob;

public class PeriodicEvent extends Event {
	long lastRun;
	boolean is_running = false;
	public long period = 2;

	void increaseNextCall() {
		nextTimeCall = time_provider.getTime() + period * 1000;
	}

	public PeriodicEvent(IJob _ijob) {
		job = _ijob;
		job.setEvent(this);
	}

	@Override
	public void startJob() {
		System.out.println(this.toString() + " : ");
		lastRun = time_provider.getTime();
		job_starter.startJob(job);
		increaseNextCall();
	}

	@Override
	public long getNearestCallTime() {
		return nextTimeCall;
	}

	@Override
	public boolean isRunning() {
		return is_running;
	}

	@Override
	public void calculateNextCall() {

	}

	@Override
	public boolean canBeRunInTheFuture() {
		return true;
	}

	@Override
	public String toString() {
		return "perdiod: " + String.valueOf(period) + "\t nextTimeCall: "
				+ String.valueOf(getNearestCallTime()) + " job: "
				+ job.toString();
	}
}
