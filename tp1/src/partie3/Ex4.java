package partie3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ex4 {

    public static void main(String[] args) {
        try {

            //Partie connexion BDD
            Class.forName("com.mysql.jdbc.Driver");

            String URL = "jdbc:mysql://localhost:3306/tp_bdd_isidis?autoReconnect=true&useSSL=false";
            String USER = "root";
            String PASS = "";
            Connection connect = DriverManager.getConnection(URL, USER, PASS);
            Statement st = connect.createStatement();

            int nbColonnes;

            //Saisie clavier 
            Scanner sc = new Scanner(System.in);
            System.out.println("Saisir votre requête : ");
            String requeteSaisie = sc.nextLine();

            // exécution de la requête
            ResultSet rs = st.executeQuery(requeteSaisie);
            // nombre de colonnes
            nbColonnes = rs.getMetaData().getColumnCount();

            System.out.println("Résultats de la requête :\n\n");
            while (rs.next()) {

                for (int i = 1; i < nbColonnes; i++) {
                    System.out.print(rs.getString(i) + ",");
                }
                System.out.println(rs.getString(nbColonnes));
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
