package tp_hibernate;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tp_hibernate_new.Entreprise;

public class Main {

    private static Session session;
    private static Transaction tx;
    private static Entreprise entreprise;
    private static Scanner sc;

    public static void main(String[] args) {

        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            sc = new Scanner(System.in);

            menu();//Lancement du menu

            System.out.println("FIN DU PROGRAMME");

            tx.commit();

            NewHibernateUtil.getSessionFactory().close();
        } catch (HibernateException e) {
            System.out.println("Erreur : ");
            e.printStackTrace();
        }
    }

    //Liste toutes les entrprises
    public static void displayEntreprises() {
        List list = session.createQuery("from Entreprise ").list();
        Iterator it = list.iterator();

        displayLine();
        System.out.println("Liste des entreprises :");
        displayStar();
        while (it.hasNext()) {
            entreprise = (Entreprise) it.next();
            System.out.println(entreprise.getIdEntreprise() + ") " + entreprise.getNomEnt());
        }
        displayStar();
        displayLine();
    }

    //Ajoute une entreprise
    public static void ajouterEntreprise() {
        displayLine();
        //Saisie des informations
        System.out.println("Création d'une entreprise : ");
        System.out.println("Entrez le nom : ");
        String nomEnt = sc.nextLine();
        System.out.println("Entrez la liste des activitées : ");
        String listeActivites = sc.nextLine();
        System.out.println("Entrez le nombre d'employés : ");
        int nbEmployes = Integer.parseInt(sc.nextLine());

        //Création de l'entreprise
        entreprise = new Entreprise();
        entreprise.setNomEnt(nomEnt);
        entreprise.setListeActivites(listeActivites);
        entreprise.setNbEmployes(nbEmployes);

        session.save(entreprise);//Sauvegarde en bdd

        System.out.println("Enregistrement de l'entreprise " + nomEnt + " terminé.");
        displayLine();
    }

    //Exercice 2
    public static void getEntreprise() {

        System.out.println("Saisir l'id de l'entreprise : ");
        int idEntreprise = Integer.parseInt(sc.nextLine());
        String hql = "SELECT nomEnt FROM Entreprise WHERE idEntreprise =:idEntreprise";
        entreprise = new Entreprise();

        Query query = session.createQuery(hql);
        query.setParameter("idEntreprise", idEntreprise);

        List result = query.list();
        System.out.println("Entreprise n° " + idEntreprise + " : " + result);

//        Iterator it = result.iterator();
//        
//        while (it.hasNext()) {
//            entreprise = (Entreprise) it.next();
//            System.out.println(entreprise.getIdEntreprise() + ") " + entreprise.getNomEnt());
//        }
    }

    public static void menu() {
        int choix = 1;

        while (choix != 0) {
            System.out.println("TP HIBERNATE 2");
            displayLine();
            System.out.println("1) Afficher les entreprises ");
            System.out.println("2) Créer une nouvelle entreprise ");
            System.out.println("3) Trouver une entreprise ");
            System.out.println("0) Quitter le programme ");
            displayLine();
            System.out.print("=> ");
            choix = Integer.parseInt(sc.nextLine());

            switch (choix) {
                case 1:
                    displayEntreprises();
                    break;
                case 2:
                    ajouterEntreprise();
                    break;
                case 3:
                    getEntreprise();
                    break;
            }
        }
    }

    public static void displayLine() {
        System.out.println("----------------------------------");
    }

    public static void displayStar() {
        System.out.println("*****");
    }
}
