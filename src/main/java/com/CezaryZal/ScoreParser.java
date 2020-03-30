package com.CezaryZal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreParser {

    public Map<Integer, Integer> parseScoreOfThrow(List<Integer> scoreOfThrow) {
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
}
