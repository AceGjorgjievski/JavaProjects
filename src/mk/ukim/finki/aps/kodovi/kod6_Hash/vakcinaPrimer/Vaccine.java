package mk.ukim.finki.aps.kodovi.kod6_Hash.vakcinaPrimer;

import java.util.Objects;

public class Vaccine {
    private String name;
    private int dose;

    public Vaccine(String name, int dose) {
        this.name = name;
        this.dose = dose;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vaccine vaccine = (Vaccine) o;
        return dose == vaccine.dose && Objects.equals(name, vaccine.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dose);
    }

    @Override
    public String toString() {
        return "Vaccine{" +
                "name='" + name + '\'' +
                ", dose=" + dose +
                '}';
    }
}


