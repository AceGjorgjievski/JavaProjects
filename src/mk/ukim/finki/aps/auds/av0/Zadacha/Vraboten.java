package mk.ukim.finki.aps.auds.av0.Zadacha;

import java.util.Arrays;

public class Vraboten {
    private String ime;
    private String prezime;
    private static double BOD=50;
    private double plata;
    private int stazh;
    private int brBodovi;
    private Zadacha [] zadachi;
    private int brZadachi;

    public Vraboten() {
        zadachi = new Zadacha[10];
        brZadachi=0;
    }

    public Vraboten(String ime, String prezime, int stazh, int brBodovi) {
        this.ime = ime;
        this.prezime = prezime;
        this.stazh = stazh;
        this.brBodovi = brBodovi;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public double getPlata() {
        return plata;
    }

    public void setPlata(double plata) {
        this.plata = plata;
    }

    public int getStazh() {
        return stazh;
    }

    public void setStazh(int stazh) {
        this.stazh = stazh;
    }

    public int getBrBodovi() {
        return brBodovi;
    }

    public void setBrBodovi(int brBodovi) {
        this.brBodovi = brBodovi;
    }

    public Zadacha[] getZadachi() {
        return zadachi;
    }

    public void setZadachi(Zadacha[] zadachi) {
        this.zadachi = zadachi;
    }

    public int getBrZadachi() {
        return brZadachi;
    }

    public void setBrZadachi(int brZadachi) {
        this.brZadachi = brZadachi;
    }

    public void dodadiZadacha(Zadacha z){
        if(brZadachi == 10){
            System.out.println("Ne mozhe da se dodade povekje zadachi");
        } else {
            this.zadachi[brZadachi++] = z;
        }
    }
    public int vkupnoChasovi(){
        int sum=0;
        for (int i=0;i<brZadachi;i++) {
            sum+=zadachi[i].getBrChasovi();
        }
        return sum;
    }

    public double vratiProcent(){
        double counter=0;
        for(int i=0;i<brZadachi;i++){
            if(zadachi[i].isStatus()){
                counter++;
            }
        }
        return ((double) counter / brZadachi);
    }

    @Override
    public String toString() {
        return "Vraboten{" +
                "ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", plata=" + plata +
                ", stazh=" + stazh +
                ", brBodovi=" + brBodovi +
                ", zadachi=" + Arrays.toString(zadachi) +
                ", brZadachi=" + brZadachi +
                '}';
    }


}
