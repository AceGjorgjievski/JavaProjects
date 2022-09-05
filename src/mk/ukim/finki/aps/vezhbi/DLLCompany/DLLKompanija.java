package mk.ukim.finki.aps.vezhbi.DLLCompany;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.spi.FileTypeDetector;

class DLLNode {
    protected DLLNode pred, succ;
    protected int id, plata;

    public DLLNode(int id, int plata, DLLNode pred, DLLNode succ) {
        this.pred = pred;
        this.succ = succ;
        this.id = id;
        this.plata = plata;
    }
}


class DLL<E> {
    private DLLNode first, last;

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
        DLLNode ins = new DLLNode(id, plata, null, first);
        if (first == null)
            last = ins;
        else
            first.pred = ins;
        first = ins;
    }

    public void insertLast(int id, int plata) {
        if (first == null)
            insertFirst(id, plata);
        else {
            DLLNode ins = new DLLNode(id, plata, last, null);
            last.succ = ins;
            last = ins;
        }
    }

    public void insertAfter(int id, int plata, DLLNode after) {
        if(after==last){
            insertLast(id, plata);
            return;
        }
        DLLNode ins = new DLLNode(id, plata, after, after.succ);
        after.succ.pred = ins;
        after.succ = ins;
    }

    public void insertBefore(int id, int plata, DLLNode before) {
        if(before == first){
            insertFirst(id, plata);
            return;
        }
        DLLNode ins = new DLLNode(id, plata, before.pred, before);
        before.pred.succ = ins;
        before.pred = ins;
    }

    public DLLNode deleteFirst() {
        if (first != null) {
            DLLNode tmp = first;
            first = first.succ;
            if (first != null) first.pred = null;
            if (first == null)
                last = null;
            return tmp;
        } else
            return null;
    }

    public DLLNode deleteLast() {
        if (first != null) {
            if (first.succ == null)
                return deleteFirst();
            else {
                DLLNode tmp = last;
                last = last.pred;
                last.succ = null;
                return tmp;
            }
        }
        // else throw Exception
        return null;
    }


    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            DLLNode tmp = first;
            ret += tmp + "<->";
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += tmp + "<->";
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public void setFirst(DLLNode first) {
        this.first = first;
    }

    public void setLast(DLLNode last) {
        this.last = last;
    }

    public DLLNode getFirst() {
        return first;
    }

    public DLLNode getLast() {
        return last;
    }

    public DLLNode find(int id, int plata) {
        if(this.length() > 0) {
            DLLNode prv = this.getFirst();
            while(prv != null) {
                if(prv.id == id && prv.plata == plata) {
                    return prv;
                }
                prv = prv.succ;
            }
            return null;
        } else {
            return null;
        }

    }

    public DLL brisi_pomali_od(int iznos) {

        DLLNode prv = first;
        DLLNode vtor = first;
        DLLNode temp;

        while(prv != null) {
            if(prv.plata < iznos) {
                if(this.getFirst().equals(prv)) {
                    first = first.succ;
                    prv = first;
                    vtor = first;
                } else {
                    temp = prv.succ;
                    vtor.succ = temp;
                    prv = prv.succ;
                }
            } else {
                if(!this.getFirst().equals(prv)) {
                    vtor = vtor.succ;
                }
                prv = prv.succ;
            }
        }


        return this;
    }

    public DLL sort(){

        //kratka verzija


        return this;
    }

    public DLL sortiraj_opagacki() {

        DLLNode prv, vtor, node, temp=null;

        for(int i=0; i<length(); i++) {
            prv = first;
            vtor = first;
            node = prv.succ;

            while(node != null) {
                if(prv.id < node.id) {
                    if(this.getFirst().equals(prv)) {
                        temp = node.succ;

                        node.succ = prv;
                        prv.succ = temp;
                        this.setFirst(node);

                        node.pred = null;
                        temp.pred = prv;
                        prv.pred = node;

                        vtor = node;
                        node = temp;
                    } else if(this.getLast().equals(node)) {
                        node.succ = prv;
                        prv.succ = null;
                        this.setLast(prv);

                        prv.pred = node;
                        node.pred = vtor;

                        vtor.succ = node;

                        node = prv.succ;

                    } else {
                        temp = node.succ;

                        node.succ = prv;
                        prv.succ = temp;


                        temp.pred = prv;
                        prv.pred = node;
                        node.pred = vtor;

                        vtor.succ = node;

                        vtor = node;
                        node = temp;


                    }
                } else {
                    if(!this.getFirst().equals(prv)) {
                        vtor = vtor.succ;
                    }
                    prv = prv.succ;
                    node = node.succ;
                }
            }
        }


        return this;
    }

    public void pecati(DLL lista1) {
        DLLNode prv = lista1.getFirst();

        while(prv != null) {
            System.out.println(prv.id + " " + prv.plata);
            prv = prv.succ;
        }

    }
}

public class DLLKompanija {

    public static void main(String[] args) throws IOException {
        DLL lista1 = new DLL();
        BufferedReader stdin = new BufferedReader(new InputStreamReader(
                System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);

        for (int i = 0; i < N; i++) {
            s = stdin.readLine();
            String s1 = stdin.readLine();
            lista1.insertLast(Integer.parseInt(s), Integer.parseInt(s1));
        }
        s = stdin.readLine();

        lista1 = lista1.brisi_pomali_od(Integer.parseInt(s));
        if (lista1 != null) {
            lista1 = lista1.sortiraj_opagacki();
            lista1.pecati(lista1);
        }
    }
}
