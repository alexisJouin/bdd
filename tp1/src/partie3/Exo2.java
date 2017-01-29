package partie3;

import java.sql.*;

public class Exo2 {

    public Exo2() {
        try {

            //Partie connexion BDD
            Class.forName("com.mysql.jdbc.Driver");

            String URL = "jdbc:mysql://localhost:3306/tp_bdd_isidis?autoReconnect=true&useSSL=false";
            String USER = "root";
            String PASS = "";
            Connection connect = DriverManager.getConnection(URL, USER, PASS);
            Statement st = connect.createStatement();

            //Affichage des personnes
            ResultSet selectTest = st.executeQuery("SELECT * FROM personnes");
            while (selectTest.next()) {
                System.out.println("ID PERSONNE : " + selectTest.getInt("idpersonne"));
                System.out.println("Nom : " + selectTest.getString("nompersonne"));
                System.out.println("Prénom : " + selectTest.getString("prenompersonne"));
                System.out.println("Date Naissance : " + selectTest.getString("datenaisspersonne"));
                System.out.println("Coeff  : " + selectTest.getString("coeffpersonne"));
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
