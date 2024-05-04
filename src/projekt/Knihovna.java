package projekt;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Knihovna  {
	private List<Kniha> knihy;
	Scanner sc =new Scanner(System.in);
		public Knihovna() {
			this.knihy=new ArrayList<>();
		}
	public void pridatKnihu() {
		System.out.println("Vyberte typ knihy: ");
		System.out.println("1=Roman, 2=Ucebnice");
		int typ=sc.nextInt();
		sc.nextLine();
		
		System.out.println("Zadejte nazov knihy: ");
		String nazov=sc.nextLine();
		
		System.out.println("Zadaj meno autora:");
		String autory=sc.nextLine();
		List<String> autor = new ArrayList<>();
		for (String autorJmeno : autory.split(",")) {
            autor.add(autorJmeno.trim());
        }
		System.out.println("Zadaj rok vydania: ");
		int rok=sc.nextInt();
		 sc.nextLine(); 
		
		System.out.println("Je kniha dostupna? (ano/ne)");
		boolean dostupnost=sc.nextLine().equalsIgnoreCase("ano");
		
		Kniha  novaKniha;
		if (typ==1) {
			System.out.println("Zadaj zaner romanu: ");
			String zaner=sc.nextLine();
			novaKniha=new Roman(nazov,autor,rok,dostupnost,zaner);
		}
		else {
			System.out.println("Zadaj rocnik ucebnice:");
			int rocnik=sc.nextInt();
			sc.nextLine();
			novaKniha=new Ucebnice(nazov,autor,rok,dostupnost,rocnik);
		}
				
	      knihy.add(novaKniha);
	        System.out.println("Knihy byla přidána do knihovny.");
	    }
	public void upravaKnihy() {
	    System.out.println("Zadaj nazov knihy na upravu: ");
	    String hladanaKniha = sc.nextLine();

	    Kniha najdenaKniha = null; 
	    for (Kniha kniha : knihy) {
	        if (kniha.getNazov().equalsIgnoreCase(hladanaKniha)) {
	            najdenaKniha = kniha;
	            break;
	        }
	    }
	    if (najdenaKniha == null) {
	        System.out.println("Kniha s nazvom " + hladanaKniha + " nebola najdena.");
	        return;
	    }

	    System.out.println("Vyberte, co chcete upravit:");
	    System.out.println("1. Autor/autory");
	    System.out.println("2. Rok vydania");
	    System.out.println("3. Stav dostupnosti");
	    int volba = sc.nextInt();
	    sc.nextLine();

	    switch (volba) {
	        case 1:
	            System.out.println("Zadaj meno noveho autora/autorov:");
	            String noviAutori = sc.nextLine();
	            List<String> novyAutor = parseAutori(noviAutori);
	            najdenaKniha.setAutor(novyAutor);
	            break;
	        case 2:
	            System.out.println("Zadaj novy rok vydania: ");
	            int novyRok = sc.nextInt();
	            najdenaKniha.setRok(novyRok);
	            break;
	        case 3:
	            System.out.println("Je kniha dostupna?(ano/ne)");
	            boolean novaDostupnost = sc.next().equalsIgnoreCase("ano");
	            najdenaKniha.setDostupnost(novaDostupnost);
	            break;
	        default:
	            System.out.println("Neplatna volba");
	    }
	    System.out.println("Kniha bola uspesne upravena");
	}
	private List<String>parseAutori(String autori){
	        List<String> parsedAuthors = new ArrayList<>();
	        String[] authorArray = autori.split(",");
	        for (String author : authorArray) {
	            parsedAuthors.add(author.trim()); 
	        }
	        return parsedAuthors;
	    }
	
	public void odstranKnihu() {
	    System.out.println("Zadaj nazov knihy, ktora sa ma odstranit: ");
	    String hladanaKniha = sc.nextLine();
	    boolean najdenaKniha = false;

	    Iterator<Kniha> iterator = knihy.iterator();
	    while (iterator.hasNext()) {
	        Kniha kniha = iterator.next();
	        if (kniha.getNazov().equalsIgnoreCase(hladanaKniha)) {
	            iterator.remove();
	            najdenaKniha = true;
	            System.out.println("Kniha " + hladanaKniha + " bola uspesne odstranena.");
	            break;
	        }
	    }

	    if (!najdenaKniha) {
	        System.out.println("Kniha " + hladanaKniha + " sa nenachadza v knihovne");
	    }
	}
	public void stavKnihy() {
		System.out.println("Zadaj nazov knihy: ");
		String hladanaKniha=sc.nextLine();
		Kniha najdenaKniha=null;
		for (Kniha kniha:knihy) {
			if(kniha.getNazov().equalsIgnoreCase(hladanaKniha)) {
				najdenaKniha=kniha;
				break;
			}
		}
		if (najdenaKniha==null) {
			System.out.println("Kniha "+hladanaKniha+ "sa nenasla.");
			
		}
		System.out.println("Vyber moznost: ");
		System.out.println("Oznacit ako vypozicanu(1) alebo ako vratenu(2): ");
		int volba=sc.nextInt();
		sc.nextLine();
		
		switch (volba){
			case 1:
				najdenaKniha.setDostupnost(false);
				break;
			case 2:
				najdenaKniha.setDostupnost(true);
				break;
		}
		System.out.println("Stav knihy bol uspesne zmeneny.");
		
	}
	public void vypisKnih() {
		
		Collections.sort(knihy,Comparator.comparing(Kniha::getNazov));
		
		for (Kniha kniha:knihy) {
			System.out.println("Nazov: "+kniha.getNazov());
			System.out.println("Autor/Autori: "+String.join(",",kniha.getAutor()));
			if (kniha instanceof Roman) {
				System.out.println("Zaner: "+((Roman)kniha).getZaner());
			}
			else if (kniha instanceof Ucebnice) {
				System.out.println("Rocnik: "+((Ucebnice)kniha).getRocnik());
			}
			System.out.println("Rok vydania: "+kniha.getRok());
            System.out.println("Stav dostupnosti: " + (kniha.isDostupnost() ? "K dispozici" : "Vypozicane"));

		}
		
		
	}
	public void vyhladatKnihu() {
		System.out.println("Zadaj nazov knihy: ");
		String hladanaKniha=sc.nextLine();
		boolean najdenaKniha=false;
		
		for (Kniha kniha:knihy) {
			if(kniha.getNazov().equalsIgnoreCase(hladanaKniha)) {
				System.out.println("Nazov knihy: "+kniha.getNazov());
				System.out.println("Autor/Autori: "+String.join(",",kniha.getAutor()));
				if (kniha instanceof Roman) {
					System.out.println("Zaner: "+((Roman)kniha).getZaner());
				}
				else if (kniha instanceof Ucebnice) {
					System.out.println("Rocnik: "+((Ucebnice)kniha).getRocnik());
				}
				System.out.println("Rok vydania: "+kniha.getRok());
	            System.out.println("Stav dostupnosti: " + (kniha.isDostupnost() ? "K dispozici" : "Vypozicane"));
	            najdenaKniha = true;
	            break;
			}
			 if (!najdenaKniha) {
			        System.out.println("Kniha s názvom '" + hladanaKniha + "' sa nenachádza v knihovne.");
			 }
		}
	}
	public void knihyPodlaAutora() {
		System.out.println("Zadaj meno autora: ");
		String hladanyAutor=sc.nextLine();
		boolean najdenyAutor=false;
		List<Kniha>knihyAutora=new ArrayList<>();
		for (Kniha kniha:knihy) {
			List<String>autori=kniha.getAutor();
			if (autori.contains(hladanyAutor.trim())) {
				knihyAutora.add(kniha);
				najdenyAutor=true;
			}
		}
		if (!najdenyAutor) {
	        System.out.println("Kniha od autora '" + hladanyAutor + "' sa nenachadza v knihovne.");
	        return;
		}
		 knihyAutora.sort(Comparator.comparingInt(Kniha::getRok));
		 System.out.println("Knihy od autora " + hladanyAutor + " v chronologickom poradí:");
		    for (Kniha kniha : knihyAutora) {
		        System.out.println("Nazov: " + kniha.getNazov());
		    }
		    System.out.println();
		    
	}
	public void knihyPodlaZanru() {
		System.out.println("Zadaj zaner knihy: ");
		String hladanyZaner=sc.nextLine();
		boolean najdenyZaner=false;
		List<Kniha>knihyPodlaZanru=new ArrayList<>();
		
		for (Kniha kniha:knihy) {
			if (kniha instanceof Roman) {
				Roman roman=(Roman) kniha;
				if (roman.getZaner().equalsIgnoreCase(hladanyZaner)) {
					knihyPodlaZanru.add(kniha);
					
				}
			}
		}
		if (knihyPodlaZanru.isEmpty()) {
			System.out.println("Knihy zanru"+hladanyZaner+"sa nenachadzaju v knihovne.");
			
		}else {
			for (Kniha kniha:knihyPodlaZanru) {
				System.out.println("Nazov: "+kniha.getNazov());
			}
		}
	}
	public void pozicaneKnihy() {
		for (Kniha kniha:knihy) {
			if(!kniha.isDostupnost()) {
				String typKnihy =kniha instanceof Roman ? "Roman": kniha instanceof Ucebnice? "Ucebnice" : "Chybny typ";
				System.out.println("Nazov: "+kniha.getNazov()+"Typ knihy: "+typKnihy);
			}
		}
	}
	public void ulozKnihuDoSuboru() {
		System.out.println("Zadaj nazov knihy na uolzenie do suboru: ");
		String hladanaKniha=sc.nextLine();
		Kniha najdenaKniha=null;
		for (Kniha kniha:knihy) {
			if (kniha.getNazov().equalsIgnoreCase(hladanaKniha)) {
				najdenaKniha=kniha;
				break;
			}
		}
		if (najdenaKniha==null) {
			System.out.println("Kniha "+hladanaKniha+" nebola najdena.");
		}
		else {
			File subor =new File(hladanaKniha+".txt");
			try {
				FileWriter fw=new FileWriter(subor);
				BufferedWriter bw=new BufferedWriter(fw);
				
			bw.write("Nazov: "+najdenaKniha.getNazov()+ "\n");
			bw.write("Autor/Autory: "+String.join(", ",najdenaKniha.getAutor())+"\n");
			bw.write("Rok vydania: "+najdenaKniha.getRok()+"\n");
			bw.write("Dostupnost: "+(najdenaKniha.isDostupnost()?"Ano":"Ne")+"\n");
			if (najdenaKniha instanceof Roman) {
				Roman roman=(Roman)najdenaKniha;
				bw.write("Zaner: "+roman.getZaner()+ "\n");
			}
			else if (najdenaKniha instanceof Ucebnice) {
				Ucebnice ucebnice=(Ucebnice)najdenaKniha;
				bw.write("Rocnik:"+ ucebnice.getRocnik()+"\n");
			}
			bw.close();
			System.out.println("Kniha bola uspesne ulozena do suboru.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Pri zapise knihy do suboru sa vyskytla chyba: "+e.getMessage());
			}
			
		}
		
	}
	public void nactiKnihuZoSuboru() {
		System.out.println("Zadaj nazov knihy: ");
		String hladanaKniha=sc.nextLine();
		File subor=new File(hladanaKniha+".txt");
		if (!subor.exists()) {
			System.out.println("Subor s nazvom "+subor.getName()+" neexistuje.");
			return;
		}
		try {
			BufferedReader br=new BufferedReader(new FileReader(subor));
			String line;
			while((line=br.readLine())!=null) {
				System.out.println(line);
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Chyba pri nacitani suboru: "+e.getMessage());
		}
	}
		
	
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    Knihovna knihovna = new Knihovna();

	    while (true) {
	        System.out.println("Zvolte moznost:");
	        System.out.println("1.  Pridat novou knihu");
	        System.out.println("2.  Upravit knihu");
	        System.out.println("3.  Odstranit knihu");
	        System.out.println("4.  Oznacit knihu ako vypozicanu/vratenu");
	        System.out.println("5.  Vypis knihy");
	        System.out.println("6   Vyhladat knihu:");
	        System.out.println("7.  Vypis knih podla autora");
	        System.out.println("8.  Vypis knih podla zanru");
	        System.out.println("9.  Vypis vypozicanych knih");
	        System.out.println("10. Zapis knihy do suboru.");
	        System.out.println("11. Nacitanie knihy zo suboru.");
	        System.out.println("12. Konec");

	        int volba = sc.nextInt();
	        sc.nextLine();
	        

	        switch (volba) {
	            case 1:
	                knihovna.pridatKnihu();
	                break;
	            case 2:
	                knihovna.upravaKnihy();
	                break;
	            case 3:
	                knihovna.odstranKnihu();
	                break;
	            case 4:
	                knihovna.stavKnihy();
	                break;
	            case 5:
	                knihovna.vypisKnih();
	                break;
	            case 6:
	            	knihovna.vyhladatKnihu();
	            	break;
	            case 7:
	            	knihovna.knihyPodlaAutora();
	            	break;
	            case 8:
	            	knihovna.knihyPodlaZanru();
	            	break;
	            case 9:
	            	knihovna.pozicaneKnihy();
	            case 10:
	            	knihovna.ulozKnihuDoSuboru();
	            	break;
	            case 11:
	            	knihovna.nactiKnihuZoSuboru();
	            	break;
	            case 12:
	                System.out.println("Ukončení programu.");
	                sc.close();
	                return;
	            default:
	                System.out.println("Neplatná volba. Zkuste to znovu.");
	        }
	    }
	}
}
