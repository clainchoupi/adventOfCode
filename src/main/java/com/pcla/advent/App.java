package com.pcla.advent;

import java.io.File;

public class App {
    public static void main(String[] args){
        
        File input1 = new File("src/main/resources/03/1.txt");
        Simulator simulator = new Simulator(input1);
        simulator.day3ParseInput2();
        
    }
}
