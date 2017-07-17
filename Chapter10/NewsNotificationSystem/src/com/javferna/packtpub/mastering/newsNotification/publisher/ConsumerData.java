package com.javferna.packtpub.mastering.newsNotification.publisher;

import java.util.concurrent.Flow.Subscriber;

import com.javferna.packtpub.mastering.newsNotification.data.News;

public class ConsumerData {
	
	private Subscriber<News> consumer;
	private MySubscription subscription;
	/**
	 * @return the consumer
	 */
	public Subscriber<News>  getConsumer() {
		return consumer;
	}
	/**
	 * @param consumer the consumer to set
	 */
	public void setConsumer(Subscriber<News> consumer) {
		this.consumer = consumer;
	}
	/**
	 * @return the subscription
	 */
	public MySubscription getSubscription() {
		return subscription;
	}
	/**
	 * @param subscription the subscription to set
	 */
	public void setSubscription(MySubscription subscription) {
		this.subscription = subscription;
	}
	
	

}
