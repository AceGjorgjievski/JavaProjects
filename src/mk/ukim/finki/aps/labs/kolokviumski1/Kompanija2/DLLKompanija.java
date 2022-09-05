package mk.ukim.finki.aps.labs.kolokviumski1.Kompanija2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DLLKompanija {


    public static void main(String[] args) throws IOException {

        DLL lista1 = new DLL();
        BufferedReader stdin = new BufferedReader(new InputStreamReader(
                System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);

        for (int i = 0; i < N; i++) {
            s=stdin.readLine();
            String s1=stdin.readLine();
            lista1.insertLast(Integer.parseInt(s),Integer.parseInt(s1));
        }
        s = stdin.readLine();

        lista1=lista1.brisi_pomali_od(Integer.parseInt(s));
        if(lista1!=null)
        {
            lista1=lista1.sortiraj_opagacki();
           // lista1=lista1.sort();
            lista1.pecati(lista1);
        }
    }


    /*
5
333
34569
440
45000
660
39000
778
41000
559
33999
25000
     */
}
