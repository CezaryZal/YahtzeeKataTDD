package com.CezaryZal;

import com.CezaryZal.calculator.PointsCalculator;
import com.CezaryZal.calculator.SingleCategoryPointsCalculator;
import com.CezaryZal.calculator.complex.ComplexCategoryPointsCalculator;
import com.CezaryZal.constants.Constant;

import java.util.*;

public class YahtzeeServer {

    private final Constant constant;
    private final InputChecker inputChecker;
    private PointsCalculator pointsCalculator;

    public YahtzeeServer() {
        this.constant = new Constant();
        this.inputChecker = new InputChecker();
    }

    public int getCollectedPoint(List<Integer> scoreOfThrow, String category) {
        inputChecker.validationOdInputData(scoreOfThrow);

        return constant.getPointTable().containsKey(category) ?
                getCollectedPointsForSingleCategory(scoreOfThrow, category) :
                getCollectedPointsForComplexCategory(scoreOfThrow, category);
    }

    private int getCollectedPointsForComplexCategory(List<Integer> scoreOfThrow, String category){
        pointsCalculator = new ComplexCategoryPointsCalculator();
        return pointsCalculator.calculatePoints(scoreOfThrow, category);
    }

    private int getCollectedPointsForSingleCategory(List<Integer> scoreOfThrow, String category) {
        pointsCalculator = new SingleCategoryPointsCalculator();
        return pointsCalculator.calculatePoints(scoreOfThrow, category);
    }

}
