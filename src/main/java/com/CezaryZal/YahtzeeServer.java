package com.CezaryZal;

import java.util.List;
import java.util.Map;

public class YahtzeeServer {

    private final Map<String, Integer> pointTable = Map.of(
            "One", 1,
            "Two", 2,
            "Three", 3,
            "Four", 4,
            "Five", 5,
            "Six", 6);

    public int getPoint(List<Integer> scoreOfThrows, String category) {
        Integer pointByCategory = pointTable.get(category);

       return  scoreOfThrows.stream()
                .filter((number) -> number.equals(pointByCategory))
                .mapToInt(Integer::intValue)
                .sum();
    }
}
