package com.javferna.packtpub.mastering.filesearch.main;

import java.io.File;
import java.util.Date;

import com.javferna.packtpub.mastering.filesearch.parallel.group.ParallelGroupFileSearch;
import com.javferna.packtpub.mastering.filesearch.serial.SerialFileSearch;
import com.javferna.packtpub.mastering.filesearch.utils.Result;

public class Main {

	public static void main(String[] args) {

		File file = new File("C:\\Windows\\");
		String regex = "hosts";
		Date start, end;

		Result result = new Result();
		start = new Date();
		SerialFileSearch.searchFiles(file, regex, result);
		end = new Date();


		System.out.printf("Serial Search: Execution Time: %d%n", end.getTime() - start.getTime());

		
		Result parallelResult = new Result();
		start = new Date();
		ParallelGroupFileSearch.searchFiles(file, regex, parallelResult);
		end = new Date();

		System.out.printf("Parallel Group Search: Path: %s%n", parallelResult.getPath());
		System.out.printf("Parallel Group Search: Execution Time: %d%n", end.getTime() - start.getTime());
	}

}
