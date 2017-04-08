package tp_db4o;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import classes.Camion;
import classes.Camionnette;
import classes.Vehicule;

public class Main {
	
	private static ObjectContainer db;
	private static String id;
	private static String marque;
	private static String modele;
	private static Date dateAchat;
	private static Date dateVente;
	private static int prixAchat;
	private static int prixVente;
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	
	public static void getBasicAttribute() throws ParseException{
		Scanner sc = new Scanner(System.in);
		System.out.println("Saisir le numéro d'immatriculation : ");
		id = sc.nextLine();
		System.out.println("Saisir la marque : ");
		marque = sc.nextLine();
		System.out.println("Saisir le modèle : ");
		modele = sc.nextLine();
		System.out.println("Saisir la date de l'achat ('jj/mm/aaaa') : ");
		dateAchat = sdf.parse(sc.nextLine());
		System.out.println("Saisir la date de vente ('jj/mm/aaaa') : ");
		dateVente = sdf.parse(sc.nextLine());
		System.out.println("Saisir le prix d'achat : ");
		prixAchat = Integer.parseInt(sc.nextLine());
		System.out.println("Saisir le prix de vente : ");
		prixVente = Integer.parseInt(sc.nextLine());
	}
	
	public static void addVoiture() throws ParseException{
		getBasicAttribute();
		Vehicule v = new Vehicule(id, marque, modele, dateAchat, dateVente, prixAchat, prixVente);
		db.store(v);
		System.out.println("Stocké " + v);
	}
	
	public static void addCamion() throws ParseException{
		getBasicAttribute();
		Scanner sc = new Scanner(System.in);
		System.out.println("Saisir la capacité de chargement : ");
		int capacite = Integer.parseInt(sc.nextLine());
		
		Camion c = new Camion(id, marque, modele, dateAchat, dateVente, prixAchat, prixVente, capacite);
		db.store(c);
		System.out.println("Stocké " + c);
	}
	
	public static void addCamionnette() throws ParseException{
		getBasicAttribute();
		Scanner sc = new Scanner(System.in);
		System.out.println("Saisir le nombre de places : ");
		int capacite = Integer.parseInt(sc.nextLine());
		
		Camionnette c = new Camionnette(id, marque, modele, dateAchat, dateVente, prixAchat, prixVente, capacite);
		db.store(c);
		System.out.println("Stocké " + c);
	}
	
	public static void listResult(List<?> result){
		System.out.println("Nombre de véhicules :" + result.size());
		for (Object o : result) {
			System.out.println(o);
		}
	}
	
	public static void displayVehicules(){
		//Affichage des véhicules
		Vehicule vehicule = new Vehicule(null, null, null, null ,null , 0, 0);
		ObjectSet result = db.queryByExample(vehicule);
		listResult(result);
	}
	
	//Liste des enregistrements de l'exercice 4
	public static void exercice4(){
			
	}
	
		
	public static void main(String[] args) throws ParseException {
		db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "db.bd4o");
		try {
			//Vehicule vehicule1 = new Vehicule("PG-JLM-2017","Opel", "CORSA", dateAchat, dateVente, 8999, 8599);
			menu();	
		}finally {
			db.close();
		}
	}
	
	
	
	public static void menu() throws ParseException {
        int choix = 1;
        Scanner sc = new Scanner(System.in);
        System.out.println("TP BDO ");
        while (choix != 0) {
            System.out.println("**** MENU ****");
            displayLine();
            System.out.println("1) Ajouter une Voiture");
            System.out.println("2) Ajouter un Camion");
            System.out.println("3) Ajouter une Camionnette");
            System.out.println("4) Lister les véhicules");
            System.out.println("5) Enregistrements Exercices 4");
            System.out.println("0) Quitter le programme ");
            displayLine();
            System.out.print("=> ");
            choix = Integer.parseInt(sc.nextLine());

            switch (choix) {
                case 1:
                    addVoiture();
                    break;
                case 2:
                    addCamion();
                    break;
                case 3:
                    addCamionnette();
                    break;
                case 4:
                    displayVehicules();
                    break;
                case 5:
                    exercice4();
                    break;
            }
        }
        
        System.out.println("FIN DU PROGRAMME ...");
    }

    public static void displayLine() {
        System.out.println("----------------------------------");
    }

    public static void displayStar() {
        System.out.println("*****");
    }

}


