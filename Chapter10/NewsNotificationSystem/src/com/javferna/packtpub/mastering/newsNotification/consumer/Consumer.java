package com.javferna.packtpub.mastering.newsNotification.consumer;

import java.util.Set;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

import com.javferna.packtpub.mastering.newsNotification.data.News;
import com.javferna.packtpub.mastering.newsNotification.publisher.MySubscription;

public class Consumer implements Subscriber<News> {

	private MySubscription subscription;
	private String name;
	private Set<Integer> categories;
	
	public Consumer(String name, Set<Integer> categories) {
		this.name=name;
		this.categories = categories;
	}

	@Override
	public void onComplete() {
		System.out.printf("%s - %s: Consumer - Completed\n", name,Thread.currentThread().getName());
	}

	@Override
	public void onError(Throwable exception) {
		System.out.printf("%s - %s: Consumer - Error: %s\n", name, Thread.currentThread().getName(), exception.getMessage());
	}

	@Override
	public void onNext(News item) {
		System.out.printf("%s - %s: Consumer - News\n", name, Thread.currentThread().getName());
		System.out.printf("%s - %s: Text: %s\n", name, Thread.currentThread().getName(),item.getTxt());
		System.out.printf("%s - %s: Category: %s\n", name, Thread.currentThread().getName(),item.getCategory());
		System.out.printf("%s - %s: Date: %s\n", name, Thread.currentThread().getName(),item.getDate());
		subscription.request(1);
	}

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = (MySubscription)subscription;
		this.subscription.setCategories(this.categories);
		this.subscription.request(1);
		System.out.printf("%s: Consumer - Subscription\n",Thread.currentThread().getName());
	}

}
