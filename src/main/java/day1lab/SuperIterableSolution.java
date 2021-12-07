package day1lab;

import cleanedupstudents.Student;
import superiterable.SuperIterable;

import java.util.List;

public class SuperIterableSolution {
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
  }
}
