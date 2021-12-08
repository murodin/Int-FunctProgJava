package exceptions;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

@FunctionalInterface
interface ExFunction<A, R> {
  R apply(A a) throws Throwable;
}

// Instead of returning Optional (which cannot express the *reason*
// for failure, we might create an "Either".. this is a container of
// *either* the result, *or* the reason for failure. Look for Either
// in the vavr library.

public class UsingExceptions {

//  Predicate<Student> ps = (Student s) -> { return s.getGpa() > 3; } ;
//  Predicate<Student> ps = s -> s.getGpa() > 3 ;

//  public static function-reporting-failure-with-empty-optional
//    wrap(function-reporting-failure-with-exception)
  public static <A, R> Function<A, Optional<R>>
    wrap(ExFunction<A, R> op) {
    return a -> {
      try {
        return Optional.of(op.apply(a));
      } catch (Throwable t) {
        // report the error to stdout??
        return Optional.empty();
      }
    };
  }

//  public static Optional<Stream<String>> getLines(String fn) {
//    try {
//      return Optional.of(Files.lines(Path.of(fn)));
//    } catch (Throwable t) {
////      throw new RuntimeException(t);
//      return Optional.empty();
//    }
//  }

  public static void main(String[] args) {
    List<String> files = List.of("a.txt", "b.txt", "c.txt");
      files.stream()
//          .flatMap(n -> getLines(n)) // when getLines returns Stream<String>
//          .map(n -> getLines(n))
          .map(wrap(fn -> Files.lines(Paths.get(fn))))
          .peek(opt -> { if (opt.isEmpty()) System.out.println("missing file...");})
          .filter(opt -> opt.isPresent())
          .flatMap(opt -> opt.get())
          .forEach(System.out::println);
  }
}
