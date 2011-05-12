package eventlib;

import eventlib.event.IJobStarter;
import eventlib.job.IJob;

public class ThreadStarter implements IJobStarter{

	@Override
	public void startJob(IJob job) {
		new Thread(job).start();	
		
	}

}
