package com.pcla.advent;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import com.pcla.advent.models.Day07Folder;

public class Day07 {
    public static void main(String[] args){
        File input1 = new File("src/main/resources/DAY/07.txt");
        day7ParseInput1(input1);
    }
    
    private static void day7ParseInput1(File file) {
        int result=0;
        Stack<String> foldersStack = new Stack<>();
        Day07Folder everything = new Day07Folder();
        
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNext()){
                String line = reader.nextLine();
                String[] item = line.split(" ");

                if (line.startsWith("$")){
                    //C'est une commande cd ou ls 
                    if (line.equals("$ ls")){
                        //Rien à faire
                    }else{
                        //On change de dossier
                        String cible = item[2];

                        if (cible.equals("..")){
                            foldersStack.pop();
                        }else {
                            foldersStack.push(cible);
                        }
                    }

                }else if (line.startsWith("dir")){
                    //C'est un sous dossier
                    String folderName = item[1];

                    //Recupération du dossier en cours
                    Day07Folder currentFolder = everything.listFolders.get(foldersStack.peek());
                    if (currentFolder == null){
                        currentFolder= new Day07Folder();
                    }
                    currentFolder.listFolders.put(folderName, new Day07Folder());
                    everything.listFolders.put(foldersStack.peek(), currentFolder);
                }else {
                    //C'est un fichier
                    Integer fileSize = Integer.parseInt(item[0]);
                    Day07Folder currentFolder = everything.listFolders.get(foldersStack.peek());
                    if (currentFolder == null){
                        currentFolder= new Day07Folder();
                    }
                    currentFolder.files.add(fileSize);
                    everything.listFolders.put(foldersStack.peek(), currentFolder);
                }
                
            }
            
            //Parcours des folders
            ArrayList<Integer> listSizes = new ArrayList<>();
            listSizes= addSizeRecursively(everything, listSizes);

            result = listSizes.stream().filter(value -> value < 100000).reduce(0, (a, b) -> a + b);
            reader.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Score day7part1 = " + result);
    }

    public static ArrayList<Integer> addSizeRecursively(Day07Folder folder, ArrayList<Integer> list){
        list.add(getFolderFullSize(folder));

        for (String key : folder.listFolders.keySet()) {
            list =  addSizeRecursively(folder.listFolders.get(key), list);
        }  
        return list;
    }

    //Get folderSize
    public static Integer getFolderFullSize (Day07Folder folder){
        Integer result = 0;

        //Taille des fichiers
        result+= folder.files.stream().reduce(0, (a, b) -> a + b);

        //Taille des sous dossiers
        for (String key : folder.listFolders.keySet()) {
            result+= getFolderFullSize(folder.listFolders.get(key));
        }  
          
        return result;
    }
    
}
