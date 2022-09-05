package mk.ukim.finki.np.vezhbi.labs.generici.GenerichkiKontejner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResizableArray<T> {
    private T [] elements;
    private int size;
    private int capacity;

    public ResizableArray() {
        elements = (T[])new Object[10];
        this.capacity = 0;
        this.size = 10;
    }

    public void addElement(T element) {
        if(size == capacity) {
            size*=2;
            T [] temp = (T[])new Object[size];
            for(int i=0; i< elements.length; i++) {
                temp[i] = elements[i];
            }
            temp[elements.length] = element;
            elements = temp;
        }
    }

    public int getIndex(T element) {
        int ind = -1;
        for(int i=0; i< elements.length; i++) {
            if(elements[i]==(element)) {
                ind = i;
            }
        }
        return ind;
    }

    public boolean removeElement(T element) {
        int index = getIndex(element);
        if(index != -1) {
            T [] temp = (T[]) new Object[this.elements.length - 1];
            int j=0;
            for(int i=0; i<elements.length; i++) {
                if(i != index){
                    temp[j++] = elements[i];
                }
            }
            elements = temp;
            this.size--;
            return true;
        }
        return false;
    }

    public boolean contains(T element) {
        return getIndex(element) != -1;
    }

    public Object [] toArray(){
        return Arrays.stream(this.elements).toArray();
    }

    public boolean isEmpty(){
        return this.elements.length == 0;
    }

    public int count(){
        return elements.length;
    }

    public T elementAt(int index){
        for(int i=0; i< elements.length; i++) {
            if(i == index) return elements[i];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public static <T> void copyAll(ResizableArray<? super T> dest, ResizableArray<? extends T> src) {
        for(int i=0; i< src.size; i++) {
            dest.addElement(src.elementAt(i));
        }
    }
}

