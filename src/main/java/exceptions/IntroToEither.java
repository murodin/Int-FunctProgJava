package exceptions;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

class Either<R> {
  private R success;
  private Throwable failure;

  private Either(R success, Throwable failure) {
    this.success = success;
    this.failure = failure;
  }

  public static <R> Either<R> ofSuccess(R success) {
    return new Either(success, null);
  }

  public static <R> Either<R> ofFailure(Throwable t) {
    return new Either(null, t);
  }

  public void reportFailure() {
    if (failure != null) {
      System.out.println("Failure " + failure.getMessage());
    }
  }

  public R getSuccess() {
    if (failure != null) {
      throw new IllegalStateException(
          "attempt to get success from a failed Either");
    }
    return success;
  }

  public boolean isSuccess() {
    return failure == null;
  }

  public boolean isFailure() {
    return failure != null;
  }

  // recover is a lot like "flatMap of the failed situation"
  public Either<R> recover(Function<Throwable, Either<R>> op) {
    if (failure != null) {
      return op.apply(failure);
    } else return this;
  }
}

public class IntroToEither {

  public static <A, R> Function<A, Either<R>>
  wrap(exceptions.ExFunction<A, R> op) {
    return a -> {
      try {
        return Either.ofSuccess(op.apply(a));
      } catch (Throwable t) {
        return Either.ofFailure(t);
      }
    };
  }

  public static void main(String[] args) {
    List<String> files = List.of("a.txt", "b.txt", "c.txt");
    files.stream()
        .map(wrap(fn -> Files.lines(Paths.get(fn))))
        .peek(e -> e.reportFailure())
        .map(e -> e.recover(t ->
            wrap((String fn) -> Files.lines(Paths.get(fn))).apply("d.txt")))
        .filter(e -> e.isSuccess())
        .flatMap(e -> e.getSuccess())
        .forEach(System.out::println);
  }
}
