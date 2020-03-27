package com.CezaryZal;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class YahtzeeServerTest {

    @Test
    void shouldGetReceiveResultFromSingleCategoryForFourType(){
        YahtzeeServer yahtzeeServer = new YahtzeeServer();
        List<Integer> scoreOfThrows = Arrays.asList(1, 1, 2, 2, 4, 4);
        String category = "Four";
        int number = yahtzeeServer.getPoint(scoreOfThrows, category);
        assertThat(number).isEqualTo(8);
    }

}