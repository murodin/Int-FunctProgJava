package useoptional;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class Example {
  public static void main(String[] args) {
    Map<String, String> names = Map.of("Fred", "Jones");

    String first = "Fred"; // assume this was calculated!!

    String last = names.get(first);
    if (last != null) {
      String lastLoud = last.toUpperCase();
      String message = "Dear " + lastLoud;
      System.out.println(message);
    }

    System.out.println("---------------");
    Optional.of(names)
        // map converts to empty optional if we get null
        .map(m -> m.get(first))
        .map(String::toUpperCase)
        .map(s -> "Dear " + s)
        .ifPresent(System.out::println);

//    Stream.of()
//        .map(i -> "number is " + i)
//        .forEach(System.out::println);
  }
}
