package com.javferna.packtpub.mastering.testing.main;

import com.javferna.packtpub.mastering.testing.common.Data;
import com.javferna.packtpub.mastering.testing.tc.TestClassKo;

import edu.umd.cs.mtc.TestFramework;

public class MainKo {

	public static void main(String[] args) {
		
		Data data=new Data();
		data.setData(10);
		TestClassKo ko=new TestClassKo(data,10);

		try {
			TestFramework.runOnce(ko);
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

}
