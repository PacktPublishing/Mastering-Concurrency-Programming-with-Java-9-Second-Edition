package com.javferna.packtpub.mastering.testing.tc;

import com.javferna.packtpub.mastering.testing.common.Data;

import edu.umd.cs.mtc.MultithreadedTestCase;

public class TestClassOk extends MultithreadedTestCase {

	
	private Data data;
	private int amount;
	private int initialData;
	
	public TestClassOk (Data data, int amount) {
		this.amount=amount;
		this.data=data;
		this.initialData=data.getData();
	}
	
	@Override
	public void initialize() {
		super.initialize();
	}

	
	public void threadAdd() {
		System.out.println("Add: Getting the data");
		int value=data.getData();
		System.out.println("Add: Increment the data");
		value+=amount;
		System.out.println("Add: Set the data");
		data.setData(value);
	}
	
	
	public void threadSub() {
		waitForTick(1);
		System.out.println("Sub: Getting the data");
		int value=data.getData();
		System.out.println("Sub: Decrement the data");
		value-=amount;
		System.out.println("Sub: Set the data");
		data.setData(value);		
	}
	
	@Override
	public void finish() {
		super.finish();
		assertEquals(initialData, data.getData());
	}



	
	
}
