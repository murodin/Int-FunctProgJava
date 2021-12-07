package usestream;

import cleanedupstudents.Student;

import java.util.List;
import java.util.stream.Stream;

public class Example {
  public static void main(String[] args) {
    List<Student> ls = List.of(
        Student.of("Fred", 3.4, "Math", "Physics"),
        Student.of("Jim", 2.9, "Journalism"),
        Student.of("Sheila", 3.9, "Math", "Physics",
            "Astrophysics", "Quantum Mechanics"),
        Student.of("Alice", 3.2, "Art", "History", "Geography"),
        Student.of("Maverick", 2.2, "Engineering"),
        Student.of("Sarah", 3.2, "Electronics", "Physics", "Engineering")
    );

    Stream<Student> ss = ls.stream();
    ss.forEach(System.out::println);
//    ss.forEach(System.out::println); // it's stateful, and NOT reusable :)
    ls.stream().forEach(System.out::println);

    Stream.iterate(1, x -> x + 1)
        .limit(10)
        .map(x -> "The number is " + x)
        .forEach(System.out::println);
  }
}
