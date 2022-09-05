package mk.ukim.finki.aps.vezhbi.SLLCompany;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class SLLNode {
    protected int id;
    protected int plata;
    protected SLLNode succ;

    public SLLNode(int id, int plata, SLLNode succ) {
        this.id = id;
        this.plata = plata;
        this.succ = succ;
    }
}

class SLL {
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
        // Vasiot kod tuka
        SLLNode p1 = first, p2 = first, temp = null;

        while (p1 != null) {
            if (p1.plata < iznos) {
                if (this.getFirst().equals(p1)) {
                    first = first.succ;
                    p1 = first;
                    p2 = first;
                } else {
                    temp = p1.succ;
                    p2.succ = temp;
                    p1 = p1.succ;
                }
            } else {
                if (!this.getFirst().equals(p1)) {
                    p2 = p2.succ;
                }
                p1 = p1.succ;
            }
        }


        return this;
    }

    public void setFirst(SLLNode first) {
        this.first = first;
    }

    public SLL sortiraj_opagacki() {
        // Vasiot kod tuka
        SLLNode p1, p2, node, temp;

        for (int i = 0; i < this.length(); i++) {
            p1 = first;
            p2 = first;
            node = p1.succ;

            while(node != null) {
                if(p1.id < node.id) {
                    temp = node.succ;
                    node.succ = p1;
                    p1.succ = temp;

                    if(this.getFirst().equals(p1)) {
                        p2 = node;
                        this.setFirst(node);
                    } else {
                        p2.succ = node;
                    }

                    p2 = node;
                    node = temp;
                } else {
                    if(!this.getFirst().equals(p1)) {
                        p2 = p2.succ;
                    }

                    p1 = p1.succ;
                    node = p1.succ;
                }

            }


        }


        return this;
    }

    public void pecati(SLL lista) {
        if (lista.length() == 0) {
            System.out.println("nema");
        } else {
            SLLNode p = lista.first;
            while (p != null) {
                System.out.println(p.id + " " + p.plata);
                p = p.succ;
            }
        }
    }

}

public class SLLKompanija {
    public static void main(String[] args) throws IOException {

        SLL lista1 = new SLL();
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
