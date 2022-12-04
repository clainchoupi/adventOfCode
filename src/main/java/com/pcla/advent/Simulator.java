package com.pcla.advent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.pcla.advent.models.FourRangeSection;


public class Simulator {
	private File file;
	
	
	public Simulator(File file) {
		this.file = file;
	}
	
	//------------------------------------------------
	
	public void day4ParseInput1() {
		BufferedReader reader;
		int score=0;
		List<Character> letters = new ArrayList<Character>();
		
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			
			while (line != null) {
				String[] ranges = line.split(",");
				String[] firstValues = ranges[0].split("-");
				String[] secondValues = ranges[1].split("-");
 				FourRangeSection firstRange = new FourRangeSection(Integer.parseInt(firstValues[0]), Integer.parseInt(firstValues[1]));
				FourRangeSection secondRange = new FourRangeSection(Integer.parseInt(secondValues[0]), Integer.parseInt(secondValues[1]));

				if (FourRangeSection.isOverlapping(firstRange, secondRange)){
					score +=1;
				}
				
				// read next group
				line = reader.readLine();
			}
			reader.close();
			System.out.println(letters);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Score day4 part1 = " + score);
	}
	
	//------------------------------------------------
	
	public void day3ParseInput2() {
		BufferedReader reader;
		int score=0;
		List<Character> letters = new ArrayList<Character>();
		
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			String line2, line3;
			
			
			
			while (line != null) {
				// read 2 others lines
				line2 = reader.readLine();
				line3 = reader.readLine();
				letters.add(getIntersectChar(line, line2, line3));
				
				// read next group
				line = reader.readLine();
			}
			reader.close();
			System.out.println(letters);
			
			//On transforme les lettes en int pour avoir leur valeur en ASCII
			for(char c : letters){
				score+=getCharacterPosition(c);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Score day3 part2 = " + score);
		
	}
	
	private Character getIntersectChar(String s1, String s2, String s3){
		//Retourne le character commun pour les 3 listes
		List<Character> charsFirst = s1.chars().mapToObj(e -> (char)e).collect(Collectors.toList());
		List<Character> charsSecond = s2.chars().mapToObj(e -> (char)e).collect(Collectors.toList());
		List<Character> charsThird = s3.chars().mapToObj(e -> (char)e).collect(Collectors.toList());
		return charsFirst.stream().distinct()
		.filter(charsSecond::contains)
		.filter(charsThird ::contains)
		.findFirst().orElseThrow();
	}
	
	public void day3ParseInput1() {
		BufferedReader reader;
		int score=0;
		List<Character> letters = new ArrayList<Character>();
		
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			
			while (line != null) {
				if (! line.isEmpty()){
					String firstAlf = line.substring(0, (line.length()/2));
					String secondAlf = line.substring(line.length()/2, line.length());
					
					List<Character> charsFirst = firstAlf.chars().mapToObj(e -> (char)e).collect(Collectors.toList());
					List<Character> charsSecond = secondAlf.chars().mapToObj(e -> (char)e).collect(Collectors.toList());
					letters.add(charsFirst.stream().distinct().filter(charsSecond::contains).findFirst().orElseThrow());
				}
				// read next line
				line = reader.readLine();
			}
			reader.close();
			
			//On transforme les lettes en int pour avoir leur valeur en ASCII
			for(char c : letters){
				score+=getCharacterPosition(c);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Score day3 = " + score);
		
	}
	
	private int getCharacterPosition(Character c){
		int asciiValue = (int)c;
		int lowerCaseAsciiValue = 96; //for lower case
		int upperCaseAsciiValue = 64; //for upper case
		if(asciiValue<=122 & asciiValue>=97){
			//Les minuscules valent de 1 à 26
			return (asciiValue-lowerCaseAsciiValue);
		}else {
			//Les majuscules valent de 27 à 52 (on rajoute 26 à la position dans l'ordre alphab)
			return (asciiValue-upperCaseAsciiValue+26);
		}
	}
	
	//------------------------------------------------
	
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
		if (CollectionUtils.isNotEmpty(calories)){
			score = calories.stream()
			.sorted(Comparator.reverseOrder())
			.limit(3)
			.reduce(0, (a, b) -> a + b);
		}
		
		System.out.println("Score day1 = " + score);
		
	}
	
}