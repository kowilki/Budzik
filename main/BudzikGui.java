package main;

import eventlib.EventDispatcher;
import eventlib.IEventDispatcher;
import eventlib.ITimeProvider;
import eventlib.event.IEvent;
import eventlib.event.PeriodicEvent;
import eventlib.job.IJob;
import gui.MainWindow;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BudzikGui {

	/**
	 * @param args
	 */
	public static void testED() {
		final IEventDispatcher eventDispatcher = new EventDispatcher();

		PeriodicEvent evt1 = new PeriodicEvent(new IJob() {
			@Override
			public void run() {
				Long time = eventDispatcher.getTime();
				Date d = new Date(time);

				@SuppressWarnings("deprecation")
				String tstr = d.toGMTString();
				System.out.println("EVENT 1   " + tstr);
			}

			@Override
			public void setEvent(IEvent evt) {
			}
		});

		PeriodicEvent evt2 = new PeriodicEvent(new IJob() {

			@Override
			public void run() {
				Long time = eventDispatcher.getTime();
				Date d = new Date(time);

				@SuppressWarnings("deprecation")
				String tstr = d.toGMTString();
				System.out.println("EVENT 2   " + tstr);
			}

			@Override
			public void setEvent(IEvent evt) {
				// TODO Auto-generated method stub

			}
		});

		evt1.nextTimeCall = ((ITimeProvider) eventDispatcher).getTime() + 1 * 1000;
		evt2.nextTimeCall = ((ITimeProvider) eventDispatcher).getTime() + 2 * 1000;
		eventDispatcher.addEvent(evt1);
		eventDispatcher.addEvent(evt2);
		evt1.period = 5;
		evt2.period = 10;
		eventDispatcher.startWorking();
	}

	public static void main(String[] args) {
		new MainWindow();		
	}
	/*
	 * public static void main(String[] args) { List<Integer> numbers = new
	 * ArrayList<Integer>(10 * 1000); Licznik licznik = new Licznik(numbers);
	 * 
	 * Thread t1 = new Thread(licznik); Thread t2 = new Thread(licznik);
	 * 
	 * // t1.start(); // t2.start(); int checkedToEl = -1; numbers.add(2);
	 * numbers.add(2); while (true) { int last = -1; if (numbers.size() > 0) {
	 * last = numbers.get(0); } else { continue; }
	 * 
	 * for (int i = 1; i < numbers.size(); i++) { int num = numbers.get(i); if
	 * (last == num) { throw new NullPointerException(); } last = num; } }
	 * 
	 * }
	 */
}
