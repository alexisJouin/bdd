package tp_hibernate;

import java.util.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class Tp_hibernate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            System.out.println("je suis la");
            Session session = MyHibernateUtil.getSessionFactory().openSession();
            System.out.println("je suis la 2");
            List list = session.createQuery("from Personnes ").list();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Personnes personne = (Personnes) it.next();
                System.out.println(personne.getNompersonne());
            }
            MyHibernateUtil.getSessionFactory().close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

}
