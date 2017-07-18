package com.javferna.packtpub.book.mastering.structures.atomic.java8;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;

public class ConcurrentMainAtomic {

	public static void main(String[] args) {
		
		LongAccumulator accumulator=new LongAccumulator((x,y) -> x*y, 1);
		
		IntStream.range(1, 10).parallel().forEach(x -> accumulator.accumulate(x));

		System.out.println(accumulator.get());
	}

}
