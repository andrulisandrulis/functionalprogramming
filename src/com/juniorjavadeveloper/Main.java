package com.juniorjavadeveloper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.juniorjavadeveloper.Gender.FEMALE;
import static com.juniorjavadeveloper.Gender.MALE;

public class Main {

    public static void main(String[] args) {

        List<Person> people = List.of(
                new Person("John", MALE),
                new Person("Jane", FEMALE),
                new Person("Paul", MALE),
                new Person("Maria", FEMALE),
                new Person("Mark", MALE)

        );

        /* ------------ Stream example ------------ */

        System.out.println("For each version");
        for (Person person : people) {
            if (person.getGender().equals(FEMALE)) {
                System.out.println(person);
            }
        }

        System.out.printf("%nStream version%n");
        people.stream()
                .filter(person -> person.getGender().equals(FEMALE))
                .forEach(System.out::println);

        /* ------------ Function ------------ */

        System.out.printf("%nFunction takes person and returns Integer%n");
        Function<Person, Integer> getPersonNameLengthFunction = person -> person.getName().length();
        int length = getPersonNameLengthFunction.apply(people.get(0));
        System.out.println(length);

        System.out.printf("%nAdds 1 to each persons name length and filters all length bellow 6%n");
        people.stream()
                .map(person -> person.getName().length() + 1) // <== function
                .filter(integer -> integer < 6).forEach(System.out::println);

        System.out.printf("%nCombining functions%n");
        Function<Integer, Integer> incrementByOne = integer -> integer + 1;
        Function<Integer, Integer> multiply = integer -> integer * 10;
        Function<Integer, Integer> subtract = integer -> integer - integer / 2;
        Function<Integer, Integer> combinedFunction = incrementByOne.andThen(multiply).andThen(subtract);
        System.out.println(combinedFunction.apply(1));

        /* ------------ BiFunction ------------ */

        System.out.printf("%nBiFunction%n");
        BiFunction<Integer, Integer, Integer> incrementAndMultiply = (a, b) -> (a + 1) * b;
        int newValue = incrementAndMultiply.apply(1, 10);
        System.out.println(newValue);
        
    }

}
