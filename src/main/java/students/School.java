package students;

import java.util.ArrayList;
import java.util.List;

public class School {
  public static void showAll(List<Student> ls) {
    for (Student s : ls) {
      System.out.println("> " + s);
    }
    System.out.println("---------------");
  }

//  public static void showAllSmart(List<Student> ls) {
//    for (Student s : ls) {
//      if (s.getGpa() >= 3.5) {
//        System.out.println(">> " + s);
//      }
//    }
//    System.out.println("---------------");
//  }

  // DO NOT erase data from the list!!
  // make a new list
  public static List<Student> selectSmart(
      // separate what changes independently!!!! (find a way to bring
      // them together to work :) -- called a design pattern
      List<Student> ls, double threshold) {

    List<Student> res = new ArrayList<>();
    for (Student s : ls) {
      if (s.getGpa() >= threshold) {
        res.add(s);
      }
    }
    return res;
  }

  public static List<Student> selectEnthusiastic(
      List<Student> ls, int threshold) {
    List<Student> res = new ArrayList<>();
    for (Student s : ls) {
      if (s.getCourses().size() >= threshold) {
        res.add(s);
      }
    }
    return res;
  }

  public static void main(String[] args) {
    List<Student> school = List.of(
        Student.of("Fred", 3.5, "Math", "Physics"),
        Student.of("Jim", 2.9, "Journalism"),
        Student.of("Sheila", 3.9, "Math", "Physics",
            "Astrophysics", "Quantum Mechanics")
    );
    showAll(school);
    showAll(selectSmart(school, 3.4));
    showAll(selectSmart(school, 2.4));
    showAll(selectEnthusiastic(school, 2));
  }
}
