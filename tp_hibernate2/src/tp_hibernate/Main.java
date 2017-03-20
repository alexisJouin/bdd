package tp_hibernate;

import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import tp_hibernate_new.Entreprise;

public class Main {

    public static void main(String[] args) {

        try {

            Session session = NewHibernateUtil.getSessionFactory().openSession();

            List list = session.createQuery("from Entreprise ").list();
            Iterator it = list.iterator();

            displayEntreprises(it);

            NewHibernateUtil.getSessionFactory().close();
        } catch (HibernateException e) {
            System.out.println("Erreur : ");
            e.printStackTrace();
        }
    }

    //Liste toutes les entrprises
    public static void displayEntreprises(Iterator it) {
        displayLine();
        System.out.println("Liste des entreprises :");
        displayStar();
        while (it.hasNext()) {
            Entreprise entreprise = (Entreprise) it.next();
            System.out.println(entreprise.getIdEntreprise() + ") " + entreprise.getNomEnt());
        }
        displayStar();
        displayLine();
    }
    
    public static void ajouterEntreprise(){
        
    }

    public static void displayLine() {
        System.out.println("----------------------------------");
    }

    public static void displayStar() {
        System.out.println("*****");
    }
}
