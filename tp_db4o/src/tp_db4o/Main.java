package tp_db4o;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

import classes.Camion;
import classes.Camionnette;
import classes.Louer;
import classes.Personne;
import classes.Possede;
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
		System.out.println("Saisir le num�ro d'immatriculation : ");
		id = sc.nextLine();
		System.out.println("Saisir la marque : ");
		marque = sc.nextLine();
		System.out.println("Saisir le mod�le : ");
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
		System.out.println("Stock� " + v);
	}
	
	public static void addCamion() throws ParseException{
		getBasicAttribute();
		Scanner sc = new Scanner(System.in);
		System.out.println("Saisir la capacit� de chargement : ");
		int capacite = Integer.parseInt(sc.nextLine());
		
		Camion c = new Camion(id, marque, modele, dateAchat, dateVente, prixAchat, prixVente, capacite);
		db.store(c);
		System.out.println("Stock� " + c);
	}
	
	public static void addCamionnette() throws ParseException{
		getBasicAttribute();
		Scanner sc = new Scanner(System.in);
		System.out.println("Saisir le nombre de places : ");
		int capacite = Integer.parseInt(sc.nextLine());
		
		Camionnette c = new Camionnette(id, marque, modele, dateAchat, dateVente, prixAchat, prixVente, capacite);
		db.store(c);
		System.out.println("Stock� " + c);
	}
	
	public static void listResult(List<?> result){
		System.out.println("Nombre :" + result.size());
		for (Object o : result) {
			System.out.println(o);
		}
	}
	
	public static void displayVehicules(){
		//Affichage des v�hicules
		Vehicule vehicule = new Vehicule(null, null, null, null ,null , 0, 0);
		ObjectSet result = db.queryByExample(vehicule);
		listResult(result);
	}
	
	public static void displayInfosEx4(){
		//Affichage des Personnes
		Personne personne = new Personne(0,null,null);
		ObjectSet result = db.queryByExample(personne);
		listResult(result);
		
		//Affichage des achats
		Possede possede = new Possede(0, null, null, 0);
		ObjectSet result1 = db.queryByExample(possede);
		listResult(result1);
		
		//Affichage des locations
		Louer louer = new Louer(0, null, null, null, 0);
		ObjectSet result2 = db.queryByExample(louer);
		listResult(result2);
	}
	
	//Liste des enregistrements de l'exercice 4
	public static void exercice4() throws ParseException{
		/*
			Personne p1 = new Personne(1, "Dupont", "Michel");
			Possede po1 = new Possede(1, "AB 234 DG", sdf.parse("25/12/2017"), 25000);
			
			Personne p2 = new Personne(2, "Gruwez", "Emmanuel");
			Possede po2 = new Possede(2, "DC 125 QG", sdf.parse("30/05/2008"), 35000);
			
			Personne p3 = new Personne(3, "Revillon", "Sonia");
			Possede po3 = new Possede(3, "JK 65 BF", sdf.parse("04/10/2007"), 35000);
			
			Personne p4 = new Personne(4, "Stark", "Loic");
			Louer po4 = new Louer(4, "GL 37 D", sdf.parse("20/03/2008"), sdf.parse("27/03/2008"), 120);
			
			
			//Pour ajouter 3 jours 
			Date dt = sdf.parse("16/07/2006");
			Calendar c = Calendar.getInstance(); 
			c.setTime(dt); 
			c.add(Calendar.DATE, 3);
			dt = c.getTime();
			
			Personne p5 = new Personne(5, "Hammer", "Bertnard");
			Louer po5 = new Louer(5, "JK 65 BF", sdf.parse("16/07/2006"), dt , 120);
			
			//Enregistrement Base de donn�es
			db.store(p1);
			db.store(po1);
			db.store(p2);
			db.store(po2);
			db.store(p3);
			db.store(po3);
			db.store(p4);
			db.store(po4);
			db.store(p5);
			db.store(po5);
			
			*/
		
			displayInfosEx4();
			
			
	}
	
	public static void exercice5() throws ParseException{
		Possede possede = new Possede(0,"DC 125 QG", null, 0);
		ObjectSet<Possede> resultPossede = db.queryByExample(possede);
		
		if(resultPossede.size() > 0){
			int pnum = resultPossede.get(0).getPnum();
			Personne personne = new Personne(pnum, null, null);
			ObjectSet<Personne> resultProprietaire = db.queryByExample(personne);
			System.out.println("Propriétaire de DC 125 QG : ");
			listResult(resultProprietaire);
		}

		System.out.println("-----------------------------");
		
		possede =  new Possede(0,null, sdf.parse("04/10/2007"), 0);
		resultPossede = db.queryByExample(possede);
		if(resultPossede.size() > 0){
			String idVehicule = resultPossede.get(0).getVeh_id();
			System.out.println("Véhicule vendu le 04/10/2007 : " + idVehicule);
		}
		
		System.out.println("-----------------------------");
		
		List<Vehicule> vehSup40000 = db.query(new Predicate<Vehicule>() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean match(Vehicule arg0) {
				return arg0.getPrix_achat() > 40000;
			}
        });
		
		System.out.println("Véhicule avec prix > 40000 : ");
		listResult(vehSup40000);
		
		//Note pour Loic
		System.out.println("-----------------------------");
		Personne loic = new Personne(0, null, "Loic");
		ObjectSet<Personne> resultLoic = db.queryByExample(loic);
		
		if(resultLoic.size() > 0){
			Louer louerLoic = new Louer(resultLoic.get(0).getPnum(),null,null,null,0);
			ObjectSet<Louer> resultLouerLoic = db.queryByExample(louerLoic);
			
			if(resultLouerLoic.size() > 0){
				long diffInMillies = resultLouerLoic.get(0).getDate_retour().getTime() - resultLouerLoic.get(0).getDate_prise().getTime();
				long nbDays = diffInMillies/1000/60/60/24;
				System.out.println("Différence : " + nbDays + " jours.");
				int note = (int) (nbDays * resultLouerLoic.get(0).getPrix_location());
				System.out.println("Note pour loic : " + note);
			}
		}
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
            System.out.println("4) Lister les v�hicules");
            System.out.println("5) Enregistrements Exercices 4");
            System.out.println("6) Affichages Exercices 5");
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
                case 6:
                    exercice5();
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


