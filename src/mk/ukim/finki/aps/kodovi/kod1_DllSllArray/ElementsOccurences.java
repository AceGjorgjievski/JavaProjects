package mk.ukim.finki.aps.kodovi.kod1_DllSllArray;

public class ElementsOccurences<E extends Comparable<E>> {
    private E elements;
    private int numberOfOccurences;

    public ElementsOccurences(E elements, int numberOfOccurences) {
        this.elements = elements;
        this.numberOfOccurences = numberOfOccurences;
    }

}
