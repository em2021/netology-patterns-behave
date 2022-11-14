package ru.netology;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class RandomsTests {
    private Randoms randoms;

    @BeforeAll
    public static void startedAll() {
        System.out.println("Randoms tests started");
    }

    @BeforeEach
    public void init() {
        System.out.println("Randoms test started");
    }

    @AfterEach
    public void completed() {
        System.out.println("Randoms test completed");
    }

    @AfterAll
    public static void completedAll() {
        System.out.println("Randoms tests completed");
    }

    @ParameterizedTest
    @MethodSource("sourceForConstructorThrowsExceptionTest")
    public void testConstructor_throwsIllegalArgumentException(int min, int max) {
        //given:
        String expectedMessage = "Origin value exceeds bound value";
        //when:
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                randoms = new Randoms(min, max));
        String actualMessage = exception.getMessage();
        //then:
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @ParameterizedTest
    @MethodSource("sourceForTestIterator")
    public void testIterator(int min, int max, int stopValue) {
        //when:
        randoms = new Randoms(min, max);
        List<Integer> generatedRandoms = new ArrayList<>();
        for (int r : randoms) {
            generatedRandoms.add(r);
            if (r == stopValue) {
                break;
            }
        }
        //then:
        Assertions.assertFalse(generatedRandoms.stream().anyMatch((x) -> x < min || x > max));
    }

    public static Stream<Arguments> sourceForConstructorThrowsExceptionTest() {
        return Stream.of(
                Arguments.of(100, 40),
                Arguments.of(0, -10),
                Arguments.of(-5, -50));
    }

    public static Stream<Arguments> sourceForTestIterator() {
        return Stream.of(
                Arguments.of(0, 1000, 100),
                Arguments.of(10, 10, 10),
                Arguments.of(-1000, 1000, 0),
                Arguments.of(0, 10, 5));
    }
}