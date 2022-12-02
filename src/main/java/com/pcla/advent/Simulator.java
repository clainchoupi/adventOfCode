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

	public void day2ParseInput1() {
		BufferedReader reader;
		int score=0;
		
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			
			while (line != null) {
				String[] inputs = line.split(" ");
				String opponent = inputs[0];
				String me = inputs[1];
				int win = 6;
				int draw = 3;
				int lost = 0;
				int rock = 1;
				int paper = 2;
				int scissors = 3;

				if ("A".equalsIgnoreCase(opponent)){
					if("X".equalsIgnoreCase(me)){score += lost + scissors;}
					if("Y".equalsIgnoreCase(me)){score += draw + rock;}
					if("Z".equalsIgnoreCase(me)){score += win + paper;}
				}else if ("B".equalsIgnoreCase(opponent)){
					if("X".equalsIgnoreCase(me)){score += lost + rock;}
					if("Y".equalsIgnoreCase(me)){score += draw + paper;}
					if("Z".equalsIgnoreCase(me)){score += win + scissors;}
				}else {
					//C
					if("X".equalsIgnoreCase(me)){score += lost + paper;}
					if("Y".equalsIgnoreCase(me)){score += draw + scissors;}
					if("Z".equalsIgnoreCase(me)){score += win + rock;}
				}
				
				// read next line
				line = reader.readLine();
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}


		System.out.println("Score day2 = " + score);

	}

	//------------------------------------------------
	
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

		System.out.println("Score day1 = " + score);

	}
	
}