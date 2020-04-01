package com.CezaryZal.calculator.complex;

import com.CezaryZal.ScoreParser;
import com.CezaryZal.constants.Constant;

import java.util.List;
import java.util.Map;

public class SimpleOfComplexCategory implements ComplexCategory {

    private final ScoreParser scoreParser;
    private final Constant constant;

    public SimpleOfComplexCategory() {
        this.constant = new Constant();
        this.scoreParser = new ScoreParser();
    }

    @Override
    public int calculatePointsForComplexCategory(List<Integer> scoreOfThrow, int numberOfGrope) {
        Map<Integer, Integer> parsedScoreOfThrow = scoreParser.parseScoreOfThrow(scoreOfThrow);

        return parsedScoreOfThrow.entrySet().stream()
                .filter(grope -> grope.getValue() == numberOfGrope)
                .mapToInt(grope -> grope.getKey() * numberOfGrope)
                .max()
                .orElse(constant.getPointForNotMatchesChooseCategory());
    }
}
