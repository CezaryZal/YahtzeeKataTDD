package com.CezaryZal;

import com.CezaryZal.exceptions.IncorrectScoreOfThrowException;

import java.util.List;

public class InputChecker {

    void validationOdInputData(List<Integer> scoreOfThrow) {
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
