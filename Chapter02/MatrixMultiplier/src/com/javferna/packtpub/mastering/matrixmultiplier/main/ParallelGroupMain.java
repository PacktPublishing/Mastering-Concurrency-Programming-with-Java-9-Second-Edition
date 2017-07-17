package com.javferna.packtpub.mastering.matrixmultiplier.main;

import java.util.Date;

import com.javferna.packtpub.mastering.matrixmultiplier.parallel.group.ParallelGroupMultiplier;
import com.javferna.packtpub.mastering.matrixmultiplier.util.MatrixGenerator;

public class ParallelGroupMain {
	
	public static void main (String[] args) {
		double matrix1[][] = MatrixGenerator.generate(2000, 2000);
		double matrix2[][] = MatrixGenerator.generate(2000, 2000);
		
		double resultParallelGroup[][]= new double[matrix1.length][matrix2[0].length];
		Date start, end;
		
		System.out.println("Start");
		start=new Date();
		ParallelGroupMultiplier.multiply(matrix1, matrix2, resultParallelGroup);
		end=new Date();
		System.out.printf("Parallel Group: %d%n",end.getTime()-start.getTime());

	}

}
