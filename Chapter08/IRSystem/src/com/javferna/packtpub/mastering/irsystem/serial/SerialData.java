package com.javferna.packtpub.mastering.irsystem.serial;

import com.javferna.packtpub.mastering.irsystem.common.Token;

public class SerialData {
	
	public static void getWordsInFile1(String fileName, SerialInvertedIndex index) {
		long value = index
				.getIndex()
				.stream()
				.filter(token -> fileName.equals(token.getFile()))
				.count();
		System.out.println("Words in File "+fileName+": "+value);
	}


	public static void getWordsInFile2(String fileName, SerialInvertedIndex index) {

		long value = index
				.getIndex()
				.stream()
				.filter(token -> fileName.equals(token.getFile()))
				.reduce(0, (n,t) -> n+1, (n1,n2) -> n1+n2);
		System.out.println("Words in File "+fileName+": "+value);
	}
	
	public static void getAverageTfxidf(String fileName, SerialInvertedIndex index) {

		long wordCounter = index
				.getIndex()
				.stream()
				.filter(token -> fileName.equals(token.getFile()))
				.reduce(0, (n,t) -> n+1, (n1,n2) -> n1+n2);
		
		double tfxidf = index
				.getIndex()
				.stream()
				.filter(token -> fileName.equals(token.getFile()))
				.reduce(0d, (n,t) -> n+t.getTfxidf(), (n1,n2) -> n1+n2);
		
		System.out.println("Words in File "+fileName+": "+(tfxidf/wordCounter));
	}

	public static void maxTfxidf(SerialInvertedIndex index) {
		Token token = index
				.getIndex()
				.stream()
				.reduce(new Token("", "xxx:0"), (t1, t2) -> {
					if (t1.getTfxidf()>t2.getTfxidf()) {
						return t1;
					} else {
						return t2;
					}
				});
		System.out.println(token.toString());
	}
	
	public static void minTfxidf(SerialInvertedIndex index) {
		Token token = index
				.getIndex()
				.stream()
				.reduce(new Token("", "xxx:1000000"), (t1, t2) -> {
					if (t1.getTfxidf()<t2.getTfxidf()) {
						return t1;
					} else {
						return t2;
					}
				});
		System.out.println(token.toString());
	}
	
	
}
