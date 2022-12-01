package com.pcla.advent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


public class Simulator {
	private File file;
	
	
	public Simulator(File file) {
		this.file = file;
	}
	
	public void day1ParseInput1() {
		BufferedReader reader;
		int score=0;
		int tempScore=0;
		List<Integer> calories = new ArrayList<>();
		
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			
			while (line != null) {
				if (! line.isEmpty()){
					tempScore += Integer.parseInt(line);
				}else{
					//Ajout dans calories
					calories.add(tempScore);
					tempScore=0;
				}
				// read next line
				line = reader.readLine();
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		//Garder les 3 meilleures calories
		if (calories != null && !calories.isEmpty()){
			List<Integer> listCalories = calories.stream()
				.sorted(Comparator.reverseOrder())
				.limit(3)
				.collect(Collectors.toList());
			Iterator<Integer> it = listCalories.iterator();
			while (it.hasNext()){
				Integer i =  it.next();
				score += i;
			}

		}

		System.out.println("Score = " + score);

	}
	
}