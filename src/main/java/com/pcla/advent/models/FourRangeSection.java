package com.pcla.advent.models;

public class FourRangeSection {

    Integer intStart, intEnd;

    public FourRangeSection(Integer start, Integer end){
        intStart = start;
        intEnd = end;
    }

    public Integer getStart() {
        return intStart;
    }

    public void setStart(Integer intStart) {
        this.intStart = intStart;
    }

    public Integer getEnd() {
        return intEnd;
    }

    public void setEnd(Integer intEnd) {
        this.intEnd = intEnd;
    }

    public static boolean isIncluded(FourRangeSection first, FourRangeSection second){
        boolean result = false;

        if (first.getStart() <= second.getStart() && first.getEnd() >= second.getEnd()){return true;}
        if (second.getStart() <= first.getStart() && second.getEnd() >= first.getEnd()){return true;}

        return result;
    }

    
}
