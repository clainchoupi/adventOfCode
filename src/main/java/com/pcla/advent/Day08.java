package com.pcla.advent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

public class Day08 {
    public static void main(String[] args){
        File input1 = new File("src/main/resources/DAY/08.txt");
        day8ParseInput(input1);
    }
    
    private static void day8ParseInput(File file) {
        ArrayList<ArrayList<Integer>> grid = new ArrayList<>();
        ArrayList<ArrayList<Boolean>> gridResult = new ArrayList<>();
        
        try {
            Scanner reader = new Scanner(file);
            int compteurLigne = 0;
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                
                String[] parts = line.split("");
                ArrayList<Integer> numbers = new ArrayList<>();
                ArrayList<Boolean> values = new ArrayList<>();
                
                
                for (int i= 0; i<parts.length; i++) {
                    int num = Integer.parseInt(parts[i]);
                    numbers.add(num);
                    if (i==0 || i==parts.length-1 || compteurLigne==0 || compteurLigne==parts.length-1){
                        values.add(true);
                    }else{
                        values.add(false);
                    }
                }
                
                compteurLigne ++;
                grid.add(numbers);
                gridResult.add(values);
            }
            reader.close();
            
            System.out.println("Grille init= " + nbTrueInGrid(gridResult));
            
            //Part 1 : Parcours de la grid pour savoir combien d'arbres sont visibles
            part1(grid, gridResult);
            part2(grid);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public static void part2(ArrayList<ArrayList<Integer>> grid){
        int score = 0;
        
        for (int i = 0; i < grid.size(); i++) {
            List<Integer> row = grid.get(i);
            
            for (int j = 0; j < row.size(); j++) {
                int tempScore = 0;
                int colonne = j;
                int tree = row.get(j);
                List<Integer> rowBefore = row.subList(0, j);
                List<Integer> rowAfter = row.subList(j+1, row.size());
                List<Integer> column = grid.stream()
                .map(c -> c.get(colonne))
                .collect(Collectors.toList());
                List<Integer> columnBefore = column.subList(0, i);
                List<Integer> columnAfter= column.subList(i+1, column.size());
                
                //Evaluer le score de l'arbre en cours
                tempScore = evaluateScore(tree, rowBefore, rowAfter, columnBefore, columnAfter);
                
                if (tempScore>score){
                    score = tempScore;
                }
            }
        }
        
        System.out.println("Score day 8 part 2 = " + score);
        
    }
    
    public static int evaluateScore (int tree,
    List<Integer> rowBefore, 
    List<Integer> rowAfter,
    List<Integer> columnBefore, 
    List<Integer> columnAfter){
        int rb=0, ra=0, cb=0, ca=0;
        
        if (rowBefore.size() ==0 ||rowAfter.size() ==0 || columnBefore.size() ==0 ||columnAfter.size() ==0){
            return 0;
        }
        
        //rb =  rowBefore
        for (int a=rowBefore.size(); a>0 ; a--){
            rb++;
            if (tree==rowBefore.get(a-1)){
                break;
            }else if (tree>rowBefore.get(a-1)){
            }else{
                break;
            }
        }
        
        //ra =  rowAfter
        for (int b=0; b<rowAfter.size(); b++){
            ra++;
            if (tree==rowAfter.get(b)){
                break;
            }else if (tree>rowAfter.get(b)){
            }else{
                break;
            }
        }
        
        //cb = columnBefore
        for (int c=columnBefore.size(); c>0 ; c--){
            cb++;
            if (tree==columnBefore.get(c-1)){
                break;
            }else if (tree>columnBefore.get(c-1)){
            }else{
                break;
            }
        }
        
        //ca =  columnAfter
        for (int d=0; d<columnAfter.size();d++){
            ca++;
            if (tree==columnAfter.get(d)){
                break;
            }else if (tree>columnAfter.get(d)){
            }else{
                break;
            }
        }
        
        return rb * ra * cb* ca;
    }
    
    public static void part1(ArrayList<ArrayList<Integer>> grid, ArrayList<ArrayList<Boolean>> gridResult){
        //Evaluer pour chaque case si l'arbre est visible
        for (int i = 0; i<grid.size(); i++){
            ArrayList<Integer> row = grid.get(i);
            
            //Pour chaque item
            for (int j = 0; j<row.size(); j++){
                int colonne = j;
                int tree = row.get(j);
                Boolean visible = gridResult.get(i).get(j);
                //Vérifier si l'arbre est visible dans les 4 sens
                //Sens lecture
                if(!visible){
                    visible = checkVisible(tree, row.subList(0, j));
                }
                
                //Sens inverse
                if(!visible){
                    visible = checkVisible(tree, row.subList(j+1, row.size()));
                }
                
                //Recuperation de la colonne en cours
                List<Integer> column = grid.stream()
                .map(c -> c.get(colonne))
                .collect(Collectors.toList());
                
                //Haut vers bas
                if(!visible){
                    //Recuperation
                    visible = checkVisible(tree, column.subList(0, i));
                }
                
                //Bas vers haut
                if(!visible){
                    visible = checkVisible(tree, column.subList(i+1, column.size()));
                }
                
                //Maj de la grille
                gridResult.get(i).set(j, visible);
                
            }
        }
        
        //Compter le nombre de True dans GridResult
        System.out.println("Score day 8 part 1 = " + nbTrueInGrid(gridResult));
        
    }
    
    public static Boolean checkVisible(Integer tree, List<Integer> valuesToCheck){
        if (CollectionUtils.isEmpty(valuesToCheck)){
            return true;
        }else{
            return tree > valuesToCheck.stream().mapToInt(a -> a).max().orElse(0);
        }
    }
    
    public static int nbTrueInGrid (ArrayList<ArrayList<Boolean>> gridResult){
        int score = 0;
        
        for (ArrayList<Boolean> sousListe : gridResult) {
            for (Boolean element : sousListe) {
                if (element){score +=1;}
            }
        }
        return score;
    }
    
}
