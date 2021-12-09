package cocontra;

import java.util.ArrayList;
import java.util.List;

public class TaxPrep {
  public static void prepareTaxes(Taxable t){}

  // "extends" in generics SIMPLY MEANS "is assignment compatible to"
  public static void prepareBulkTaxes(List<? extends Taxable> lt) {
//    lt.add(new Taxable()); // break BOTH a list of corporation AND of individual
//    lt.add(new Individual()); // break a list of corporation
//    lt.add(new Corporation());  // breaks a list of individual
    for (Taxable t : lt) {
//      if (t instanceof Individual) { cast//t.getSomethingUniqueToIndividual();
      prepareTaxes(t);
    }
  }

  // "super" in generics means "is assignable FROM"
  public static void collectJoesClients(List<? super Individual> clients) {
//    ? thing = new Individual();
//    clients.add(thing);

    clients.add(new Individual());
    clients.add(new Individual());
    clients.add(new Individual());
  }

  public static void main(String[] args) {
    List<Taxable> lt = new ArrayList<>();
    lt.add(new Corporation());
    lt.add(new Corporation());
    lt.add(new Individual());
    collectJoesClients(lt);
    prepareBulkTaxes(lt);

    List<Individual> joesClients = new ArrayList<>();
//    collectJoesClients(joesClients);
//    joesClients.add(new Individual());
//    joesClients.add(new Individual());

    prepareBulkTaxes(joesClients);

    List<Object> lo = new ArrayList<>();
    collectJoesClients(lo);
  }

}
