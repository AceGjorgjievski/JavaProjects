package mk.ukim.finki.aps.auds.av0.Zadacha;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        Vraboten [] pom = new Vraboten[n];
        for(int i=0; i<n;i++){
            Vraboten v = new Vraboten();
            v.setIme(input.next());
            v.setPrezime(input.next());
            v.setStazh(input.nextInt());
            v.setBrBodovi(input.nextInt());
            pom[i] = v;
            int p = input.nextInt();
            for(int j=0;j<p;j++){
                Zadacha z = new Zadacha();
                z.setBrChasovi(input.nextInt());
                z.setOpis(input.next());
                z.setStatus(input.nextBoolean());
                v.dodadiZadacha(z);
            }
        }
        Kompanija k = new Kompanija(pom);
        k.pecati();
        k.pecatiPoUspeshnost();
        System.out.println("Najangaziran vraboten e: "+k.najangaziran());
    }
}
