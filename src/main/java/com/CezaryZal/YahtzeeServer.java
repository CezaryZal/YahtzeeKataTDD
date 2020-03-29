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
        if (category.equals("Pair")) {
            return getCollectedPointFromPairCategory(scoreOfThrow);
        } else if (category.equals("Two Pair")) {
            return getCollectedPointFromTwoPairCategory(scoreOfThrow);
        }
        return getCollectedPointFromSingleCategory(scoreOfThrow, category);
    }

    private int getCollectedPointFromTwoPairCategory(List<Integer> scoreOfThrow) {
        scoreOfThrow.sort(Comparator.reverseOrder());
        int pointsFromThrow = 0;

        pointsFromThrow += calculatePointsForPairCategory(scoreOfThrow, 0);
        pointsFromThrow += calculatePointsForPairCategory(scoreOfThrow, 2);

        return pointsFromThrow;
    }

    private int getCollectedPointFromPairCategory(List<Integer> scoreOfThrow) {
        scoreOfThrow.sort(Comparator.reverseOrder());

        return calculatePointsForPairCategory(scoreOfThrow, 0);
    }

    private int calculatePointsForPairCategory(List<Integer> scoreOfThrow, int StartingIndex) {
        Integer maxPointFromThrow = scoreOfThrow.get(StartingIndex);
        if (maxPointFromThrow.equals(scoreOfThrow.get(++StartingIndex)) &&
                !maxPointFromThrow.equals(scoreOfThrow.get(++StartingIndex))) {
            return maxPointFromThrow * 2;
        }
        return 0;
    }

    private int getCollectedPointFromSingleCategory(List<Integer> scoreOfThrow, String category) {
        Integer pointByCategory = pointTable.get(category);

        return scoreOfThrow.stream()
                .filter((number) -> number.equals(pointByCategory))
                .mapToInt(Integer::intValue)
                .sum();
    }

    private void throwExceptionIfInputScopeIsIncorrect(List<Integer> scoreOfThrow) {
        if (scoreOfThrow.size() > 5) {
            throw new IncorrectScoreOfThrowException("Throw contains too many numbers");
        }
        scoreOfThrow.forEach((number) -> {
            if (number == 0) {
                throw new IncorrectScoreOfThrowException("The scope contains the number zero");
            }
        });
    }
}
