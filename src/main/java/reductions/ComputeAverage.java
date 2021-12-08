package reductions;

import java.util.Optional;

class Average {
  private double sum = 0;
  private long count = 0;

  public Average(double sum, long count) {
    this.sum = sum;
    this.count = count;
  }

  public Average merge(Average other) {
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
    // create a stream of random numbers
    //  - in the range plus and minus X
    //  -- therefore the average should be about zero :)
    // map them to Averages of one value
    // reduce them to a single Average object
    // extract the result and print it
  }
}
