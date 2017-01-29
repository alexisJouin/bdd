package partie3;

import java.sql.*;
import java.util.Scanner;

public class Exo1 {

    public Exo1() {
//        String prenom1 = "Alexis";
//        String nom1 = "Jouin";
//        String dateNaiss1 = "1995-06-10";
//        int coeff1 = 10;
//        String prenom2 = "Robert";
//        String nom2 = "Robert";
//        String dateNaiss2 = "1945-06-28";
//        int coeff2 = 185;
        //Saisie clavier 
        Scanner sc = new Scanner(System.in);
        System.out.println("Saisir votre prénom, nom, date de naissance et coef de la 1ère personne : ");
        String prenom1 = sc.nextLine();
        String nom1 = sc.nextLine();
        String dateNaiss1 = sc.nextLine();
        String coeff1 = sc.nextLine();
        System.out.println("Saisir votre prénom, nom, date de naissance et coef de la 2ème personne : ");
        String prenom2 = sc.nextLine();
        String nom2 = sc.nextLine();
        String dateNaiss2 = sc.nextLine();
        String coeff2 = sc.nextLine();

        try {

            //Partie connexion BDD
            Class.forName("com.mysql.jdbc.Driver");

            String URL = "jdbc:mysql://localhost:3306/tp_bdd_isidis";
            String USER = "root";
            String PASS = "";
            Connection connect = DriverManager.getConnection(URL, USER, PASS);
            Statement st = connect.createStatement();

            //Insertion des 2 personnes
            st.executeUpdate("INSERT INTO personnes (idpersonne, nompersonne, prenompersonne, datenaisspersonne, coeffpersonne)"
                    + " VALUES(6, '" + nom1 + "', '" + prenom1 + "', '" + dateNaiss1 + "', " + coeff1 + " ), "
                    + " (7, '" + nom2 + "', '" + prenom2 + "', '" + dateNaiss2 + "', " + coeff2 + " ) "
                    + " ON DUPLICATE KEY UPDATE "
                    + " nompersonne = VALUES(nompersonne),"
                    + " prenompersonne= VALUES(prenompersonne),"
                    + " datenaisspersonne= VALUES(datenaisspersonne),"
                    + " coeffpersonne= VALUES(coeffpersonne)");

            st.executeUpdate("INSERT INTO grppers (idgroupe, idpersonne) VALUES(1,6),(1,7) ON DUPLICATE KEY UPDATE idgroupe = VALUES(idgroupe), idpersonne = VALUES(idpersonne) ");

            //Affichage Test
            ResultSet selectTest = st.executeQuery("SELECT * FROM personnes");
            while (selectTest.next()) {
                System.out.println("ID PERSONNE : " + selectTest.getInt("idpersonne"));
                System.out.println("Nom : " + selectTest.getString("nompersonne"));
            }

        } catch (SQLException err) {
            System.out.println("SQLException: " + err.getMessage());
            System.out.println("SQLState: " + err.getSQLState());
            System.out.println("VendorError: " + err.getErrorCode());
        } catch (ClassNotFoundException err) {
            System.out.println("Erreur : " + err);
        }
    }

}
