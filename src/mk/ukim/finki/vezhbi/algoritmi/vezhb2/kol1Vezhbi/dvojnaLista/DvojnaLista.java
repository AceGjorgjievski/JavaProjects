package mk.ukim.finki.vezhbi.algoritmi.vezhb2.kol1Vezhbi.dvojnaLista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class DLLNode<E extends Comparable<E>> {
    protected E element;
    protected int countOccurences;
    protected DLLNode<E> pred, succ;

    public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
        this.element = elem;
        this.pred = pred;
        this.succ = succ;
        this.countOccurences = 1;
    }

//    @Override
//    public String toString() {
//        return element.toString() + "(" + countOccurences + ")";
//    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class DLL<E extends Comparable<E>>{
    private DLLNode<E> first, last;

    public DLL() {
        // Construct an empty SLL
        this.first = null;
        this.last = null;
    }

    public void deleteList() {
        first = null;
        last = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            DLLNode<E> tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;

    }

    public DLLNode<E> find(E o) {
        if (first != null) {
            DLLNode<E> tmp = first;
            while (tmp.element != o && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.element == o) {
                return tmp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
        return first;
    }

    public void insertFirst(E o) {
        DLLNode<E> ins = new DLLNode<E>(o, null, first);
        if (first == null)
            last = ins;
        else
            first.pred = ins;
        first = ins;
    }

    public void insertLast(E o) {
        if (first == null)
            insertFirst(o);
        else {
            DLLNode<E> ins = new DLLNode<E>(o, last, null);
            last.succ = ins;
            last = ins;
        }
    }

    public void insertAfter(E o, DLLNode<E> after) {
        if(after==last){
            insertLast(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, after, after.succ);
        after.succ.pred = ins;
        after.succ = ins;
    }

    public void insertBefore(E o, DLLNode<E> before) {
        if(before == first){
            insertFirst(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, before.pred, before);
        before.pred.succ = ins;
        before.pred = ins;
    }

    public E deleteFirst() {
        if (first != null) {
            DLLNode<E> tmp = first;
            first = first.succ;
            if (first != null) first.pred = null;
            if (first == null)
                last = null;
            return tmp.element;
        } else
            return null;
    }

    public E deleteLast() {
        if (first != null) {
            if (first.succ == null)
                return deleteFirst();
            else {
                DLLNode<E> tmp = last;
                last = last.pred;
                last.succ = null;
                return tmp.element;
            }
        }
        // else throw Exception
        return null;
    }

    public E delete(DLLNode<E> node) {
        if(node==first){
            deleteFirst();
            return node.element;
        }
        if(node==last){
            deleteLast();
            return node.element;
        }
        node.pred.succ = node.succ;
        node.succ.pred = node.pred;
        return node.element;

    }


    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder(new String());
        if (first != null) {
            DLLNode<E> tmp = first;
            if(tmp.succ == null){
                ret.append(tmp);
            } else {
                ret.append(tmp).append("<->");//1<->2
                while (tmp.succ != null) {
                    tmp = tmp.succ;
                    if(tmp.succ == null){
                        ret.append(tmp);
                    } else {
                        ret.append(tmp).append("<->");
                    }
                }
            }
            ret.append("<->");
        } else
            ret = new StringBuilder("Prazna lista!!!");
        return ret.toString();
    }
    //1<->2<->3
    public String toStringReverse() {
        StringBuilder ret = new StringBuilder(new String());
        if (last != null) {
            DLLNode<E> tmp = last;
            if(tmp.pred == null){
                ret.append(tmp);
            } else {
                ret.append(tmp).append("<->");
            }
            while (tmp.pred != null) {
                tmp = tmp.pred;
                if(tmp.pred == null){
                    ret.append(tmp);
                } else {
                    ret.append(tmp).append("<->");
                }
            }
            ret.append("<->");
        } else
            ret = new StringBuilder("Prazna lista!!!");
        return ret.toString();
    }

    public DLLNode<E> getFirst() {
        return first;
    }

    public DLLNode<E> getLast() {
        return last;
    }
    //1<->2<->3<->1<->5<->2 ==>  1<->2<->3<->5
    public void izvadiDupliPrebroj(){
        if(first != null){
            DLLNode<E> temp = first;
            DLLNode<E> temp2 = temp.succ;

            while (temp.succ != null){// i<n-1
                while (temp2 != null){//j<n
                    if(temp.element.compareTo(temp2.element) == 0){
                        temp.countOccurences++;
                        if(temp2.succ != null){
                            temp2 = temp2.succ;
                            this.delete(temp2.pred);
                        } else {
                            this.delete(temp2);
                            temp2 = null;
                        }
                    } else {
                        temp2 = temp2.succ;
                    }
                }
                temp = temp.succ;
                if(temp == null) break;
                temp2 = temp.succ;
            }
        }
    }
}

public class DvojnaLista {



    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        int M = Integer.parseInt(bf.readLine());

        DLL<Integer> lista1 = new DLL<>();
        DLL<Integer> lista2 = new DLL<>();
        DLL<Integer> result = new DLL<>();

        for(int i=0; i<N; i++) {
            lista1.insertLast(Integer.parseInt(bf.readLine()));
        }

        for(int i=0; i<M; i++) {
            lista2.insertLast(Integer.parseInt(bf.readLine()));
        }

        DLLNode<Integer> prv1 = lista1.getFirst();
        DLLNode<Integer> prv2 = lista2.getLast();

        //1 3 4 6 7
        //9 8 5 2 1
        //1 1 2 3 4 5 6 7

        //
        //9 8
        //1 1 2 3 4 5 6 7

        while ((prv1 != null)) {

            while ((prv2 != null)) {

                if (prv1.element.compareTo(prv2.element) < 0) {
                    result.insertLast(prv1.element);
                    break;
                } else if(prv1.element.compareTo(prv2.element) == 0) {
                    result.insertLast(prv2.element);
                } else {
                    result.insertLast(prv2.element);
                }
                prv2 = prv2.pred;
            }

            prv1 = prv1.succ;
        }

        while (prv1 != null) {
            result.insertLast(prv1.element);
            prv1 = prv1.succ;
        }

        while (prv2 != null) {
            result.insertLast(prv2.element);
            prv2 = prv2.pred;
        }

        System.out.println(result.toStringReverse());

        DLLNode<Integer> posle = result.getFirst();

        System.out.println(result.toString());

    }
}
