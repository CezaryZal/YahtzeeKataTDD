package com.CezaryZal.calculator;

import java.util.Comparator;
import java.util.List;

public class SumCalculatorByScoreOfThrow {

    public int calculateSumOfAllDice(List<Integer> scoreOfThrow){
        return scoreOfThrow.stream()
                .sorted(Comparator.reverseOrder())
                .mapToInt(Integer::intValue)
                .sum();
    }
}
