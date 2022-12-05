package com.pcla.advent;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class Day06 {
    public static void main(String[] args){
        File input1 = new File("src/main/resources/DAY/06.txt");
        day6ParseInput1(input1);
    }

    private static void day6ParseInput1(File file) {
        int score=0;
        HashMap<Integer, String> piles = new HashMap();
        
        try {
            Scanner reader = new Scanner(file);
            String line = reader.nextLine();
            boolean firstBloc = true;
            
            while (line != null) {
                if (firstBloc){
                    if (line.isEmpty() || line.startsWith(" 1")){
                        //fin du premier bloc
                        firstBloc = false;
                        line = reader.nextLine();
                    }else{
                        //Lecture du premier bloc
                        String[] inputLine = line.split("(?<=\\G.{4})");
                        for(int i=0; i< inputLine.length; i++){
                            if(piles.get(i) == null){
                                piles.put(i, "");
                            }
                            
                            String letter = inputLine[i].split("")[1];
                            if (letter != null && !letter.equalsIgnoreCase(" ")){
                                String result = letter + piles.get(i);
                                piles.put(i, result);
                            }
                        }
                        
                    }
                    
                }else{
                    //Lecture des mouvements
                    String[] moves = line.split(" ");
                    Integer nbMove = Integer.parseInt(moves[1]);
                    Integer fromStack = Integer.parseInt(moves[3])-1;
                    Integer toStack = Integer.parseInt(moves[5])-1;
                    
                    //Recupère la lettre a deplacer
                    String lettersToMove = piles.get(fromStack).substring(piles.get(fromStack).length() - nbMove);
                    
                    //Supprime les lettres de From
                    piles.put(fromStack, piles.get(fromStack).substring(0, piles.get(fromStack).length() - nbMove));
                    
                    //Ajoute la lettre dans To
                    piles.put(toStack, piles.get(toStack)+lettersToMove);
                    
                }
                
                line = reader.nextLine();
            }
            reader.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //Print result
        String resultStack = "";
        Iterator it = piles.keySet().iterator();
        while (it.hasNext()){
            Integer key = (Integer) it.next();
            resultStack += piles.get(key).substring(piles.get(key).length() - 1);
        }
        
        System.out.println("Score day6 part1 = " + resultStack);
    }
    
}
