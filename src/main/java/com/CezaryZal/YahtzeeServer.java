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

        if (pointTable.containsKey(category)) {
            return getCollectedPointFromSingleCategory(scoreOfThrow, category);
        }
        Map<Integer, Integer> parsedScoreOfThrow = parseScoreOfThrow(scoreOfThrow);

        switch (category) {
            case "Pair":
                return getCollectedPointForSimpleCategory(parsedScoreOfThrow, 2);
            case "Two Pair":
                return getCollectedPointFromTwoPairCategory(parsedScoreOfThrow);
            case "Three of a kind":
                return getCollectedPointForSimpleCategory(parsedScoreOfThrow, 3);
            case "Four of a kind":
                return getCollectedPointForSimpleCategory(parsedScoreOfThrow, 4);
            case "Yahtzee":
                if (getCollectedPointForSimpleCategory(parsedScoreOfThrow, 5) != 0){
                    return 50;
                }
                return getCollectedPointForSimpleCategory(parsedScoreOfThrow, 5);
        }
        throw new IncorrectCategoryException("The Category of throw is incorrect");
    }

    private Map<Integer, Integer> parseScoreOfThrow(List<Integer> scoreOfThrow) {
        Map<Integer, Integer> parsedScoreOfThrow = new HashMap<>();

        for (Integer point : scoreOfThrow) {
            if (parsedScoreOfThrow.containsKey(point)) {
                Integer pointFromMap = parsedScoreOfThrow.get(point);
                parsedScoreOfThrow.replace(point, ++pointFromMap);
            }
            parsedScoreOfThrow.putIfAbsent(point, 1);
        }
        return parsedScoreOfThrow;
    }

    private int getCollectedPointFromTwoPairCategory(Map<Integer, Integer> parsedScoreOfThrow) {
        int pointFromFirstPair = 0;
        int pointFromSecondPair = 0;

        for (Map.Entry<Integer, Integer> iteration : parsedScoreOfThrow.entrySet()) {
            if (iteration.getValue() == 2 && pointFromFirstPair == 0) {
                pointFromFirstPair = iteration.getKey();
            } else if (iteration.getValue() == 2) {
                pointFromSecondPair = iteration.getKey();
            }
        }
        if (pointFromFirstPair == 0 || pointFromSecondPair == 0) {
            return 0;
        }
        return pointFromFirstPair * 2 + pointFromSecondPair * 2;
    }

    private int getCollectedPointForSimpleCategory(
            Map<Integer, Integer> parsedScoreOfThrow,
            int numberOfGrope) {

        return parsedScoreOfThrow.entrySet().stream()
                .filter(grope -> grope.getValue() == numberOfGrope)
                .mapToInt(grope -> grope.getKey() * numberOfGrope)
                .max()
                .orElse(0);
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
