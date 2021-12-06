package cleanedupstudents;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
interface Criterion<E> {
  boolean test(E s);
}

public class School {
  public static <E> void showAll(List<E> ls) {
    for (E s : ls) {
      System.out.println("> " + s);
    }
    System.out.println("---------------");
  }
//  public static List<Student> selectByCriterion(
//      List<Student> ls, Criterion<Student> crit) {
//    List<Student> res = new ArrayList<>();
//    for (Student s : ls) {
//      if (crit.test(s)) {
//        res.add(s);
//      }
//    }
//    return res;
//  }

  public static <E> List<E> selectByCriterion(
      List<E> ls, Criterion<E> crit) {
    List<E> res = new ArrayList<>();
    for (E s : ls) {
      if (crit.test(s)) {
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
    showAll(selectByCriterion(
        school, s -> s.getCourses().size() > 2));

    List<String> ls = List.of("Fred", "Jim", "Sheila");
    showAll(selectByCriterion(ls, (String s) -> s.length() > 3));
  }
}
