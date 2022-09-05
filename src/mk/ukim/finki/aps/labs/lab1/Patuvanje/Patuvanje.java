package mk.ukim.finki.aps.labs.lab1.Patuvanje;

public class Patuvanje {
    private String imeAgencija;
    private int cenaPatuvanje;


    public Patuvanje() {

    }

    public Patuvanje(String imeAgencija, int cenaPatuvanje) {
        this.imeAgencija = imeAgencija;
        this.cenaPatuvanje = cenaPatuvanje;
    }

    public String getImeAgencija() {
        return imeAgencija;
    }

    public void setImeAgencija(String imeAgencija) {
        this.imeAgencija = imeAgencija;
    }

    public int getCenaPatuvanje() {
        return cenaPatuvanje;
    }

    public void setCenaPatuvanje(int cenaPatuvanje) {
        this.cenaPatuvanje = cenaPatuvanje;
    }

//    int vratiVremeVoDenovi() {
//        return 0;
//    }
}
