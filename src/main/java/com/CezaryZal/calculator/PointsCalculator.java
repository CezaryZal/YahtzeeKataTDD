package com.CezaryZal.calculator;

import com.CezaryZal.constants.Constant;

import java.util.List;

//use generic type in the future
public abstract class PointsCalculator {

    final Constant constant;

    public PointsCalculator() {
        this.constant = new Constant();
    }

    public int calculatePoints(List<Integer> scoreOfThrow, String category){
       return calculatePointsForCategory(scoreOfThrow, category);
    }

    protected abstract int calculatePointsForCategory(List<Integer> scoreOfThrow, String category);
}
