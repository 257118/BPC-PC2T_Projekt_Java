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
	private Connect connect;
	
	private List<Kniha> odstraneneKnihy = new ArrayList<>();
	private List<Kniha> knihy;
	
	Scanner sc =new Scanner(System.in);
		public Knihovna() {
			this.connect=new Connect();
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
	        System.out.println("Kniha bola pridana do knihovny.");
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

	    System.out.println("Vyberte, co chcete upravit: ");
	    System.out.println("1. Autor/autori");
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
	            sc.nextLine();
	            najdenaKniha.setRok(novyRok);
	            break;
	        case 3:
	            System.out.println("Je kniha dostupna?(ano/ne)");
	            boolean novaDostupnost = sc.nextLine().equalsIgnoreCase("ano");
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
	    Kniha odstranenaKniha = null;

	    Iterator<Kniha> iterator = knihy.iterator();
	    while (iterator.hasNext()) {
	        Kniha kniha = iterator.next();
	        if (kniha.getNazov().equalsIgnoreCase(hladanaKniha)) {
	            odstranenaKniha = kniha;
	            iterator.remove();
	            System.out.println("Kniha " + hladanaKniha + " bola uspesne odstranena.");
	            break;
	        }
	    }

	    if (odstranenaKniha == null) {
	        System.out.println("Kniha " + hladanaKniha + " sa nenachadza v knihovne");
	    } else {
	        
	        odstraneneKnihy.add(odstranenaKniha);
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
            System.out.println();
            

		}
		
		
	}
	public void vyhladatKnihu() {
		System.out.println("Zadaj nazov knihy: ");
		String hladanaKniha=sc.nextLine();
		boolean najdenaKniha=false;
		
		for (Kniha kniha:knihy) {
			if(kniha.getNazov().equalsIgnoreCase(hladanaKniha)) {
				System.out.println("Nazov knihy: "+kniha.getNazov());
				System.out.println("Autor: "+String.join(",",kniha.getAutor()));
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
			        System.out.println("Kniha s nazvom '" + hladanaKniha + "' sa nenachadza v knihovne.");
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
		 System.out.println("Knihy od autora " + hladanyAutor + " v chronologickom poradi:");
		    for (Kniha kniha : knihyAutora) {
		        System.out.println("Nazov: " + kniha.getNazov());
		    }
		   
		    
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
		System.out.println();
	}
	public void pozicaneKnihy() {
		for (Kniha kniha:knihy) {
			if(!kniha.isDostupnost()) {
				String typKnihy =kniha instanceof Roman ? "Roman": kniha instanceof Ucebnice? "Ucebnice" : "Chybny typ";
				System.out.println("Nazov: "+kniha.getNazov()+","+" Typ knihy: "+typKnihy);
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
			bw.write("Autor/Autori: "+String.join(", ",najdenaKniha.getAutor())+"\n");
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
	public void ulozKnihyDoSqlDatabaze() {
		Connect connect=new Connect();
		connect.connect();
		connect.createTable();
	
		for(Kniha kniha:knihy) {
			connect.insertKniha(
			 kniha.getNazov(),
             String.join(", ", kniha.getAutor()),
             kniha.getRok(),
             kniha.isDostupnost(),
             kniha instanceof Roman ? ((Roman) kniha).getZaner() : null,
             kniha instanceof Ucebnice ? ((Ucebnice) kniha).getRocnik() : null
         );
			
		}
		 for (Kniha kniha : odstraneneKnihy) {
		        connect.deleteKniha(kniha.getNazov()); 
		    }
		    
		System.out.println("Knihy boli ulozene do databaze,");
		connect.disconnect();
		
		
	}
	public void nacitajKnihyZDatabaze() {
	    Connect connect = new Connect();
	    try {
	        connect.connect();
	        List<Kniha> nacitaneKnihy = (List<Kniha>) connect.selectAll();
	        knihy.addAll(nacitaneKnihy);
	        System.out.println("Knihy boli nacitane z databazy.");
	    } catch (Exception e) {
	        System.out.println("Chyba pri nacitani knh z databazy: " + e.getMessage());
	    } finally {
	        connect.disconnect();
	    }
	}
		
	

}
