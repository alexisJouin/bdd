package tp_hibernate;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
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

    // **** Exercice 3 **** //
    //Get infos de toutes les entreprises
    public static void getInfosEntreprises() {
        List list = session.createQuery("from Entreprise ").list();
        Iterator it = list.iterator();

        displayLine();
        System.out.println("Liste des entreprises :");
        displayStar();
        while (it.hasNext()) {
            entreprise = (Entreprise) it.next();
            System.out.println(entreprise.getIdEntreprise() + ") " + entreprise.getNomEnt());
            System.out.println("Activités : " + entreprise.getListeActivites());
            System.out.println("Nombre d'employés : " + entreprise.getNbEmployes());
            displayStar();
        }
        displayLine();
    }

    //Get les infos d'une entreprise par son nom
    public static void getInfosEntreprise() {
        System.out.println("Saisir le nom de l'entreprise : ");
        String nomEnt = sc.nextLine();
        String hql = "FROM Entreprise WHERE nomEnt =:nomEnt";
        entreprise = new Entreprise();

        Query query = session.createQuery(hql);
        query.setParameter("nomEnt", nomEnt);

        Iterator it = query.list().iterator();
        System.out.println("Iterator n° " + it);

        entreprise = (Entreprise) it.next();
        displayLine();
        System.out.println("Info de l'entreprise : " + entreprise.getNomEnt());
        displayStar();
        System.out.println("Activités : " + entreprise.getListeActivites());
        System.out.println("Nombre d'employés : " + entreprise.getNbEmployes());
        displayStar();
        displayLine();
    }

    //Get infos de toutes les entreprises triés par nombres d'employés
    public static void getInfosEntreprisesOrdrerByNbEmploye() {
        List list = session.createQuery("FROM Entreprise ORDER BY nbEmployes ").list();
        Iterator it = list.iterator();

        displayLine();
        System.out.println("Liste des entreprises :");
        displayStar();
        while (it.hasNext()) {
            entreprise = (Entreprise) it.next();
            System.out.println(entreprise.getIdEntreprise() + ") " + entreprise.getNomEnt());
            System.out.println("Activités : " + entreprise.getListeActivites());
            System.out.println("Nombre d'employés : " + entreprise.getNbEmployes());
            displayStar();
        }
        displayLine();
    }

    public static void getNbEntreprises() {
//        Number nbEntreprises = (Number) session.createCriteria("Entreprise").setProjection(Projections.rowCount()).uniqueResult();

        List list = session.createQuery("FROM Entreprise").list();
        Iterator it = list.iterator();
        int nbEntreprises = 0;
        while (it.hasNext()) {
            entreprise = (Entreprise) it.next();
            nbEntreprises++;
        }
        System.out.println("Nombre d'entreprise : " + nbEntreprises);
    }

    public static void menu() {
        int choix = 1;
        System.out.println("TP HIBERNATE 2");
        while (choix != 0) {
            System.out.println("**** MENU ****");
            displayLine();
            System.out.println("1) Afficher les entreprises [EXO 1] ");
            System.out.println("2) Créer une nouvelle entreprise [EXO 1]");
            System.out.println("3) Trouver une entreprise [EXO 2]");
            System.out.println("4) Afficher les infos de toutes les entreprises [EXO 3]");
            System.out.println("5) Afficher les infos d'une entreprise [EXO 3]");
            System.out.println("6) Afficher et trier par nombre d'employés les infos des entreprises [EXO 3]");
            System.out.println("7) Afficher le nombre d'entreprises [EXO 3]");
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
                case 4:
                    getInfosEntreprises();
                    break;
                case 5:
                    getInfosEntreprise();
                    break;
                case 6:
                    getInfosEntreprisesOrdrerByNbEmploye();
                    break;
                case 7:
                    getNbEntreprises();
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
