package eventlib.job;

import db.DBLayer;

public class LogProcessorUsage extends BaseJob {

	@Override
	public void run() {
		long free_mem = getMemoryUsage();
		String values = "(" + String.valueOf(free_mem) + ")";
		DBLayer.InsertData("cpu_usage", values);
	}

	public long getMemoryUsage() {
		Runtime r = Runtime.getRuntime();
		return r.totalMemory() - r.freeMemory();
	}

}
