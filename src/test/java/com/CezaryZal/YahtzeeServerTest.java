package com.CezaryZal;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class YahtzeeServerTest {

    @Test
    void testMethod(){
        YahtzeeServer yahtzeeServer = new YahtzeeServer();
        int number = yahtzeeServer.getNumber();
        assertThat(number).isEqualTo(2);
    }

}