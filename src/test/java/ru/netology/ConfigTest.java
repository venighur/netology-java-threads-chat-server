package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConfigTest {
    @Test
    public void testConfig() {
        // arrange
        Config config = new Config();
        int expected = 8080;

        // act
        int result = config.getPort();

        // assert
        Assertions.assertEquals(expected, result);
    }
}
