package day2lab1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Person {
  private String first;
  private String last;

  @Override
  public String toString() {
    return "Person{" +
        "first='" + first + '\'' +
        ", last='" + last + '\'' +
        '}';
  }

  public Person(String first, String last) {
    this.first = first;
    this.last = last;
  }

  public static Comparator<Person> getFirstNameComparator() {
    // return a lambda which will order two Person objects
    // based on the alphabetical order of their first names
    // write a little test code to prove this works before continuing!
    return (p1, p2) -> p1.first.compareTo(p2.first);
  }

  // Step 2, do this, and write a little test code for it
  // Also define getLastNameComparator() ordering by last name
  public static Comparator<Person> getLastNameComparator() {
    return (p1, p2) -> p1.last.compareTo(p2.last);
  }
}
public class Template {
  // step 3 function that takes Comparator<E> and returns Comparator<E>
  // that's in reverse order...
  // ask if you're really stuck on the signature
  public static <E> Comparator<E> reverseOrder(Comparator<E> order) {
//    return (e1, e2) -> -order.compare(e1, e2);
    return (e1, e2) -> order.compare(e2, e1);
  }

  public static <E> Comparator<E> thenComparing(
      Comparator<E> first, Comparator<E> second) {
    return (e1, e2) -> {
      int res = first.compare(e1, e2);
      if (res != 0) return res;
      return second.compare(e1, e2);
    };
  }

  public static void main(String[] args) {
    List<Person> folks = new ArrayList<>(List.of(
        // put your persons in here...
        // need some duplicate first names, and some duplicate last names
        new Person("Fred", "Jones"),
        new Person("Jim", "Williams"),
        new Person("Jim", "Ackroyd"),
        new Person("Fred", "Williams"),
        new Person("Fred", "Ackroyd"),
        new Person("Jim", "Jones"),
        new Person("Fred", "Smith"),
        new Person("Jim", "Smith")
        ));
    System.out.println(folks);

    // step 1 (above) order by first name
    folks.sort(Person.getFirstNameComparator());
    System.out.println(folks);
    // step 2 above, order by last name
    folks.sort(Person.getLastNameComparator());
    System.out.println(folks);

    // step 3, write a function (above) which takes a Comparator<E>
    // and returns a Comparator<E> which compares in REVERSE order
    // step 3a order by first name "descending" (no new lambdas,
    // and no new comparators)
    folks.sort(reverseOrder(Person.getLastNameComparator()));
    System.out.println(folks);

    // step 4 (optional) write a function that takes TWO comparators<E>
    // and returns a single comparator such that if the first is order
    // by last name, and the second is order by first name, then the result
    // is order by last name, and sub-order by first name if the last names
    // are the same (no new lambdas,
    // and no new comparators)
    folks.sort(thenComparing(Person.getLastNameComparator(),
        reverseOrder(Person.getFirstNameComparator())));
    System.out.println(folks);

  }
}
