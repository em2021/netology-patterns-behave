package ru.netology;

import java.util.Iterator;
import java.util.Random;

public class Randoms implements Iterable<Integer> {
    private Random random;
    private final int min;
    private final int max;

    public Randoms(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Origin value exceeds bound value");
        } else {
            this.min = min;
            this.max = max;
        }
    }

    private Integer getNextValue() {
        random = new Random();
        return random.nextInt((max + 1) - min) + min;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Integer next() {
                return getNextValue();
            }
        };
    }
}