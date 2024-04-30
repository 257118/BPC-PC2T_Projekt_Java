package projekt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




import java.util.List;

public class Kniha {

	private String nazov;
	private List<String> autor;
	private int rok;
	private boolean dostupnost;

	
	public Kniha(String nazov,List<String>autor,int rok,boolean dostupnost)
	{
		this.nazov=nazov;
		this.autor=autor;
		this.rok=rok;
		this.dostupnost=dostupnost;

		
	}

	public String getNazov() {
		return nazov;
	}

	public void setNazov(String nazov) {
		this.nazov = nazov;
	}

	public List<String> getAutor() {
		return autor;
	}

	public void setAutor(List<String> autor) {
		this.autor = autor;
	}

	public int getRok() {
		return rok;
	}

	public void setRok(int rok) {
		this.rok = rok;
	}

	public boolean isDostupnost() {
		return dostupnost;
	}

	public void setDostupnost(boolean dostupnost) {
		this.dostupnost = dostupnost;
	}


		
	

}
