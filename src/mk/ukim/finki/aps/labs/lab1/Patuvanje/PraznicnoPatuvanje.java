package mk.ukim.finki.aps.labs.lab1.Patuvanje;

public class PraznicnoPatuvanje extends Patuvanje{
    private String ime;
    private int cena;
    private int pochetenDatum;
    private int pocetenMesec;
    private int kraenDatum;
    private int kraenMesec;


    public PraznicnoPatuvanje(String ime, int cena, int pocD, int pocM, int krajD, int krajM) {
        super();
        this.ime = ime;
        this.cena = cena;
        this.pochetenDatum = pocD;
        this.pocetenMesec = pocM;
        this.kraenDatum = krajD;
        this.kraenMesec = krajM;
    }

    public String getIme() {
        return ime;
    }

    public int getCena() {
        return cena;
    }

    public int getPochetenDatum() {
        return pochetenDatum;
    }

    public int getPocetenMesec() {
        return pocetenMesec;
    }

    public int getKraenDatum() {
        return kraenDatum;
    }

    public int getKraenMesec() {
        return kraenMesec;
    }
}
