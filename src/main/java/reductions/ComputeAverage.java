package reductions;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

class Average {
  private double sum = 0;
  private long count = 0;

  public Average(double sum, long count) {
    this.sum = sum;
    this.count = count;
  }

//  public static Set<String> threads =
//      Collections.synchronizedSet(new HashSet<>());
  public Average merge(Average this, Average other) {
//    threads.add(Thread.currentThread().getName());
    return new Average(this.sum + other.sum, this.count + other.count);
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
    // create a stream of random numbers as *double*
    // Look at the Random class :)
    //  - in the range plus and minus X
    //  -- therefore the average should be about zero :)
    // map them to Averages of one value
    // reduce them to a single Average object
    // extract the result and print it

    long start = System.nanoTime();
    ThreadLocalRandom.current().doubles(4_000_000_000L, -Math.PI, +Math.PI)
        .parallel()
        .mapToObj(d -> new Average(d, 1))
        .reduce(new Average(0, 0), (a1, a2) -> a1.merge(a2))
        .get()
        .map(d -> "The average is " + d)
        .ifPresent(System.out::println);

//    System.out.println("There were " + Average.threads.size() + " threads");
//    for (String t : Average.threads) {
//      System.out.println(t);
//    }

    long time = System.nanoTime() - start;
    System.out.println("Time take is " + (time / 1_000_000_000.0) + " seconds");
  }
}
