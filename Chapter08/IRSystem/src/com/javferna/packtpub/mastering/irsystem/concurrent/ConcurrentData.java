package com.javferna.packtpub.mastering.irsystem.concurrent;

import com.javferna.packtpub.mastering.irsystem.common.Token;

public class ConcurrentData {
	
	public static void getWordsInFile1(String fileName, ConcurrentInvertedIndex index) {
		long value = index
				.getIndex()
				.parallelStream()
				.filter(token -> fileName.equals(token.getFile()))
				.count();
		System.out.println("Words in File "+fileName+": "+value);
	}


	public static void getWordsInFile2(String fileName, ConcurrentInvertedIndex index) {

		long value = index
				.getIndex()
				.parallelStream()
				.filter(token -> fileName.equals(token.getFile()))
				.mapToLong(token -> 1)
				.reduce(0, Long::sum);
		System.out.println("Words in File "+fileName+": "+value);
	}
	
	public static void getAverageTfxidf(String fileName, ConcurrentInvertedIndex index) {

		long wordCounter = index
				.getIndex()
				.parallelStream()
				.filter(token -> fileName.equals(token.getFile()))
				.mapToLong(token -> 1)
				.reduce(0, Long::sum);
		
		double tfxidf = index
				.getIndex()
				.parallelStream()
				.filter(token -> fileName.equals(token.getFile()))
				.reduce(0d, (n,t) -> n+t.getTfxidf(), (n1,n2) -> n1+n2);
		
		System.out.println("Words in File "+fileName+": "+(tfxidf/wordCounter));
	}

	public static void maxTfxidf(ConcurrentInvertedIndex index) {
		Token token = index
				.getIndex()
				.parallelStream()
				.reduce(new Token("", "xxx:0"), (t1, t2) -> {
					if (t1.getTfxidf()>t2.getTfxidf()) {
						return t1;
					} else {
						return t2;
					}
				});
		System.out.println(token.toString());
	}
	
	public static void minTfxidf(ConcurrentInvertedIndex index) {
		Token token = index
				.getIndex()
				.parallelStream()
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
