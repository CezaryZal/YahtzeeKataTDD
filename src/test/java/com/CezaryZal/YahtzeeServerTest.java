package com.CezaryZal;

import org.junit.jupiter.api.Assertions;
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
    void shouldGetReceiveResultFromSingleCategoryForFourType() {
        List<Integer> scoreOfThrow = Arrays.asList(1, 1, 2, 4, 4);
        String category = "Four";
        int collectedPoints = yahtzeeServer.getCollectedPoint(scoreOfThrow, category);
        assertThat(collectedPoints).isEqualTo(8);
    }

    @Test
    void shouldGetReceiveResultFromSingleCategoryForThreeType() {
        List<Integer> scoreOfThrow = Arrays.asList(3, 3, 3, 2, 4);
        String category = "Three";
        int collectedPoints = yahtzeeServer.getCollectedPoint(scoreOfThrow, category);
        assertThat(collectedPoints).isEqualTo(9);
    }

    @Test
    void shouldGetReceiveResultFromSingleCategoryForSixType() {
        List<Integer> scoreOfThrow = Arrays.asList(6, 3, 3, 6, 4);
        String category = "Six";
        int collectedPoints = yahtzeeServer.getCollectedPoint(scoreOfThrow, category);
        assertThat(collectedPoints).isEqualTo(12);
    }

    @Test
    void shouldGetReceiveResultFromPairCategoryForFourType() {
        List<Integer> scoreOfThrow = Arrays.asList(1, 4, 4, 3, 3);
        String category = "Pair";
        int collectedPoints = yahtzeeServer.getCollectedPoint(scoreOfThrow, category);
        assertThat(collectedPoints).isEqualTo(8);
    }

    @Test
    void shouldThrowExceptionWhenSendScoreWithZeroNumber(){
        List<Integer> scoreOfThrow = Arrays.asList(1, 4, 0, 3, 3);
        String category = "Six";
        Assertions.assertThrows(IncorrectScoreOfThrowException.class,
                () -> yahtzeeServer.getCollectedPoint(scoreOfThrow, category));
    }

    @Test
    void shouldThrowExceptionWhenScoreSizeIsGreaterThanFive(){
        List<Integer> scoreOfThrow = Arrays.asList(1, 4, 4, 2, 3, 3);
        String category = "Six";
        Assertions.assertThrows(IncorrectScoreOfThrowException.class,
                () -> yahtzeeServer.getCollectedPoint(scoreOfThrow, category));
    }

}