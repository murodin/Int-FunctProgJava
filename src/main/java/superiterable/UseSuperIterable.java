package superiterable;

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
  }
}
