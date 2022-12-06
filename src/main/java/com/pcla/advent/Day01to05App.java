package com.pcla.advent;

import java.io.File;

public class Day01to05App {
    public static void main(String[] args){
        
        File input1 = new File("src/main/resources/DAY/06.txt");
        Day01to05Simulator simulator = new Day01to05Simulator(input1);
        simulator.day5ParseInput1();
        
    }
}
