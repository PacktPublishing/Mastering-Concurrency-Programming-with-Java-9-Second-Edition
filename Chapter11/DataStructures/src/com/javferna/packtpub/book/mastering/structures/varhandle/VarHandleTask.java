package com.javferna.packtpub.book.mastering.structures.varhandle;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

;

public class VarHandleTask implements Runnable {

	private VarHandleData data;
	
	public VarHandleTask(VarHandleData data) {
		this.data = data;
	}
	
	@Override
	public void run() {

		VarHandle handler;
		try {
			handler = MethodHandles.lookup().in(VarHandleData.class).findVarHandle(VarHandleData.class, "safeValue", double.class);
			for (int i = 0; i < 10000; i++) {
				handler.getAndAdd(data, +100);
				data.unsafeValue += 100;
				handler.getAndAdd(data, -100);
				data.unsafeValue -= 100;
			}
		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}

}
