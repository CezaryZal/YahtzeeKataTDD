package com.CezaryZal.constants;

import java.util.Map;

public class Constant {

    private final int pointForNotMatchesChooseCategory = 0;
    private final Map<String, Integer> pointTable = Map.of(
            "One", 1,
            "Two", 2,
            "Three", 3,
            "Four", 4,
            "Five", 5,
            "Six", 6);

    public int getPointForNotMatchesChooseCategory() {
        return pointForNotMatchesChooseCategory;
    }

    public Map<String, Integer> getPointTable() {
        return pointTable;
    }
}
