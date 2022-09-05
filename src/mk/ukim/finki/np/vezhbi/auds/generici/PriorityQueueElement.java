package mk.ukim.finki.np.vezhbi.auds.generici;


import org.jetbrains.annotations.NotNull;

public class PriorityQueueElement<T> implements Comparable<PriorityQueueElement<T>> {
    private T element;
    private int priority;

    public PriorityQueueElement(T element, int priority) {
        this.element = element;
        this.priority = priority;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return element +" "+ priority;
    }



    @Override
    public int compareTo(PriorityQueueElement<T> other) {
        return Integer.compare(other.priority, this.priority);
    }
}
