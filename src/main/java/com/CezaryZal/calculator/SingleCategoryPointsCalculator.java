package com.CezaryZal.calculator;

import java.util.List;

public class SingleCategoryPointsCalculator extends PointsCalculator {

    @Override
    public int calculatePointsForCategory(List<Integer> scoreOfThrow, String category) {
        Integer pointByCategory = constant.getPointTable().get(category);

        return scoreOfThrow.stream()
                .filter((number) -> number.equals(pointByCategory))
                .mapToInt(Integer::intValue)
                .sum();
    }
}
