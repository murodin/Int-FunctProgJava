package superiterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class SuperIterable<E> implements Iterable<E> {
  private Iterable<E> self;

  public SuperIterable(Iterable<E> self) {
    this.self = self;
  }

  public <F> SuperIterable<F> map(
      SuperIterable<E> this, Function<E, F> op) {
    List<F> res = new ArrayList<>();
    for (E e : this) {
      F f = op.apply(e);
      res.add(f);
    }
    return new SuperIterable<>(res);
  }

  // This is essentially "forEach" :)
//  public void withEvery(SuperIterable<E> this, Consumer<E> op) {
//    for (E e : this.self) {
//      op.accept(e);
//    }
//  }

  public SuperIterable<E> filter(
      /*@Deprecated */SuperIterable<E> this, Predicate<E> criterion) {
    List<E> res = new ArrayList<>();
    for (E e : this.self) {
      if (criterion.test(e)) {
        res.add(e);
      }
    }
    return new SuperIterable<>(res);
  }

  public Iterator<E> iterator() {
    return self.iterator();
  }
}
