package com.javferna.packtpub.mastering.geneticAlgorithm.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GeneticOperators {

	public static Individual[] crossover(Individual[] selected, int numberOfIndividuals, int size) {

		Individual population[] = new Individual[numberOfIndividuals];
		Random rm = new Random(System.nanoTime());

		for (int i = 0; i < numberOfIndividuals / 2; i++) {
			population[2 * i] = new Individual(size);
			population[2 * i + 1] = new Individual(size);

			int p1Index = rm.nextInt(selected.length);
			int p2Index;
			do {
				p2Index = rm.nextInt(selected.length);
			} while (p1Index == p2Index);

			Individual parent1 = selected[p1Index];
			Individual parent2 = selected[p2Index];

			crossover(parent1, parent2, population[2 * i], population[2 * i + 1]);
		}

		return population;
	}

	public static void crossover(final Individual parent1, final Individual parent2, final Individual individual1,
			final Individual individual2) {

		List<Integer> p1 = Arrays.asList(parent1.getChromosomes());
		List<Integer> p2 = Arrays.asList(parent2.getChromosomes());
		List<Integer> ch1 = new ArrayList<Integer>(p1.size());
		List<Integer> ch2 = new ArrayList<Integer>(p1.size());
		int size = p1.size();

		Random random = new Random();

		int number1 = random.nextInt(size - 1);
		int number2;
		
		do {
			number2 = random.nextInt(size);
		} while (number2==number1);

		int start = Math.min(number1, number2);
		int end = Math.max(number1, number2);
		ch1.addAll(p1.subList(start, end));
		ch2.addAll(p2.subList(start, end));

		int currentCity = 0;
		int currentCityParent1 = 0;
		int currentCityParent2 = 0;
		for (int i = 0; i < size; i++) {

			currentCity = (end + i) % size;

			currentCityParent1 = p1.get(currentCity);
			currentCityParent2 = p2.get(currentCity);

			if (!ch1.contains(currentCityParent2)) {
				ch1.add(currentCityParent2);
			} 

			if (!ch2.contains(currentCityParent1)) {
				ch2.add(currentCityParent1);
			} 
		}

		Collections.rotate(ch1, start);
		Collections.rotate(ch2, start);
		individual1.setChromosomes(ch1.toArray(individual1.getChromosomes()));
		individual2.setChromosomes(ch2.toArray(individual2.getChromosomes()));
	}

	public static Individual[] selection(Individual[] population) {

		Individual selected[] = new Individual[population.length / 2];

		for (int i = 0; i < selected.length; i++) {
			selected[i] = new Individual(population[i]);
		}

		return selected;
	}

	public static Individual[] initialize(int numberOfIndividuals, int size) {

		Individual population[] = new Individual[numberOfIndividuals];

		for (int i = 0; i < numberOfIndividuals; i++) {
			population[i] = new Individual(size);
			initialize(population[i].getChromosomes());
		}

		return population;
	}

	public static void evaluate(Individual[] population, int[][] distanceMatrix) {

		for (Individual individual : population) {
			evaluate(individual, distanceMatrix);
		}
		Arrays.sort(population);
	}

	public static void evaluate(Individual individual, int[][] distanceMatrix) {

		Integer chromosomes[] = individual.getChromosomes();
		int totalDistance = 0;
		int source, destination;

		for (int i = 0; i < chromosomes.length - 1; i++) {
			source = chromosomes[i];
			destination = chromosomes[i + 1];
			totalDistance += distanceMatrix[source][destination];
		}
		source = chromosomes[chromosomes.length - 1];
		destination = chromosomes[0];
		totalDistance += distanceMatrix[source][destination];

		individual.setValue(totalDistance);
	}

	public static void initialize(Integer[] chromosomes) {

		int size = chromosomes.length;
		List<Integer> values = new ArrayList<Integer>(size);

		for (int i = 0; i < size; i++) {
			values.add(i);
		}
		Collections.shuffle(values, new Random(System.nanoTime()));

		for (int i = 0; i < size; i++) {
			chromosomes[i] = values.get(i);
		}

	}

}
