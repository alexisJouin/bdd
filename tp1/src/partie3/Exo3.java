package partie3;

import java.sql.*;
import java.util.Scanner;

public class Exo3 {

    public static void main(String[] args) {
        try {

            //Partie connexion BDD
            Class.forName("com.mysql.jdbc.Driver");

            String URL = "jdbc:mysql://localhost:3306/tp_bdd_isidis?autoReconnect=true&useSSL=false";
            String USER = "root";
            String PASS = "";
            Connection connect = DriverManager.getConnection(URL, USER, PASS);
            Statement st = connect.createStatement();

            //Saisie clavier 
            Scanner sc = new Scanner(System.in);
            System.out.println("Donner le nom d'un groupe : ");
            String groupeSaisie = sc.nextLine();
            System.out.println("Groupe saisie : " + groupeSaisie);

            PreparedStatement selectTable = connect.prepareStatement("SELECT DISTINCT p.nompersonne nom "
                    + " FROM groupes g"
                    + " INNER JOIN grppers gp ON gp.idgroupe = g.idgroupe"
                    + " INNER JOIN personnes p ON gp.idpersonne = p.idpersonne"
                    + " WHERE g.nomgroupe LIKE ?"
            );

            selectTable.setString(1, groupeSaisie);
            ResultSet rs = selectTable.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("nom"));
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
