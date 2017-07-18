package com.javferna.packtpub.mastering.testing.main;

import com.javferna.packtpub.mastering.testing.common.Data;
import com.javferna.packtpub.mastering.testing.tc.TestClassOk;

import edu.umd.cs.mtc.TestFramework;

public class MainOk {

	public static void main(String[] args) {
		
		Data data=new Data();
		data.setData(10);
		TestClassOk ok=new TestClassOk(data,10);

		try {
			TestFramework.runOnce(ok);
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

}
