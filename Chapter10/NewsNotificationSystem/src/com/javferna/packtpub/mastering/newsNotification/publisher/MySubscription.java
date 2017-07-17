package com.javferna.packtpub.mastering.newsNotification.publisher;

import java.util.Set;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.atomic.AtomicLong;

public class MySubscription implements Subscription {
	
	private boolean cancelled=false;
	private AtomicLong requested = new AtomicLong(0);
	private Set<Integer> categories;
	
	@Override
	public void cancel() {
		cancelled=true;
	}

	@Override
	public void request(long value) {
		requested.addAndGet(value);
	}

	/**
	 * @return the canceled
	 */
	public boolean isCancelled() {
		return cancelled;
	}

	/**
	 * @return the requested
	 */
	public long getRequested() {
		return requested.get();
	}

	public void decreaseRequested() {
		requested.decrementAndGet();		
	}
	
	public void setCategories(Set<Integer> categories) {
		this.categories=categories;
	}
	
	public boolean hasCategory (int category) {
		return categories.contains(category);
	}

}
