package students;

import java.util.ArrayList;
import java.util.List;

interface StudentCriterion {
  boolean test(Student s);
}

class SmartCriterion implements StudentCriterion {
  private double threshold;
  public SmartCriterion(double t) {
    threshold = t;
  }
  @Override
  public boolean test(Student s) {
    return s.getGpa() > threshold;
  }
}

class EnthusiasticCriterion implements StudentCriterion {
  @Override
  public boolean test(Student s) {
    return s.getCourses().size() > 2;
  }
}

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
//  public static List<Student> selectSmart(
//      // separate what changes independently!!!! (find a way to bring
//      // them together to work :) -- called a design pattern
//      List<Student> ls, double threshold) {
//
//    List<Student> res = new ArrayList<>();
//    for (Student s : ls) {
//      if (s.getGpa() >= threshold) {
//        res.add(s);
//      }
//    }
//    return res;
//  }
//
//  public static List<Student> selectEnthusiastic(
//      List<Student> ls, int threshold) {
//    List<Student> res = new ArrayList<>();
//    for (Student s : ls) {
//      if (s.getCourses().size() >= threshold) {
//        res.add(s);
//      }
//    }
//    return res;
//  }

  // pattern is called "Command" pattern,
  // in functional programming it's simply called
  // "Higher Order Function"
  public static List<Student> selectByCriterion(
      List<Student> ls, StudentCriterion crit) {
    List<Student> res = new ArrayList<>();
    for (Student s : ls) {
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
    showAll(school);

//    showAll(selectSmart(school, 3.4));
//    showAll(selectSmart(school, 2.4));
//    showAll(selectEnthusiastic(school, 2));

    showAll(school);
    showAll(School.selectByCriterion(school, new SmartCriterion(3.4)));
    showAll(School.selectByCriterion(school, new EnthusiasticCriterion()));
//    showAll(School.selectByCriterion(school, new StudentCriterion() {
//      @Override
//      public boolean test(Student s) {
//        return s.getCourses().size() > 2;
//      }
//    }));

    // to use a lambda we MUST have a context that demands:
    // - an INTERFACE
    // - with exactly ONE ABSTRACT METHOD (because our syntax will provide
    //   the body of ONE method
//    showAll(School.selectByCriterion(school, /*new StudentCriterion() {*/
//      /*@Override
//      public boolean test*/(Student s) -> {
//        return s.getCourses().size() > 2;
//      }
//    /*}*/));

//    showAll(School.selectByCriterion(school,
//        (Student s) -> {
//          return s.getCourses().size() > 2;
//        }
//        ));

//    showAll(School.selectByCriterion(school,
////        (s) -> {
//// ONLY for a single no-type-specified argument, leave off parens
//        s -> {
//          return s.getCourses().size() > 2;
//        }
//    ));
//
//    showAll(School.selectByCriterion(school,
////        s -> { return s.getCourses().size() > 2; }
//        s -> s.getCourses().size() > 2
//    ));
    showAll(School.selectByCriterion(
        school, s -> s.getCourses().size() > 2));

//    myList.sort(/* lambda */)
  }
}
