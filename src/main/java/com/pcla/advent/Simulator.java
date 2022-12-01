package com.pcla.advent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class Simulator {
	private File file;
	
	
	public Simulator(File file) {
		this.file = file;
	}
	
	public int day1ParseInput1() {
		BufferedReader reader;
		int score=0;
		int tempScore=0;
		
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			
			while (line != null) {
				if (! line.isEmpty()){
					tempScore += Integer.parseInt(line);
				}else{
					//RAZ temp et max score
					score = Integer.max(score, tempScore);
					tempScore=0;
				}
				// read next line
				line = reader.readLine();
			}
			
			reader.close();

			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Score= " + score);
		return score;
	}
	
}