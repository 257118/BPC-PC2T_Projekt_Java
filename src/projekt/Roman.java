package projekt;

import java.util.List;

public class Roman extends Kniha {
	private String zaner;
	public Roman(String nazov,List<String>autor,int rok,boolean dostupnost, String zaner) {
		super(nazov,autor,rok,dostupnost);
		this.zaner=zaner;
	}
	public String getZaner() {
		return zaner;
	}
	public void setZaner(String zaner) {
		this.zaner = zaner;
	}
	

}
