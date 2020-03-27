package com.CezaryZal;

import java.util.List;

public class YahtzeeServer {

    public int getPoint(List<Integer> scoreOfThrows, String category) {
        if (category.equals("Four")){
            return scoreOfThrows.stream()
                    .filter((a) -> a == 4)
                    .reduce(0, Integer::sum);
        }
        return 0;
    }
}
