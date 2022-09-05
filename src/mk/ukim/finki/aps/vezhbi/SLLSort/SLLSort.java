package mk.ukim.finki.aps.vezhbi.SLLSort;


import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
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

    public void setFirst(SLLNode<E> first) {
        this.first = first;
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


public class SLLSort {

    private static void sortSLL(SLL<Integer> list) {
        SLLNode<Integer> p1 = null,p2=null,n=null,temp=null;

        for(int i=0; i<list.length(); i++) {
            p2 = list.getFirst();
            p1 = list.getFirst();
            n = p1.succ;

            while(n != null) {
                if(p1.element < n.element) {
                    temp = n.succ;

                    n.succ = p1;
                    p1.succ = temp;

                    if(list.getFirst().equals(p1)) {
                        list.setFirst(n);
                    } else {
                        p2.succ = n;
                    }

                    p2 = n;
                    n = temp;

                } else {
                    if(!list.getFirst().equals(p1)) {
                        p2 = p2.succ;
                    }
                    p1 = p1.succ;
                    n = n.succ;
                }
            }
        }

        System.out.println(list);




    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        SLL<Integer> list = new SLL<Integer>();

        for(int i=0; i<n; i++) {
            list.insertLast(sc.nextInt());
        }

        sortSLL(list);

    }
}
