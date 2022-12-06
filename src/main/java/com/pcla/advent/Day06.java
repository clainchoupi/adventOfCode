package com.pcla.advent;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;

public class Day06 {
    public static void main(String[] args){
        File input1 = new File("src/main/resources/DAY/06.txt");
        day6ParseInput1(input1);
    }
    
    private static void day6ParseInput1(File file) {
        int position=0;
        int result=0;
        
        try {
            Scanner reader = new Scanner(file);
            String line = reader.nextLine();
            
            // Iterate over the characters in the string
            for (char c : line.toCharArray()) {
                HashSet<Character> tempSet = new HashSet<Character>();
                tempSet.add(c);
                tempSet.add(line.charAt(position +1));
                tempSet.add(line.charAt(position +2));
                tempSet.add(line.charAt(position +3));
                tempSet.add(line.charAt(position +4));
                tempSet.add(line.charAt(position +5));
                tempSet.add(line.charAt(position +6));
                tempSet.add(line.charAt(position +7));
                tempSet.add(line.charAt(position +8));
                tempSet.add(line.charAt(position +9));
                tempSet.add(line.charAt(position +10));
                tempSet.add(line.charAt(position +11));
                tempSet.add(line.charAt(position +12));
                tempSet.add(line.charAt(position +13));
                // Check if the four most recently received characters are all different
                if (tempSet.size() == 14){
                    //Found
                    result=position;
                    break;
                }
                
                // Increment pos by 1
                position++;
            }
            
            // After the loop has finished, the first position where the four most recently received characters are all different will be stored in result
            reader.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        result+=14;
        
        System.out.println("Score day6 part1 = " + result);
    }
    
}
