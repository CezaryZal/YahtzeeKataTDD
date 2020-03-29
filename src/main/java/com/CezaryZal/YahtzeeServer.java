package com.CezaryZal;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        if (category.equals("Pair")) {
            return getCollectedPointForSimpleCategory(parsedScoreOfThrow, 2);
        } else if (category.equals("Two Pair")) {
            return getCollectedPointFromTwoPairCategory(parsedScoreOfThrow);
        } else if (category.equals("Three of a kind")) {
            return getCollectedPointForSimpleCategory(parsedScoreOfThrow, 3);
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
            int simpleCategory) {

        return parsedScoreOfThrow.entrySet().stream()
                .filter(grope -> grope.getValue() == simpleCategory)
                .mapToInt(grope -> grope.getKey() * simpleCategory)
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
