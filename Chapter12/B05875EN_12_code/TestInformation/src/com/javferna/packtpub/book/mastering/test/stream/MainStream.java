package com.javferna.packtpub.book.mastering.test.stream;

import java.util.stream.IntStream;

public class MainStream {

	public static void main(String[] args) {

		double result=IntStream.range(0,1000)
						.parallel()
						.peek(n -> System.out.println(Thread.currentThread().getName()+": Number "+n))
						.map(n -> n*n)
						.peek(n -> System.out.println(Thread.currentThread().getName()+": Transformer "+n))
						.average()
						.getAsDouble();
		
		System.out.println("Result: "+result);

	}

}
