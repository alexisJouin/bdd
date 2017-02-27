package tp_hibernate;

import java.util.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Test2 {

    public static void main(String[] args) {
        try {
            Session session = MyHibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            Personnes personne = new Personnes();
            personne.setNompersonne("nom6");
            personne.setPrenompersonne("prenom6");
            personne.setCoeffpersonne(new Integer(46));
            personne.setDatenaisspersonne(new Date());
            session.save(personne);
            Groupes groupe = (Groupes) session.load(Groupes.class, new Integer(1));
            Grppers grppres = new Grppers();
            grppres.setPersonnes(personne);
            grppres.setGroupes(groupe);
            session.save(grppres);
            tx.commit();
            MyHibernateUtil.getSessionFactory().close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
