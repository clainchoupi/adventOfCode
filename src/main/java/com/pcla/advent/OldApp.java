package com.pcla.advent;

import java.io.File;

public class OldApp {
    public static void main(String[] args){
        
        File input1 = new File("src/main/resources/DAY/06.txt");
        Simulator simulator = new Simulator(input1);
        simulator.day5ParseInput1();
        
    }
}
