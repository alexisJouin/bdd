package tp_hibernate3;

import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
    
    
    public static void displayPassenger714Flight6Mounth(){
        
        
        
    }
    
    public static void menu() {
        int choix = 1;
        System.out.println("TP HIBERNATE 3");
        while (choix != 0) {
            System.out.println("**** MENU ****");
            displayLine();
            System.out.println("1) Afficher les Passagers du vol 714 au cours des 6 derniers mois [EXO 1] ");
            System.out.println("0) Quitter le programme ");
            displayLine();
            System.out.print("=> ");
            choix = Integer.parseInt(sc.nextLine());

            switch (choix) {
                case 1:
                    
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
