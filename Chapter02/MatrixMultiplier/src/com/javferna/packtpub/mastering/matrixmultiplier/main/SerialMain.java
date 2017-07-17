package com.javferna.packtpub.mastering.matrixmultiplier.main;

import java.util.Date;

import com.javferna.packtpub.mastering.matrixmultiplier.serial.SerialMultiplier;
import com.javferna.packtpub.mastering.matrixmultiplier.util.MatrixGenerator;

public class SerialMain {

	public static void main(String[] args) {
		
		double matrix1[][] = MatrixGenerator.generate(2000, 2000);
		double matrix2[][] = MatrixGenerator.generate(2000, 2000);
		double resultSerial[][]= new double[matrix1.length][matrix2[0].length];
	
		Date start, end;
		
		start=new Date();
		SerialMultiplier.multiply(matrix1, matrix2, resultSerial);
		end=new Date();
		System.out.printf("Serial: %d%n",end.getTime()-start.getTime());

	}

}
