package com.javferna.packtpub.mastering.geneticAlgorithm.concurrent;

import com.javferna.packtpub.mastering.geneticAlgorithm.common.GeneticOperators;
import com.javferna.packtpub.mastering.geneticAlgorithm.common.Individual;

public class ConcurrentGeneticAlgorithm {

	private int numberOfGenerations;
	private int numberOfIndividuals;
	private int[][] distanceMatrix;
	private int size;
	
	public ConcurrentGeneticAlgorithm(int[][] distanceMatrix, int numberOfGenerations, int numberOfIndividuals) {
		this.distanceMatrix=distanceMatrix;
		this.numberOfGenerations=numberOfGenerations;
		this.numberOfIndividuals=numberOfIndividuals;
		size=distanceMatrix.length;
	}
	
	public Individual calculate() {

		Individual[] population=GeneticOperators.initialize(numberOfIndividuals,size);
		GeneticOperators.evaluate(population,distanceMatrix);

		SharedData data=new SharedData();
		data.setPopulation(population);
		data.setDistanceMatrix(distanceMatrix);
		data.setBest(population[0]);
		
		int numTasks=Runtime.getRuntime().availableProcessors();
		GeneticPhaser phaser=new GeneticPhaser(numTasks,data);
		
		ConcurrentGeneticTask[] tasks=new ConcurrentGeneticTask[numTasks];
		Thread[] threads=new Thread[numTasks];
		
		tasks[0]=new ConcurrentGeneticTask(phaser, numberOfGenerations, true);
		for (int i=1; i< numTasks; i++) {
			tasks[i]=new ConcurrentGeneticTask(phaser, numberOfGenerations, false);
		}
		
		for (int i=0; i<numTasks; i++) {
			threads[i]=new Thread(tasks[i]);
			threads[i].start();
		}
		
		for (int i=0; i<numTasks; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		return data.getBest();
	}
}
