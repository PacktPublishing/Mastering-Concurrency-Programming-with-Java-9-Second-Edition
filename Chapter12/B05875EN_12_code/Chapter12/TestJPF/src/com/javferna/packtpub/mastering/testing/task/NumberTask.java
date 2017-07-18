package com.javferna.packtpub.mastering.testing.task;

import com.javferna.packtpub.mastering.testing.common.Data;

public class NumberTask implements Runnable {

	private Data data;
	
	public NumberTask (Data data) {
		this.data=data;
	}
	
	@Override
	public void run() {

		for (int i=0; i<10; i++) {
			data.increment(10);
		}
	}

}
