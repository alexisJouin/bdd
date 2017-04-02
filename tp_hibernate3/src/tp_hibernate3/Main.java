package tp_hibernate3;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tp_hibernate3_new.Clients;
import tp_hibernate3_new.Passagers;
import tp_hibernate3_new.Reservations;

public class Main {

    private static Session session;
    private static Transaction tx;
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

    //Exercice 1
    public static void displayPassenger714Flight6Mounth() {
        Calendar minDate = Calendar.getInstance();
        minDate.add(Calendar.DATE, -183);//183 jours en 6 mois

        List list = session.createQuery("FROM Clients c, Reservations r, Passagers p "
                + " INNER JOIN r.clients numClient "
                + " INNER JOIN r.passagers passager "
                + " WHERE r.vols = 714 AND r.dateDepart >= :minDate ").setTimestamp("minDate", minDate.getTime()).list();
        Iterator it = list.iterator();

        displayLine();
        System.out.println("Liste des passagers du vol 714 au cours des 6 derniers mois :");
        displayStar();
        
        while (it.hasNext()) {
            
            Object[] tuple = (Object[]) it.next();

//            System.out.println(Arrays.toString(tuple));
            //Clients client = (Clients) tuple[0];
            Passagers passager = (Passagers) tuple[3];
            //Reservations reservation = (Reservations) tuple[1];
            
            System.out.println(passager.getNom() + passager.getPrenom());
            displayStar();
        }
        displayLine();
    }
    
    
    //Exercice 2
    public static void displaySiege7Day(){
        Calendar minDate = Calendar.getInstance();
        minDate.add(Calendar.DATE, -7);
        
        
        
    }

    public static void menu() {
        int choix = 1;
        System.out.println("TP HIBERNATE 3");
        while (choix != 0) {
            System.out.println("**** MENU ****");
            displayLine();
            System.out.println("1) Afficher les Passagers du vol 714 au cours des 6 derniers mois [EXO 1] ");
            System.out.println("2) Afficher le nombre de sièges en moyenne réservés 7 jours avant du vol 714 [EXO 2] ");
            System.out.println("0) Quitter le programme ");
            displayLine();
            System.out.print("=> ");
            choix = Integer.parseInt(sc.nextLine());

            switch (choix) {
                case 1:
                    displayPassenger714Flight6Mounth();
                    break;
                case 2:
                    displaySiege7Day();
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
