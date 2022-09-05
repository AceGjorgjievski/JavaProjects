package mk.ukim.finki.np.vezhbi.auds.generici;

import java.util.ArrayList;
import java.util.List;

public class PriorityQueue<T> {
    private List<PriorityQueueElement<T>> elements;

    public PriorityQueue() {
        elements = new ArrayList<>();
    }

    public void addElement(T element, int priority) {
        PriorityQueueElement<T> newElement = new PriorityQueueElement<>(element,priority);

        int i;
        for(i=0; i<elements.size(); i++) {
            if(elements.get(i).compareTo(newElement) <= 0) break;
        }
        elements.add(i,newElement);
    }

    public T remove() {
        if(elements.size() < 1) return null;

        return elements.remove(elements.size()-1).getElement();
    }
}
