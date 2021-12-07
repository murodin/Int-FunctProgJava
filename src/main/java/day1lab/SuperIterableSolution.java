package day1lab;

import cleanedupstudents.Student;
import superiterable.SuperIterable;

import java.util.List;
import java.util.function.Predicate;

public class SuperIterableSolution {

//  public static <E> Predicate<E> and(Predicate<E> first, Predicate<E> second) {
//    return e -> first.test(e) && second.test(e);
//  }
//
//  public static <E> Predicate<E> or(Predicate<E> first, Predicate<E> second) {
//    return e -> first.test(e) || second.test(e);
//  }
//
//  public static <E> Predicate<E> not(Predicate<E> test) {
//    return e -> !test.test(e);
//  }

  public static void main(String[] args) {
    SuperIterable<Student> roster = new SuperIterable<>(List.of(
        Student.of("Fred", 3.4, "Math", "Physics"),
        Student.of("Jim", 2.9, "Journalism"),
        Student.of("Sheila", 3.9, "Math", "Physics",
            "Astrophysics", "Quantum Mechanics"),
        Student.of("Alice", 3.2, "Art", "History", "Geography"),
        Student.of("Maverick", 2.2, "Engineering"),
        Student.of("Sarah", 3.2, "Electronics", "Physics", "Engineering")
    ));
    roster
        .map(s -> "Welcome " + s)
        .forEach(s -> System.out.println(s));
    System.out.println("------------------");
    roster
        .filter(s -> s.getGpa() > 3)
        .forEach(s -> System.out.println(s));
    System.out.println("------------------");
    roster
        .filter(s -> s.getGpa() < 3)
        .map(s -> "Dear " + s.getName() + " your grade of " + s.getGpa()
            + " isn't good enough")
        .forEach(s -> System.out.println(s));
    System.out.println("------------------");
    roster
        .filter(s -> s.getGpa() > 3)
        .filter(s -> s.getGpa() < 3.5)
        .map(s -> "Dear " + s.getName()
            + " you're doing fine, but there's room for further improvement")
        .forEach(s -> System.out.println(s));

    System.out.println("------------------");
    roster
        .filter(Student.getSmartPredicate(2.5))
        .forEach(s -> System.out.println(s));

    System.out.println("------------------");
    double [] smartness = {2.5};
    Predicate<Student> pred = Student.getFlexibleSmartPredicate(smartness);
    roster
        .filter(pred)
        .forEach(s -> System.out.println(s));
    System.out.println("------------------");
    smartness[0] = 3.5; // clever, but ugly and NOT "good functional style" :)
    roster
        .filter(pred)
        .forEach(s -> System.out.println(s));

    Predicate<Student> smart = Student.getSmartPredicate(3.0);
    Predicate<Student> enthusiastic = Student.getEnthusiasticPredicate(2);
    Predicate<Student> smartAndEnthusiastic =
        s -> smart.test(s) && enthusiastic.test(s);

    System.out.println("Not enthusiastic(2)------------------");
    roster
//        .filter(and(not(enthusiastic), smart))
        .filter(enthusiastic.negate().and(smart))
        .forEach(s -> System.out.println(s));
  }
}
