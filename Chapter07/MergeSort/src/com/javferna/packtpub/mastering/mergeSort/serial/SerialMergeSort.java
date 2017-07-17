package com.javferna.packtpub.mastering.mergeSort.serial;

public class SerialMergeSort {

	public void mergeSort (Comparable data[], int start, int end) {
		if (end-start < 2) { 
			return;
		}
		int middle= (end+start)>>>1;
		mergeSort(data,start,middle);
		mergeSort(data,middle,end);
		merge(data,start,middle,end);
	}

	private void merge(Comparable[] data, int start, int middle, int end) {
		int length=end-start+1;
		Comparable[] tmp=new Comparable[length];
		
		int i, j, index;
		i=start;
		j=middle;
		index=0;
		
		while ((i<middle) && (j<end)) {
			if (data[i].compareTo(data[j])<=0) {
				tmp[index]=data[i];
				i++;
			} else {
				tmp[index]=data[j];
				j++;
			}
			index++;
		}
		
		while (i<middle) {
			tmp[index]=data[i];
			i++;
			index++;
		}

		while (j<end) {
			tmp[index]=data[j];
			j++;
			index++;
		}
		
		for (index=0; index < (end-start); index++) {
			data[index+start]=tmp[index];
		}
	}
}
