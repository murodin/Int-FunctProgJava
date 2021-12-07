package grouping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Example {
  public static void main(String[] args) {
    // Java 10 var
    // use in place of explicit type for a LOCAL variable declaration
    // the context MUST INITIALIZE the variable, and the type
    // is taken from the type of the initializing expression.
    var x = 10;

    Map<Integer, List<String>>
//    var
        map = List.of("Fred", "Jim", "Sheila", "Alice", "Bill", "Bob", "Tony", "Alan")
        .stream().collect(Collectors.groupingBy(s -> s.length()));
    map.entrySet().stream()
        .forEach(System.out::println);
  }
}
