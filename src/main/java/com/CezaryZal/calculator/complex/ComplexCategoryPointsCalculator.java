package com.CezaryZal.calculator.complex;

import com.CezaryZal.calculator.PointsCalculator;
import com.CezaryZal.calculator.SumCalculatorByScoreOfThrow;
import com.CezaryZal.exceptions.IncorrectCategoryException;

import java.util.List;

public class ComplexCategoryPointsCalculator extends PointsCalculator {

    @Override
    protected int calculatePointsForCategory(List<Integer> scoreOfThrow, String category) {
        switch (category) {
            case "Pair":
                ComplexCategory complexCategory = new SimpleOfComplexCategory();
                return calculateComplexCategory(complexCategory, scoreOfThrow, 2);

            case "Two Pair":
                complexCategory = new PairOfComplexCategory();
                return calculateComplexCategory(complexCategory, scoreOfThrow, 2);

            case "Three of a kind":
                complexCategory = new SimpleOfComplexCategory();
                return calculateComplexCategory(complexCategory, scoreOfThrow, 3);

            case "Four of a kind":
                complexCategory = new SimpleOfComplexCategory();
                return calculateComplexCategory(complexCategory, scoreOfThrow, 4);

            case "Small Straight":
                complexCategory = new StraightOfComplexCategory();
                return calculateComplexCategory(complexCategory, scoreOfThrow, 615);

            case "Large Straight":
                complexCategory = new StraightOfComplexCategory();
                return calculateComplexCategory(complexCategory, scoreOfThrow, 120);

            case "Full House":
                complexCategory = new PairOfComplexCategory();
                return calculateComplexCategory(complexCategory, scoreOfThrow, 3);

            case "Chance":
                SumCalculatorByScoreOfThrow sumByScoreOfThrow = new SumCalculatorByScoreOfThrow();
                return sumByScoreOfThrow.calculateSumOfAllDice(scoreOfThrow);

            case "Yahtzee":
                complexCategory = new SimpleOfComplexCategory();
                int pointsViaSimpleOfComplexCategory = calculateComplexCategory(
                        complexCategory, scoreOfThrow, 5);

                if (pointsViaSimpleOfComplexCategory != 0){
                    return 50;
                }
                return pointsViaSimpleOfComplexCategory;
        }
        throw new IncorrectCategoryException("The Category of throw is incorrect");
    }

    private int calculateComplexCategory(
            ComplexCategory complexCategory,List<Integer> scoreOfThrow, int numberOfGrope){

        return complexCategory.calculatePointsForComplexCategory(scoreOfThrow, numberOfGrope);
    }
}
