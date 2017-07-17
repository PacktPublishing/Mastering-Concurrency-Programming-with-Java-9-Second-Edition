package com.javferna.packtpub.mastering.eventNotification.consumer;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

import com.javferna.packtpub.mastering.eventNotification.data.Event;

public class Consumer implements Subscriber<Event> {

	private String name;
	private Subscription subscription;
	
	public Consumer (String name) {
		this.name = name;
	}
	
	@Override
	public void onComplete() {
		this.showMessage("No more events");
	}

	@Override
	public void onError(Throwable error) {
		this.showMessage("An error has ocurred");
		error.printStackTrace();
	}

	@Override
	public void onNext(Event event) {
		this.showMessage("An event has arrived: "+event.getSource()+": "+event.getDate()+": "+event.getMsg());
		this.subscription.request(1);
		
		processEvent(event);
		
	}

	private void processEvent(Event event) {
		Random random = new Random();
		
		int number = random.nextInt(3);
		
		try {
			TimeUnit.SECONDS.sleep(number);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription=subscription;
		this.subscription.request(1);
		this.showMessage("Subscription OK");
	}
	
	private void showMessage (String txt) {
		System.out.println(Thread.currentThread().getName()+":"+this.name+": "+txt);
	}

}
