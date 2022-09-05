package mk.ukim.finki.np.vezhbi.za_juni.box;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Box<E extends Drawable> {
    private List<E> elements;
    public static Random RANDOM = new Random();

    public Box() {
        this.elements = new ArrayList<>();
    }

    public void add(E element) {
        this.elements.add(element);
    }

    public boolean isEmpty() {
        return this.elements.size() == 0;
    }

    public E drawElement() {
        if (this.isEmpty()) return null;

        int index = RANDOM.nextInt(this.elements.size());

        return this.elements.remove(index);
    }
}
