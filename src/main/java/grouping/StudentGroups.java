package grouping;

import cleanedupstudents.Student;

import java.util.List;
import java.util.stream.Collectors;

public class StudentGroups {

  public static String asLetterGrade(Student s) {
    double g = s.getGpa();
    if (g > 3.5) return "A";
    if (g > 3.0) return "B";
    if (g > 2.5) return "C";
    return "D";
  }
  public static String asLetterGrade(double g) {
    if (g > 3.5) return "A";
    if (g > 3.0) return "B";
    if (g > 2.5) return "C";
    return "D";
  }
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
    ls.stream()
//        .collect(Collectors.groupingBy(s -> StudentGroups.asLetterGrade(s)))
//        .collect(Collectors.groupingBy(s -> asLetterGrade(s.getGpa())))
//        .collect(Collectors.groupingBy(StudentGroups::asLetterGrade))

//        .collect(Collectors.groupingBy(StudentGroups::asLetterGrade,
//            Collectors.counting()))

//        .collect(Collectors.groupingBy(StudentGroups::asLetterGrade,
//            Collectors.mapping(s -> s.getName(), Collectors.toList())))

        .collect(Collectors.groupingBy(StudentGroups::asLetterGrade,
            Collectors.mapping(s -> s.getName(),
                Collectors.joining(", ", "scored by: ", ""))))

        .entrySet().stream()
        .forEach(System.out::println);
  }
}
