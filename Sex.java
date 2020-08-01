package com.company;

import java.util.*;

public enum Sex {
    MAN,
    WOMAN;

    private static final List<Sex> VALUES = List.of(Sex.values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Sex randomSex() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
