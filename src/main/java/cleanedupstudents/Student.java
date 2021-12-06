package cleanedupstudents;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// classes really need to be final to be able to enforce
// long-term immutability
public final class Student {
  private String name;
  private double gpa;
  private List<String> courses; // should this be a Set???
//  class X { private int x; }

  // "never" expose a constructor that would permit an invalid object..
  // if you want to build in "stages" -- use the builder pattern.
//  public Student() {}


  // exposing accessible constructors is very last millenium.
  // problem "new Xxxx()" can only create a BRAND NEW object
  // of exactly the Xxxx class, or throw an exception
  private Student(String name, double gpa, List<String> courses) {
    if (name == null) throw new IllegalArgumentException("must have a name");
    this.name = name;
    this.gpa = gpa;
    this.courses = courses;
  }

  // factory or builder can do anything a constructor can do, but can
  // also (without change to the caller) you can return an old object
  // from a pool, or a subtype.
  //
  // varargs: compiler will make a list from comma separated arguments
  // BUT if caller passes an array, it still works
  public static Student of(String name, double gpa, String ... courses) {
    // Arrays.asList creates a "VIEW" on an existing array
    // this ALLOWS the use of the set() method...
    return new Student(name, gpa, Arrays.asList(courses.clone()));

//    return new Student(name, gpa, List.of(courses)); // List.of is Java 9
  }

  // IMMUTABLE DATA PLEASE -- no setters

  public String getName() {
    return name;
  }

  public double getGpa() {
    return gpa;
  }

  public Student withName(String name) {
    return new Student(name, this.gpa, this.courses);
  }

  public List<String> getCourses() {
    // copyOf is Java 10 (I think) it creates an unmodifiable copy
    // if the original is modifiable (but not otherwise)
//    return List.copyOf(courses);
    // Collections.blahblahList/Set/Map creates proxies
    return Collections.unmodifiableList(courses);
  }

  private static Criterion<Student> getSmartCriterion() {
    return s -> s.getGpa() > 3.4;
  }

  @Override
  public String toString() {
    return "Student{" +
        "name='" + name + '\'' +
        ", gpa=" + gpa +
        ", courses=" + courses +
        '}';
  }
}
