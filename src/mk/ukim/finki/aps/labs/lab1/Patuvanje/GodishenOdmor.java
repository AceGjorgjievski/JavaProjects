package mk.ukim.finki.aps.labs.lab1.Patuvanje;

public class GodishenOdmor extends Patuvanje{
    private int vreme;

    public GodishenOdmor(String ime, int cena, int vreme) {
        super();
        this.vreme = vreme;
    }

    @Override
    public String toString() {
        return "GodishenOdmor{" +
                "ime='" + getImeAgencija() + '\'' +
                ", cena=" + getCenaPatuvanje() +
                ", denovi=" + vreme +
                '}';
    }

    public int getDenovi() {
        return vreme;
    }

    public void setDenovi(int vreme) {
        this.vreme = vreme;
    }
}


