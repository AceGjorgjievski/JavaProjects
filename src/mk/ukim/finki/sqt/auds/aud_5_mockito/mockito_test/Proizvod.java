package mk.ukim.finki.sqt.auds.aud_5_mockito.mockito_test;

public class Proizvod {
	private String proizvodID;
	private String proizvodIme;
	private int kolicina;
	
	
	public Proizvod(String proizvodID,String proizvodIme,int kolicina){
		this.proizvodID=proizvodID;
		this.proizvodIme=proizvodIme;
		this.kolicina=kolicina;
	}

	public String getProizvodID(){
		return proizvodID;
	}

	public int getKolicina(){
		return kolicina;
	}
	public String getProizvodIme(){
		return proizvodIme;
	}

	
}
