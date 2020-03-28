package com.CezaryZal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class YahtzeeServerTest {

    YahtzeeServer yahtzeeServer;
    @BeforeEach
    void setUp() {
        yahtzeeServer = new YahtzeeServer();
    }

    @Test
    void shouldGetReceiveResultFromSingleCategoryForFourType(){
        List<Integer> scoreOfThrows = Arrays.asList(1, 1, 2, 2, 4, 4);
        String category = "Four";
        int collectedPoints = yahtzeeServer.getPoint(scoreOfThrows, category);
        assertThat(collectedPoints).isEqualTo(8);
    }
    @Test
    void shouldGetReceiveResultFromSingleCategoryForThreeType(){
        List<Integer> scoreOfThrows = Arrays.asList(3, 3, 3, 2, 4, 4);
        String category = "Three";
        int number = yahtzeeServer.getPoint(scoreOfThrows, category);
        assertThat(number).isEqualTo(9);
    }
    @Test
    void shouldGetReceiveResultFromSingleCategoryForSixType(){
        List<Integer> scoreOfThrows = Arrays.asList(6, 3, 3, 6, 4, 4);
        String category = "Six";
        int number = yahtzeeServer.getPoint(scoreOfThrows, category);
        assertThat(number).isEqualTo(12);
    }

}