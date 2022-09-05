package mk.ukim.finki.aps.labs.kolokviumski1.Kompanija2;

public class DLL {
    private DLLNode first,last;

    public DLL() {
        this.first = null;
        this.last = null;
    }

    public void deleteList(){
        first = null;
        last = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            DLLNode tmp = first;
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
        DLLNode ins = new DLLNode(id,plata, null, first);
        if (first == null)
            last = ins;
        else
            first.pred = ins;
        first = ins;
    }

    public void insertLast(int id, int plata) {
        if (first == null)
            insertFirst(id,plata);
        else {
            DLLNode ins = new DLLNode(id,plata, last, null);
            last.succ = ins;
            last = ins;
        }
    }

    public DLLNode getFirst() {
        return first;
    }

    public DLL brisi_pomali_od(int iznos) {

        // 20 50 70 20 20 50
        //testCases cases 4,6
        DLLNode prev = first,curr = first,temp;
        while(curr != null){
            if(curr.plata < iznos){
                if(curr == first){
                    first = curr.succ;
                    curr = prev = first;
                } else if(curr == last){
                    prev.succ = null;
                    //curr = curr.succ;
                    break;
                } else {
                    temp = curr.succ;
                    prev.succ = temp;
                    temp.pred = prev;

                    curr = curr.succ;
                }
            } else {
                prev = curr;
                curr = curr.succ;
            }
        }
        return this;
    }

    public DLL sortiraj_opagacki() {
        // 20 50 70 20 20 50

        DLLNode p1,p2,temp=null,node;


        for(int i=0; i<length(); i++){
            p1 = p2 = first;
            node = p1.succ;

            while(node != null){
                if(p1.id < node.id){
                    temp = node.succ;
                    if(p1 == first){

                        node.succ = p1;
                        p1.succ = temp;
                        first = node;

                        node.pred = null;
                        temp.pred = p1;
                        p1.pred = node;

                        p2 = node;
                        node = temp;
                        p1 = p1.succ;
                    } else if(node == last){
                        node.succ = p1;
                        p1.succ = null;
                        last = p1;

                        p1.pred = node;
                        node.pred = p2;

                        p2.succ = node;

                        node = temp;
                    } else {
                        node.succ = p1;
                        p1.succ = temp;

                        if(temp != null)
                            temp.pred = p1;
                        p1.pred = node;
                        node.pred = p2;

                        p2.succ = node;

                        p2 = node;
                        node = temp;
                    }
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


    public DLL sort(){

        DLLNode curr = first;
        DLLNode index = first;
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



    public void pecati(DLL lista) {
        DLLNode p = lista.first;
        if(p == null){
            System.out.println("nema");
        }
        while (p != null) {
            System.out.println(p.id + "," + p.plata);
            p = p.succ;
        }
    }
}
