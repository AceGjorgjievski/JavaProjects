package mk.ukim.finki.aps.labs.kolokviumski1.Kompanija;

public class SLL {
    private SLLNode first;

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
            SLLNode tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;
    }


    public void insertFirst(int id, int plata) {
        SLLNode ins = new SLLNode(id, plata, first);
        first = ins;
    }

    public void insertLast(int id, int plata) {
        if (first != null) {
            SLLNode tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            SLLNode ins = new SLLNode(id, plata, null);
            tmp.succ = ins;
        } else {
            insertFirst(id, plata);
        }
    }

    public SLLNode getFirst() {
        return first;
    }


    public SLL brisi_pomali_od(int iznos) {

       SLLNode prev = first,curr = first;
       while(curr != null){
           if(curr.plata < iznos){
               if(curr == first){
                   first = curr.succ;
                   curr = prev = first;
               } else {
                   prev.succ = curr.succ;
                   curr = curr.succ;
               }
           } else {
               prev = curr;
               curr = curr.succ;
           }
       }


        return this;
    }

    public SLL sortiraj_opagacki() {
        // 20 50 70 20 20 50

        SLLNode p2,p1,node,temp;
        for(int i=0; i<length(); i++){
            p2=p1=first;
            node = p1.succ;
            while (node != null){
                if(p1.id < node.id){
                    temp = node.succ;
                    if(p1 == first){
                        node.succ = p1;
                        p1.succ = temp;
                        first = node;
                    } else {
                        node.succ = p1;
                        p1.succ = temp;
                        p2.succ = node;
                    }
                    p2 = node;
                    node = temp;
                } else {
                    if(p1 != first){
                        p2 = p2.succ;
                    }
                    p1 = p1.succ;
                    node = node.succ;
                }
            }
        }


        return this;
    }

    public SLL sort(){

        SLLNode curr = first, index = first;
        int temp;
        for(curr = first; curr.succ != null; curr = curr.succ){
            for(index = curr.succ; index != null; index = index.succ){
                if(curr.id < index.id){
                    temp = curr.id;
                    curr.id = index.id;
                    index.id = temp;
                }
            }
        }
        return this;
    }

    public void pecati(SLL lista) {
        SLLNode p = lista.first;
        if(p == null){
            System.out.println("nema");
        }
        while (p != null) {
            System.out.println(p.id + "," + p.plata);
            p = p.succ;
        }
    }

}

