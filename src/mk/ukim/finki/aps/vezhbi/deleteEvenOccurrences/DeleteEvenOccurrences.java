package mk.ukim.finki.aps.vezhbi.deleteEvenOccurrences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

class SLLNode<E> {
    protected E element;
    protected int numAppearances;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
        this.numAppearances = 1;
    }

    public int getNumAppearances() {
        return numAppearances;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class SLL<E extends Comparable<E>> {
    private SLLNode<E> first;

    public SLL() {
        // Construct an empty SLL
        this.first = null;
    }

    public void deleteList() {
        first = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            SLLNode<E> tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;

    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            SLLNode<E> tmp = first;
            if(tmp.succ == null) // if there is only one element in the list
                ret += tmp;
            else
                ret += tmp + "->";
            while (tmp.succ != null) {
                tmp = tmp.succ;
                if(tmp.succ == null)
                    ret += tmp;
                else
                    ret += tmp + "->";
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public void insertFirst(E o) {
        SLLNode<E> ins = new SLLNode<E>(o, first);
        first = ins;
    }

    public void insertAfter(E o, SLLNode<E> node) {
        if (node != null) {
            SLLNode<E> ins = new SLLNode<E>(o, node.succ);
            node.succ = ins;
        } else {
            System.out.println("Dadenot jazol e null===123");
        }
    }

    public void insertBefore(E o, SLLNode<E> before) {

        if (first != null) {
            SLLNode<E> tmp = first;
            if(first==before){
                this.insertFirst(o);
                return;
            }
            //ako first!=before
            while (tmp.succ != before)
                tmp = tmp.succ;
            if (tmp.succ == before) {
                SLLNode<E> ins = new SLLNode<E>(o, before);
                tmp.succ = ins;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
    }

    public void insertLast(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            SLLNode<E> ins = new SLLNode<E>(o, null);
            tmp.succ = ins;
        } else {
            insertFirst(o);
        }
    }

    public E deleteFirst() {
        if (first != null) {
            SLLNode<E> tmp = first;
            first = first.succ;
            return tmp.element;
        } else {
            System.out.println("Listata e prazna");
            return null;
        }
    }

    public E delete(SLLNode<E> node) {
        if (first != null) {
            SLLNode<E> tmp = first;
            if(first ==node){
                return this.deleteFirst();
            }
            while (tmp.succ != node && tmp.succ.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == node) {
                tmp.succ = tmp.succ.succ;
                return node.element;
            } else {
                System.out.println("Elementot ne postoi vo listata");
                return null;
            }
        } else {
            System.out.println("Listata e prazna");
            return null;
        }

    }

    public SLLNode<E> getFirst() {
        return first;
    }

    public SLLNode<E> find(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (!tmp.element.equals(o) && tmp.succ != null)
                tmp = tmp.succ;
//			while (tmp.element != o && tmp.succ != null)
//				tmp = tmp.succ;
            if (tmp.element.equals(o)) {
                return tmp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
        return first;
    }

    public Iterator<E> iterator () {
        // Return an iterator that visits all elements of this list, in left-to-right order.
        return new LRIterator<E>();
    }

    // //////////Inner class ////////////

    private class LRIterator<E> implements Iterator<E> {

        private SLLNode<E> place, curr;

        private LRIterator() {
            place = (SLLNode<E>) first;
            curr = null;
        }

        public boolean hasNext() {
            return (place != null);
        }

        public E next() {
            if (place == null)
                throw new NoSuchElementException();
            E nextElem = place.element;
            curr = place;
            place = place.succ;
            return nextElem;
        }

        public void remove() {
            //Not implemented
        }
    }

    public void mirror(){
        if (first != null) {
            //m=nextsucc, p=tmp,q=next
            SLLNode<E> tmp = first;
            SLLNode<E> newsucc = null;
            SLLNode<E> next;

            while(tmp != null){
                next = tmp.succ;
                tmp.succ = newsucc;
                newsucc = tmp;
                tmp = next;
            }
            first = newsucc;
        }

    }

    public void merge (SLL<E> in){
        if (first != null) {
            SLLNode<E> tmp = first;
            while(tmp.succ != null)
                tmp = tmp.succ;
            tmp.succ = in.getFirst();
        }
        else{
            first = in.getFirst();
        }
    }

    public SLL<E> joinLists(SLL<E> in){
        SLL<E> result = new SLL<E>();
        SLLNode<E> temp1 = in.getFirst(), temp2 = this.getFirst();
        while (temp1 != null && temp2 != null){
            if(temp1.element.compareTo(temp2.element) < 0){//temp1 < temp2
                result.insertLast(temp1.element);
                temp1 = temp1.succ;
            } else { // temp1 > temp2
                result.insertLast(temp2.element);
                temp2 = temp2.succ;
            }
        }

        if(temp1 != null){
            while (temp1 != null){
                result.insertLast(temp1.element);
                temp1 = temp1.succ;
            }
        }

        if(temp2 != null){
            while (temp2 != null){
                result.insertLast(temp2.element);
                temp2 = temp2.succ;
            }
        }
        return result;
    }

    public void removeDuplicates(){
        if(first != null){
            SLLNode<E> temp1 = getFirst();
            SLLNode<E> temp2 = temp1.succ;

            while(temp1.succ != null){
                while(temp2 != null){
                    if(temp1.element.compareTo(temp2.element) == 0){
                        SLLNode<E> temp3 = temp2;
                        if(temp2.succ != null){
                            temp2 = temp2.succ;
                            this.delete(temp3);
                        } else {
                            this.delete(temp2);
                            temp2 = null;
                        }
                    } else {
                        temp2 = temp2.succ;
                    }
                }
                if(temp1.succ == null) break;
                temp1 = temp1.succ;
                temp2 = temp1.succ;
            }

        }
    }
}

public class DeleteEvenOccurrences {

    public static void deleteEven(SLL<Integer> list1) {
        //todo: enter code here

        SLLNode<Integer> prv = list1.getFirst();
        SLLNode<Integer> vtor = prv.succ;
        SLLNode<Integer> forDeleting = null, temp = null;
        int counter = 0;
        while(prv != null) {
            while(vtor != null) {
                if(prv.element.equals(vtor.element)) {
                    counter++;
                }
                vtor = vtor.succ;
            }
            if(counter != 0 && counter %2 == 0) {
                vtor = list1.getFirst();

                while (vtor != null) {
                    if(vtor.element.equals(prv.element)) {
                        list1.delete(vtor);
                    }
                    vtor = vtor.succ;
                }
            }
            counter = 1;
            prv = prv.succ;
            vtor = list1.getFirst();
        }


    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        SLL<Integer> list1 = new SLL<Integer>();

        //todo: enter code here
        for(int i=0; i<n; i++) {
            int num = scanner.nextInt();
            list1.insertLast(num);
        }


        deleteEven(list1);

        System.out.println(list1);
        //todo: enter code here
    }
}
