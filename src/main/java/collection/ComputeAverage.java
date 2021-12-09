package collection;


import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

class Average {
  private double sum = 0;
  private long count = 0;

  public Average(double sum, long count) {
    this.sum = sum;
    this.count = count;
  }

  public void include(double d) {
    this.sum += d;
    this.count++;
  }

  public void merge(Average this, Average other) {
    this.sum += other.sum;
    this.count += other.count;
  }

  public Optional<Double> get() {
    if (count > 0) {
      return Optional.of(sum / count);
    } else {
      return Optional.empty();
    }
  }

  @Override
  public String toString() {
    return "Average{" +
        "sum=" + sum +
        ", count=" + count +
        '}';
  }
}

public class ComputeAverage {
  public static void main(String[] args) {
    long start = System.nanoTime();
    ThreadLocalRandom.current().doubles(8_000_000_000L, -Math.PI, +Math.PI)
        .parallel()
        .collect(() -> new Average(0, 0),
            (a1, d) -> a1.include(d),
            (a1, a2) -> a1.merge(a2))
        .get()
        .map(d -> "The average is " + d)
        .ifPresent(System.out::println);

    long time = System.nanoTime() - start;
    System.out.println("Time take is " + (time / 1_000_000_000.0) + " seconds");
  }
}
