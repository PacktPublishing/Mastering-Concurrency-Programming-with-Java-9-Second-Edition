package com.javferna.packtpub.mastering.matrixmultiplier.util;

import java.util.Random;

public class MatrixGenerator {

	public static double[][] generate (int rows, int columns) {
		double[][] ret=new double[rows][columns];
		Random random=new Random();
		for (int i=0; i<rows; i++) {
			for (int j=0; j<columns; j++) {
				ret[i][j]=random.nextDouble()*10;
			}
		}
		return ret;
	}
}
