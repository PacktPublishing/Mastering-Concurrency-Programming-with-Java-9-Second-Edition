package com.javferna.packtpub.mastering.geneticAlgorithm.concurrent;

import java.util.Arrays;
import java.util.concurrent.Phaser;

public class GeneticPhaser extends Phaser {
	
	private SharedData data;

	public GeneticPhaser(int parties, SharedData data) {
		super(parties);
		this.data=data;
	}

	@Override
	protected boolean onAdvance(int phase, int registeredParties) {
		int realPhase=phase%3;
		if (registeredParties>0) {
			switch (realPhase) {
			case 0:
			case 1:
				data.getIndex().set(0);
				break;
			case 2:
				Arrays.sort(data.getPopulation());
				if  (data.getPopulation()[0].getValue() < data.getBest().getValue()) {
					data.setBest(data.getPopulation()[0]);
				}
				break;
			}
			return false;
		}
		return true;
	}

	public SharedData getData() {
		return data;
	}

	public void setData(SharedData data) {
		this.data = data;
	}
	
	
	
	

}
