package mk.ukim.finki.sqt.auds.aud_5_mockito.mockito_test;

import java.util.List;

public class Klient {


	private ServisTroshoci servisTroshoci;
	private List<Proizvod> proizvodi;

	public ServisTroshoci getServisTroshoci(){
		return servisTroshoci;
	}

	public void setServisTroshoci(ServisTroshoci servisTroshoci){
		this.servisTroshoci=servisTroshoci;
	}

	public List<Proizvod> getProizvodi(){
		return proizvodi;
	}

	public void setProizvodi(List<Proizvod> proizvodi){
		this.proizvodi=proizvodi;
	}

	public double getCenaProizvod(){
		double cena=0.0;

		for(Proizvod proizvod:proizvodi){
			cena+=(servisTroshoci.getCena(proizvod)* proizvod.getKolicina());
		}
		return cena;
	}
}