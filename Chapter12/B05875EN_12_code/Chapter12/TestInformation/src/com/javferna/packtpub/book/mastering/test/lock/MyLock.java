package com.javferna.packtpub.book.mastering.test.lock;

import java.util.Collection;
import java.util.concurrent.locks.ReentrantLock;

public class MyLock extends ReentrantLock {

	private static final long serialVersionUID = 8025713657321635686L;

	public String getOwnerName() {
		if (this.getOwner() == null) {
			return "None";
		}
		return this.getOwner().getName();
	}

	public Collection<Thread> getThreads() {
		return this.getQueuedThreads();
	}
}
