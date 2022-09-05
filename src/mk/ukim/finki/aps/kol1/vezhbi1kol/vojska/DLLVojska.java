package mk.ukim.finki.aps.kol1.vezhbi1kol.vojska;

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

    public void setFirst(DLLNode<E> first) {
        this.first = first;
    }

    public void setLast(DLLNode<E> last) {
        this.last = last;
    }
}



public class DLLVojska {

    public static DLL<Integer> transform(DLL<Integer> soldiers,int a,int b, int c, int d) {

        DLLNode<Integer> curr = soldiers.getFirst();
        DLLNode<Integer> s1 = null,s2 = null,e1=null,e2=null;
        while(curr != null){
            if(curr.element.equals(a)) s1 = curr;
            if(curr.element.equals(b)) e1 = curr;
            if(curr.element.equals(c)) s2 = curr;
            if(curr.element.equals(d)) e2 = curr;

            curr = curr.succ;
        }

        DLLNode<Integer> temp,temp2,temp3,temp4;



//        System.out.println(prvLevo+ " "+prvDesno+ " "+vtorLevo+ " "+vtorDesno);

        s2.pred.succ = s1;
        if(s1 == soldiers.getFirst()) {
            s1.pred = s2.pred;
            s2.pred = null;
            soldiers.setFirst(s2);
        } else {
            s1.pred.succ = s2;

            temp = s1.pred;
            s1.pred = s2.pred;
            s2.pred = temp;
        }

        e1.succ.pred = e2;
        if(e2 == soldiers.getLast()) {
            e2.succ = e1.succ;
            e1.succ = null;
            soldiers.setLast(e1);
        } else {
            e2.succ.pred = e1;

            temp2 = e1.succ;
            e1.succ = e2.succ;
            e2.succ = temp2;
        }

        System.out.println(soldiers);
        return soldiers;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());

        DLL<Integer> soldiers = new DLL<>();

        String [] parts = bf.readLine().split("\\s++");
        for(int i=0; i<N; i++) {
            soldiers.insertLast(Integer.parseInt(parts[i]));
        }

        String [] indexesFirst = bf.readLine().split("\\s++");
        String [] indexesSecond = bf.readLine().split("\\s++");

        int startFirst = Integer.parseInt(indexesFirst[0]);
        int endFirst = Integer.parseInt(indexesFirst[1]);

        int startSecond = Integer.parseInt(indexesSecond[0]);
        int endSecond = Integer.parseInt(indexesSecond[1]);

        DLL<Integer> result = transform(soldiers,startFirst,endFirst,startSecond,endSecond);

//        System.out.println(soldiers);
//        DLLNode<Integer> node = result.getFirst();
//        System.out.print(node.element);
//        node = node.succ;
//        while(node != null){
//            System.out.print(" "+node.element);
//            node = node.succ;
//        }


    }
}
