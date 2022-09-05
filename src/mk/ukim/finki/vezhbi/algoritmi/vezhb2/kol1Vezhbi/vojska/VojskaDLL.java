package mk.ukim.finki.vezhbi.algoritmi.vezhb2.kol1Vezhbi.vojska;


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

    @Override
    public String toString() {
//        return element.toString() + "(" + countOccurences + ")";
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
        String ret = new String();
        if (first != null) {
            DLLNode<E> tmp = first;
            if(tmp.succ == null){
                ret += tmp;
            } else {
                ret += tmp + "<->";//1<->2
                while (tmp.succ != null) {
                    tmp = tmp.succ;
                    if(tmp.succ == null){
                        ret += tmp;
                    } else {
                        ret += tmp + "<->";
                    }
                }
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }
    //1<->2<->3
    public String toStringR() {
        String ret = new String();
        if (last != null) {
            DLLNode<E> tmp = last;
            if(tmp.pred == null){
                ret += tmp;
            } else {
                ret += tmp + "<->";
            }
            while (tmp.pred != null) {
                tmp = tmp.pred;
                if(tmp.pred == null){
                    ret += tmp;
                } else {
                    ret += tmp + "<->";
                }
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
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

public class VojskaDLL {

    public static void main(String[] args) throws IOException {

        /*
9
1 2 3 4 5 6 7 8 9
2 3
6 8
         */

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int numOfSoldiers = Integer.parseInt(bf.readLine());
        String [] soldiersID = bf.readLine().split("\\s++");

        String [] firstSubInterval = bf.readLine().split("\\s++");
        int firstStartID = Integer.parseInt(firstSubInterval[0]);
        int firstEndID = Integer.parseInt(firstSubInterval[1]);

        String [] secondSubInterval = bf.readLine().split("\\s++");
        int secondStartID = Integer.parseInt(secondSubInterval[0]);
        int secondEndID = Integer.parseInt(secondSubInterval[0]);

        DLL<Integer> integerDLL = new DLL<>();

        for(int i=0; i<soldiersID.length; i++) {
            integerDLL.insertLast(Integer.parseInt(soldiersID[i]));
        }
        DLL<Integer> prvInterval,vtorInterval;

        DLLNode<Integer> prv = integerDLL.getFirst();




    }
}
