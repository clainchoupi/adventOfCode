package com.pcla.advent.models;

public class RangeSection {

    Integer intStart, intEnd;

    public RangeSection(Integer start, Integer end){
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

    public static boolean isIncluded(RangeSection first, RangeSection second){
        boolean result = false;

        if (first.getStart() <= second.getStart() && first.getEnd() >= second.getEnd()){return true;}
        if (second.getStart() <= first.getStart() && second.getEnd() >= first.getEnd()){return true;}

        return result;
    }

    public static boolean isOverlapping(RangeSection first, RangeSection second){
        boolean result = false;

        if (first.getStart() >= second.getStart() && first.getStart() <= second.getEnd() ){return true;}
        if (second.getStart() >= first.getStart() && second.getStart() <= first.getEnd() ){return true;}

        if (second.getEnd() >= first.getStart() && second.getEnd() <= first.getStart() ){return true;}
        if (first.getEnd() >= second.getStart() && first.getEnd() <= second.getStart() ){return true;}

        return result;
    }

    
}
