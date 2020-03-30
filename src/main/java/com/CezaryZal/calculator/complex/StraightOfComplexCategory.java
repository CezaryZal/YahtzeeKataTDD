package com.CezaryZal.calculator.complex;

import com.CezaryZal.calculator.SumCalculatorByScoreOfThrow;

import java.util.List;

public class StraightOfComplexCategory implements ComplexCategory {

    private final SumCalculatorByScoreOfThrow sumCalculatorByScoreOfThrow;

    public StraightOfComplexCategory() {
        this.sumCalculatorByScoreOfThrow = new SumCalculatorByScoreOfThrow();
    }

    @Override
    public int calculatePointsForComplexCategory(List<Integer> scoreOfThrow, int numberOfGrope) {
        return calculatePointsForComplexCategory(
                scoreOfThrow, (numberOfGrope / 100) % 10, numberOfGrope % 100);
    }

    public int calculatePointsForComplexCategory(
            List<Integer> scoreOfThrow,
            int whitOutPoint,
            int sumOfAllDice) {

        int currentSumOfAllDice = sumCalculatorByScoreOfThrow.calculateSumOfAllDice(scoreOfThrow);

        if (!scoreOfThrow.contains(whitOutPoint) && currentSumOfAllDice == sumOfAllDice) {
            return currentSumOfAllDice;
        }
        return 0;
    }
}