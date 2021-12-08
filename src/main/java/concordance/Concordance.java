package concordance;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Concordance {
  static final Pattern WORD_BOUNDARY = Pattern.compile("\\W+");

  static final Comparator<Map.Entry<String, Long>> entryByValue =
      Map.Entry.comparingByValue();
  static final Comparator<Map.Entry<String, Long>> reverseEntryByValue =
    entryByValue.reversed();

  public static void main(String[] args) throws Throwable {
    try (Stream<String> input = Files.lines(Path.of("PrideAndPrejudice.txt"))) {
      input
//          .map(s -> s.toLowerCase())
          .map(String::toLowerCase)
//          .flatMap(l -> WORD_BOUNDARY.splitAsStream(l))
          .flatMap(WORD_BOUNDARY::splitAsStream)
          .filter(s -> !s.isBlank())
//          .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
          .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
          .entrySet().stream()
//          .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
//          .sorted(Comparator.comparing(e -> e.getValue()).reversed()) ??? doesn't work?
//          .sorted(Map.Entry.comparingByValue().reversed()) // also doesn't work
//          .sorted(reverseEntryByValue) // this does work!?
          .sorted(Map.Entry.<String, Long>comparingByValue().reversed()) // ??? doesn't work?
          .limit(200)
          .forEach(System.out::println);
    }
  }
}
