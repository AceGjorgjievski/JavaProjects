package mk.ukim.finki.vezhbi.algoritmi.vezhb2.techAlgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
        String ret = "";
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

    public SLL<E> joinLists(SLL<E> lista2) {
        SLL<E> resutList = new SLL<>();
        SLLNode<E> prv = this.getFirst(), vtor = lista2.getFirst();

        this.removeDuplicates();
        lista2.removeDuplicates();

        // 2 3  // 0 1 4 6 7
        while (prv != null && vtor != null) {
            if(prv.element.compareTo(vtor.element) < 0) {
                resutList.insertLast(prv.element);
                prv = prv.succ;
            } else {
                resutList.insertLast(vtor.element);
                vtor = vtor.succ;
            }
        }

        while(prv != null){
            resutList.insertLast(prv.element);
            prv = prv.succ;
        }

        while(vtor != null) {
            resutList.insertLast(vtor.element);
            vtor = vtor.succ;
        }

        return resutList;
    }

    public SLL<E> listiNaizmenichno(SLL<E> lista2) {

        SLL<E> resultList = new SLL<>();
        SLLNode<E> prv = this.getFirst(),vtor = lista2.getFirst();

        //
        //4

        //1 2 3

        while (prv != null && vtor != null) {

            resultList.insertLast(prv.element);
            if(prv.succ != null) {
                resultList.insertLast(prv.succ.element);
                prv = prv.succ;
            }

            resultList.insertLast(vtor.element);
            if(vtor.succ != null) {
                resultList.insertLast(vtor.succ.element);
                vtor = vtor.succ;
            }

            prv = prv.succ;
            vtor = vtor.succ;
        }

        while (prv != null) {
            resultList.insertLast(prv.element);
            prv = prv.succ;
        }

        while (vtor != null) {
            resultList.insertLast(vtor.element);
            vtor = vtor.succ;
        }



        return resultList;
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

   public void removeDuplicates(){
        if(this.length() != 0 || this.length() != 1) {

            //1 2 2 2 3 => 1 2 3
            SLLNode<E> temp = getFirst();
            while (temp != null) {
                SLLNode<E> temp2 = temp.succ;
                while (temp2 != null) {
                    if(temp.element.compareTo(temp2.element) == 0) {
                        this.delete(temp2);
                    }
                    temp2 = temp2.succ;
                }
                temp = temp.succ;
            }
        }
   }
}


public class Test {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        SLL<Integer> lista1 = new SLL<>();

        for(int i=0; i<N; i++) {
            int temp = Integer.parseInt(bf.readLine());
            lista1.insertLast(temp);
        }
        lista1.removeDuplicates();

        int M = Integer.parseInt(bf.readLine());

        SLL<Integer> lista2 = new SLL<>();

        for(int i=0; i<M; i++) {
            int temp = Integer.parseInt(bf.readLine());
            lista2.insertLast(temp);
        }
        lista2.removeDuplicates();

        SLL<Integer> finalList = new SLL<>();

        finalList = lista1.joinLists(lista2);
        finalList.removeDuplicates();

        System.out.println(finalList.toString());




    }
}
