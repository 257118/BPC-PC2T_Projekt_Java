package projekt;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    Knihovna knihovna = new Knihovna();
	    Connect connect=new Connect();
	    knihovna.nacitajKnihyZDatabaze();
	    

	    while (true) {
	    	System.out.println();
	        System.out.println("Zvolte moznost:");
	        System.out.println("1.  Pridat novou knihu");
	        System.out.println("2.  Upravit knihu");
	        System.out.println("3.  Odstranit knihu");
	        System.out.println("4.  Oznacit knihu ako vypozicanu/vratenu");
	        System.out.println("5.  Vypis knih");
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
	            	break;
	            case 10:
	            	knihovna.ulozKnihuDoSuboru();
	            	break;
	            case 11:
	            	knihovna.nactiKnihuZoSuboru();
	            	break;
	            case 12:
	            	knihovna.ulozKnihyDoSqlDatabaze();
	                System.out.println("Ukoncenie programu.");
	                sc.close();
	                return;
	            default:
	                System.out.println("Neplatná volba. Skuste to znovu.");
	        }
	    }
	}
}