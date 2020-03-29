package com.CezaryZal;

import java.util.*;

public class YahtzeeServer {

    private final int pointForNotMatchesChooseCategory = 0;
    private final Map<String, Integer> pointTable = Map.of(
            "One", 1,
            "Two", 2,
            "Three", 3,
            "Four", 4,
            "Five", 5,
            "Six", 6);

    public int getCollectedPoint(List<Integer> scoreOfThrow, String category) {
        throwExceptionIfInputScopeIsIncorrect(scoreOfThrow);

        return pointTable.containsKey(category) ?
                getCollectedPointForSingleCategory(scoreOfThrow, category) :
                matchSolutionForComplexCategory(scoreOfThrow, category);
    }

    private int matchSolutionForComplexCategory(List<Integer> scoreOfThrow, String category){
        Map<Integer, Integer> parsedScoreOfThrow = parseScoreOfThrow(scoreOfThrow);

        switch (category) {
            case "Pair":
                return getCollectedPointForSimpleCategory(parsedScoreOfThrow, 2);
            case "Two Pair":
                return getCollectedPointForPairCategory(parsedScoreOfThrow, 2);
            case "Three of a kind":
                return getCollectedPointForSimpleCategory(parsedScoreOfThrow, 3);
            case "Four of a kind":
                return getCollectedPointForSimpleCategory(parsedScoreOfThrow, 4);
            case "Small Straight":
                return getCollectedPointForStraight(scoreOfThrow, 6, 15);
            case "Large Straight":
                return getCollectedPointForStraight(scoreOfThrow, 1, 20);
            case "Full House":
                return getCollectedPointForPairCategory(parsedScoreOfThrow, 3);
            case "Chance":
                return calculateSumOfAllDice(scoreOfThrow);
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

    private int getCollectedPointForStraight(
            List<Integer> scoreOfThrow,
            int noPoint,
            int sumOfAllDice){

        int currentSumOfAllDice = calculateSumOfAllDice(scoreOfThrow);

        return (!scoreOfThrow.contains(noPoint) && currentSumOfAllDice == sumOfAllDice) ?
                currentSumOfAllDice : pointForNotMatchesChooseCategory;
    }

    private int calculateSumOfAllDice(List<Integer> scoreOfThrow){
        return scoreOfThrow.stream()
                .sorted(Comparator.reverseOrder())
                .mapToInt(Integer::intValue)
                .sum();
    }

    private int getCollectedPointForPairCategory(
            Map<Integer, Integer> parsedScoreOfThrow,
            int pointOfSecondPair) {

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
                pointForNotMatchesChooseCategory :
                pointFromFirstPair * 2 + pointFromSecondPair * pointOfSecondPair;
    }

    private boolean isConditionForPairCondition(int pointFromFirstPair, int pointFromSecondPair) {
        return pointFromFirstPair == 0 || pointFromSecondPair == 0;
    }

    private int getCollectedPointForSimpleCategory(
            Map<Integer, Integer> parsedScoreOfThrow,
            int numberOfGrope) {

        return parsedScoreOfThrow.entrySet().stream()
                .filter(grope -> grope.getValue() == numberOfGrope)
                .mapToInt(grope -> grope.getKey() * numberOfGrope)
                .max()
                .orElse(pointForNotMatchesChooseCategory);
    }

    private int getCollectedPointForSingleCategory(List<Integer> scoreOfThrow, String category) {
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
            if (number < 1) {
                throw new IncorrectScoreOfThrowException("The scope contains the number zero");
            }
        });
    }
}
