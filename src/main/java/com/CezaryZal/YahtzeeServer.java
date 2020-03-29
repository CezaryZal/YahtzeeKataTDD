package com.CezaryZal;

import java.util.*;

public class YahtzeeServer {

    private final Map<String, Integer> pointTable = Map.of(
            "One", 1,
            "Two", 2,
            "Three", 3,
            "Four", 4,
            "Five", 5,
            "Six", 6);

    public int getCollectedPoint(List<Integer> scoreOfThrow, String category) {
        throwExceptionIfInputScopeIsIncorrect(scoreOfThrow);
        if (category.equals("Pair")){
            return getCollectedPointFromPairCategory(scoreOfThrow);
        }
        return getCollectedPointFromSingleCategory(scoreOfThrow, category);
    }

    private int getCollectedPointFromPairCategory(List<Integer> scoreOfThrow){
        scoreOfThrow.sort(Comparator.reverseOrder());

        Integer maxPointFromThrow = scoreOfThrow.get(0);
        if (maxPointFromThrow.equals(scoreOfThrow.get(1)) &&
                !maxPointFromThrow.equals(scoreOfThrow.get(2))) {
            return maxPointFromThrow * 2;
        }
        return 0;
    }

    private int getCollectedPointFromSingleCategory(List<Integer> scoreOfThrow, String category){
        Integer pointByCategory = pointTable.get(category);

        return scoreOfThrow.stream()
                .filter((number) -> number.equals(pointByCategory))
                .mapToInt(Integer::intValue)
                .sum();
    }

    private void throwExceptionIfInputScopeIsIncorrect(List<Integer> scoreOfThrow){
        if (scoreOfThrow.size() > 5){
            throw new IncorrectScoreOfThrowException("Throw contains too many numbers");
        }
        scoreOfThrow.forEach((number) -> {
            if (number == 0){
                throw new IncorrectScoreOfThrowException("The scope contains the number zero");
            }
        });
    }
}
