package superiterable;

import cleanedupstudents.Student;

import java.util.List;

public class UseSuperIterable {
  public static void main(String[] args) {
    SuperIterable<String> sis =
        new SuperIterable<>(List.of(
            "Alice", "Maverick", "Susan", "Fred", "Jim", "Sheila"));

    sis
        .filter(s -> s.length() < 7)
//        .map(s -> "Dear, " + s.toUpperCase())
        .map(s -> s.length())
        .map(i -> i.doubleValue())
//        .withEvery(s -> System.out.println("> " + s));
        .forEach(s -> System.out.println("> " + s));

//    for (String s : sis) {
//      System.out.println(" > " + s);
//    }

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
        .filter(s -> s.getGpa() > 3)
        .flatMap(s -> new SuperIterable<>(s.getCourses()))
//        .forEach(s -> System.out.println(s));
        .forEach(System.out::println);
  }
}
