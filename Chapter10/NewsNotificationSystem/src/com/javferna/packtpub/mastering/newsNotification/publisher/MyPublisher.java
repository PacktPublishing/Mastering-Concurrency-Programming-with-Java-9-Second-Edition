package com.javferna.packtpub.mastering.newsNotification.publisher;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.ThreadPoolExecutor;

import com.javferna.packtpub.mastering.newsNotification.data.News;

public class MyPublisher implements Publisher<News> {

	private ConcurrentLinkedDeque<ConsumerData> consumers;
	private ThreadPoolExecutor executor;
	
	public MyPublisher() {
		consumers=new ConcurrentLinkedDeque<>();
		executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	}
	
	@Override
	public void subscribe(Subscriber<? super News> subscriber) {
		
		ConsumerData consumerData=new ConsumerData();
		consumerData.setConsumer((Subscriber<News>)subscriber);
		
		MySubscription subscription=new MySubscription();
		consumerData.setSubscription(subscription);
		
		subscriber.onSubscribe(subscription);
		
		consumers.add(consumerData);
	}
	
	public void publish(News news) {
		consumers.forEach( consumerData -> {
			try {
				executor.execute(new PublisherTask(consumerData, news));
			} catch (Exception e) {
				consumerData.getConsumer().onError(e);
			}
		});
	}
	
	public void shutdown() {
		consumers.forEach( consumerData -> {
			consumerData.getConsumer().onComplete();
		});
		executor.shutdown();
	}

}
