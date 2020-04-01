package com.CezaryZal.calculator.complex;

import com.CezaryZal.ScoreParser;
import com.CezaryZal.constants.Constant;

import java.util.List;
import java.util.Map;

public class PairOfComplexCategory implements ComplexCategory {

    private final Constant constant;
    private final ScoreParser scoreParser;

    public PairOfComplexCategory() {
        this.constant = new Constant();
        this. scoreParser = new ScoreParser();
    }

    @Override
    public int calculatePointsForComplexCategory(
            List<Integer> scoreOfThrow, int pointOfSecondPair) {

        Map<Integer, Integer> parsedScoreOfThrow = scoreParser.parseScoreOfThrow(scoreOfThrow);

        int pointFromFirstPair = 0;
        int pointFromSecondPair = 0;

        for (Map.Entry<Integer, Integer> iteration : parsedScoreOfThrow.entrySet()) {
            if (iteration.getValue() == 2 && pointFromFirstPair == 0) {
                pointFromFirstPair = iteration.getKey();
            } else if (iteration.getValue() == pointOfSecondPair) {
                pointFromSecondPair = iteration.getKey();
            }
        }
        return isConditionForPairCondition(pointFromFirstPair, pointFromSecondPair) ?
                constant.getPointForNotMatchesChooseCategory() :
                pointFromFirstPair * 2 + pointFromSecondPair * pointOfSecondPair;
    }

    private boolean isConditionForPairCondition(int pointFromFirstPair, int pointFromSecondPair) {
        return pointFromFirstPair == 0 || pointFromSecondPair == 0;
    }
}
