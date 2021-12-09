package cocontra;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ListProcessor {
  public static <E, F> List<F> process(
      List<E> input, Function<? super E, ? extends F> op) {
    List<F> output = new ArrayList<>();
    for (E e : input) {
//      ? it = e;
      output.add(op.apply(e));
    }
    return output;
  }

  public static void main(String[] args) {
    List<CharSequence> lcs = List.of("Fred", new StringBuilder("Jim"), "Sheila");
    Function<Object, StringBuilder> fcc =
        (c) -> new StringBuilder("Dear " + c);

    List<CharSequence> res = process(lcs, fcc);
    System.out.println(res);

  }
}
