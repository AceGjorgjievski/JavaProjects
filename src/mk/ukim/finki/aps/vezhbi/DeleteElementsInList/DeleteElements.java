package mk.ukim.finki.aps.vezhbi.DeleteElementsInList;


import java.util.Scanner;

class DLLNode<E> {
    protected E element;
    protected int numAppearances;
    protected DLLNode<E> pred, succ;

    public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
        this.element = elem;
        this.pred = pred;
        this.succ = succ;
        this.numAppearances = 1;
    }

    @Override
    public String toString() {
        return this.element.toString() + "(" + this.numAppearances + ")";
    }
}


class DLL<E> {
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


    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            DLLNode<E> tmp = first;
            ret += tmp + "<->";
            while (tmp.succ != null) {
                tmp = tmp.succ;
                if(tmp.succ == null) {
                    ret += tmp;
                } else {
                    ret += tmp + "<->";
                }
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public void setFirst(DLLNode<E> first) {
        this.first = first;
    }

    public void setLast(DLLNode<E> last) {
        this.last = last;
    }

    public DLLNode<E> getFirst() {
        return first;
    }

    public DLLNode<E> getLast() {
        return last;
    }

    public DLLNode<E> find(E elem) {
        if(this.length() > 0) {
            DLLNode<E> prv = this.getFirst();
            while(prv != null) {
                if(prv.element.equals(elem)) {
                    return prv;
                }
                prv = prv.succ;
            }
            return null;
        } else {
            return null;
        }
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

}


public class DeleteElements {

    private static void removeElements(DLL<Integer> list) {
        DLLNode<Integer> p1 = list.getFirst();
        DLLNode<Integer> p2 = p1.succ, temp = null;

        while(p1.succ != null) {
            while(p2 != null) {
                if(p1.element.compareTo(p2.element) == 0) {
                    p1.numAppearances++;
                    temp = p2;
                    p2 = p2.succ;
                    list.delete(temp);
                } else {
                    p2 = p2.succ;
                }
            }

            p1 = p1.succ;
            if(p1 == null) {
                break;
            }
            p2 = p1.succ;
        }
    }

    public static void main(String[] args) {
        DLL<Integer> list = new DLL<>();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for(int i=0; i<n; i++) {
            list.insertLast(sc.nextInt());
        }

        removeElements(list);
        System.out.println(list);
    }
}
