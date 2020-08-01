package com.company;

import java.util.*;

public class Person {

    private String name;
    private Integer age;
    private Sex sex;

    public Person(String name, Integer age, Sex sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public Sex getSex() {
        return sex;
    }

    public static double processData(List<Person> list) {
        long startTime = System.nanoTime();

        long numOfMilitary = list.stream()
                .filter(x -> x.getSex() == Sex.MAN)
                .filter(x -> x.getAge() > 18 && x.getAge() < 30)
                .count();

        System.out.println("Число военнообязанных:" + numOfMilitary);

        OptionalDouble averageManAge = list.stream()
                .filter(x -> x.getSex() == Sex.MAN)
                .mapToInt(x -> x.getAge())
                .average();

        System.out.println("Средний возраст мужчин:" + averageManAge.getAsDouble());

        long workers = list.stream()
                .filter(x -> x.getSex() == Sex.MAN)
                .filter(x -> x.getAge() > 18 && x.getAge() < 65)
                .count() + list.stream()
                .filter(x -> x.getSex() == Sex.WOMAN)
                .filter(x -> x.getAge() > 18 && x.getAge() < 60)
                .count();

        System.out.println("Количество трудоспособных: " + workers);

        long stopTime = System.nanoTime();
        double processTime = (double) (stopTime - startTime) / 1_000_000_000.0;
        System.out.println("Время обработки: " + processTime + " сек\n");

        return processTime;
    }

    public static double processDataParallel(List<Person> list) {
        long startTime = System.nanoTime();

        long numOfMilitary = list.parallelStream()
                .filter(x -> x.getSex() == Sex.MAN)
                .filter(x -> x.getAge() > 18 && x.getAge() < 30)
                .count();

        System.out.println("Число военнобязанных: " + numOfMilitary);

        OptionalDouble averageManAge = list.parallelStream()
                .filter(x -> x.getSex() == Sex.MAN)
                .mapToInt(x -> x.getAge())
                .average();

        System.out.println("Средний возраст мужчин: " + averageManAge.getAsDouble());

        long workers = list.parallelStream()
                .filter(x -> x.getSex() == Sex.MAN)
                .filter(x -> x.getAge() > 18 && x.getAge() < 65)
                .count() + list.parallelStream()
                .filter(x -> x.getSex() == Sex.WOMAN)
                .filter(x -> x.getAge() > 18 && x.getAge() < 60)
                .count();

        System.out.println("Количество трудоспособных: " + workers);

        long stopTime = System.nanoTime();
        double processTime = (double) (stopTime - startTime) / 1_000_000_000.0;
        System.out.println("Время обработки (параллельно): " + processTime + " сек\n");
        return processTime;
    }

    public static List<Person> generateBase(List<String> names, int size) {
        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < 1_000_000; i++) {
            persons.add(new Person(names.get(
                    new Random().nextInt(names.size())),
                    new Random().nextInt(100),
                    Sex.randomSex()));
        }

        return persons;
    }

    public static void runTest(List<Integer> sizeOfDatabase, List<String> names) {

        for (int i = 0; i < sizeOfDatabase.size(); i++) {
            List<Person> tmpList = generateBase(names, sizeOfDatabase.get(i));
            System.out.println("\nРазмер базы: " + sizeOfDatabase.get(i) + " чел.");
            processData(tmpList);
            processDataParallel(tmpList);
        }
    }
}
