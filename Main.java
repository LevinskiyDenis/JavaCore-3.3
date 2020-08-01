package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("Гамаюн", "Петриченко", "Чебану", "Юрко");
        List<Integer> sizeOfDatabase = Arrays.asList(100, 1_000, 10_000, 100_000, 1_000_000, 10_000_000);

        Person.runTest(sizeOfDatabase, names);
    }
}
