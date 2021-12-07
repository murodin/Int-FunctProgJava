package usestream;

import cleanedupstudents.Student;

import java.util.List;
import java.util.function.Function;
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

    // warning to non-smart
    ls.stream()
        .filter(s -> s.getGpa() < 3)
        .map(s -> "Dear " + s.getName() + " your grade of " + s.getGpa()
            + " is terrible, buck your ideas up!")
        .forEach(System.out::println);
    // all courses for enthusiastic students
    System.out.println("----courses for enthusiastic students");
    ls.stream()
        .filter(s -> s.getCourses().size() > 2)
        .flatMap(s -> s.getCourses().stream())
        .forEach(System.out::println);

    // all courses from smart students without duplicates
    System.out.println("----- course for smart students, no dups");
    ls.stream()
        .filter(s -> s.getGpa() > 3)
        .flatMap(s -> s.getCourses().stream())
        .distinct()
        .forEach(System.out::println);

//    // ditto, in alpha order
    System.out.println("----- course for smart students, no dups, sorted");
    ls.stream()
        .filter(s -> s.getGpa() > 3)
        .flatMap(s -> s.getCourses().stream())
        .distinct()
        .sorted() // MEMORY!!!
        .forEach(System.out::println);

//    // name-course pairs...
    Function<Student, Stream<String>> studentToNameCoursePairs =
        s -> s.getCourses().stream()
          .map(c -> "Student " + s.getName() + " takes course " + c);

    ls.stream()
//        .flatMap((Student s) -> {
//          return s.getCourses().stream().map(c -> "Student " + s.getName()
//                  + " takes course " + c);
//        })

//        .flatMap(s -> s.getCourses().stream()
//            .map(c -> "Student " + s.getName() + " takes course " + c))

        .flatMap(studentToNameCoursePairs)
        .forEach(System.out::println);
  }
}
