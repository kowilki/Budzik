package eventlib;

import java.util.List;

public class Licznik implements Runnable {
	int number;
	List<Integer> numbers;

	public Licznik(List<Integer> _numbers) {
		this.numbers = _numbers;
	}

	@Override
	public void run() {
		while (true) {
			System.out.println(this.number);
			this.numbers.add(this.number);
			this.number += 1;
		}
	}

}
