
import java.sql.*;
import java.util.Scanner;

public class Exo1 {

    public static void main(String[] args) {
        String prenom1 = "Alexis";
        String nom1 = "Jouin";
        String dateNaiss1 = "1995-06-10";
        int coeff1 = 10;
        String prenom2 = "Robert";
        String nom2 = "Robert";
        String dateNaiss2 = "1945-06-28";
        int coeff2 = 185;

        try {

            //Partie connexion BDD
            Class.forName("com.mysql.jdbc.Driver");

            String URL = "jdbc:mysql://localhost:3306/mysql?autoReconnect=true&useSSL=false";
            String USER = "root";
            String PASS = "root";
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
            

            st.executeUpdate("INSERT INTO grppers (idgroupe, idpersonne) VALUES(1,6),(1,7) ");

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
