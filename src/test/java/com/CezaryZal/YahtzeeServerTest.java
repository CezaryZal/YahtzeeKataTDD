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
    void shouldGetReceiveResultFromPairCategoryForThreeType() {
        List<Integer> scoreOfThrow = Arrays.asList(1, 4, 2, 3, 3);
        String category = "Pair";
        int collectedPoints = yahtzeeServer.getCollectedPoint(scoreOfThrow, category);
        assertThat(collectedPoints).isEqualTo(6);
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

    @Test
    void shouldGetReceiveResultFromTwoPairCategoryForThreeAndFourType() {
        List<Integer> scoreOfThrow = Arrays.asList(1, 4, 4, 3, 3);
        String category = "Two Pair";
        int collectedPoints = yahtzeeServer.getCollectedPoint(scoreOfThrow, category);
        assertThat(collectedPoints).isEqualTo(14);
    }

    @Test
    void shouldGetReceiveResultFromTwoPairCategoryForTwoAndThreeType() {
        List<Integer> scoreOfThrow = Arrays.asList(2, 2, 3, 3, 3);
        String category = "Two Pair";
        int collectedPoints = yahtzeeServer.getCollectedPoint(scoreOfThrow, category);
        assertThat(collectedPoints).isEqualTo(0);
    }

    @Test
    void shouldGetReceiveResultFromTwoPairCategoryForThreeAndTwoType() {
        List<Integer> scoreOfThrow = Arrays.asList(3, 3, 3, 2, 2);
        String category = "Two Pair";
        int collectedPoints = yahtzeeServer.getCollectedPoint(scoreOfThrow, category);
        assertThat(collectedPoints).isEqualTo(0);
    }

    @Test
    void shouldGetReceiveResultFromThreeGropeCategoryForThreeType() {
        List<Integer> scoreOfThrow = Arrays.asList(1, 3, 4, 3, 3);
        String category = "Three of a kind";
        int collectedPoints = yahtzeeServer.getCollectedPoint(scoreOfThrow, category);
        assertThat(collectedPoints).isEqualTo(9);
    }

    @Test
    void shouldGetReceiveResultFromThreeGropeCategoryForSixType() {
        List<Integer> scoreOfThrow = Arrays.asList(6, 3, 6, 3, 6);
        String category = "Three of a kind";
        int collectedPoints = yahtzeeServer.getCollectedPoint(scoreOfThrow, category);
        assertThat(collectedPoints).isEqualTo(18);
    }

    @Test
    void shouldGetReceiveResultFromFourGropeCategoryForFiveType() {
        List<Integer> scoreOfThrow = Arrays.asList(5, 3, 5, 5, 5);
        String category = "Four of a kind";
        int collectedPoints = yahtzeeServer.getCollectedPoint(scoreOfThrow, category);
        assertThat(collectedPoints).isEqualTo(20);
    }

    @Test
    void shouldGetReceiveResultFromFiveGropeCategoryForFourType() {
        List<Integer> scoreOfThrow = Arrays.asList(4, 4, 4, 4, 4);
        String category = "Yahtzee";
        int collectedPoints = yahtzeeServer.getCollectedPoint(scoreOfThrow, category);
        assertThat(collectedPoints).isEqualTo(50);
    }

}