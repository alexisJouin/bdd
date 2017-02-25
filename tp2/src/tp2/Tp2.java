package tp2;

import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tp2 {

    private Connection connect;

    //Partie connexion BDD
    public Connection Connection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String URL = "jdbc:mysql://localhost:3306/tp_bdd_isidis";
            String USER = "root";
            String PASS = "";
            connect = DriverManager.getConnection(URL, USER, PASS);
            return connect;
        } catch (ClassNotFoundException err) {
            System.out.println("Erreur : " + err);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(Tp2.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    //Affichage des numéros de vols
    public void Question1() {
        try {

            Statement st = Connection().createStatement();
            ResultSet selectNumVol = st.executeQuery("SELECT NumVol FROM vol");
            while (selectNumVol.next()) {
                System.out.println("Numéro de Vol : " + selectNumVol.getString("NumVol"));
            }

        } catch (SQLException err) {
            System.out.println("SQLException: " + err.getMessage());
            System.out.println("SQLState: " + err.getSQLState());
            System.out.println("VendorError: " + err.getErrorCode());
        }
    }

    //
    public void Question2(String ville) {
        try {

            Statement st = Connection().createStatement();
            int duree = 0;
            int depart_possible = 0;
            ResultSet selectDepart = st.executeQuery("SELECT * FROM `vol` WHERE Ville_depart LIKE '" + ville + "'");
            System.out.println("Les différents vols pour un tour du monde au départ de " + ville + " : ");
            System.out.println(" ----------- ");

            while (selectDepart.next()) {
                depart_possible++;
                String NumVol = selectDepart.getString("NumVol");
                String VilleArrivee = selectDepart.getString("Ville_arrivee");
                System.out.println("Num Vol de " + ville + " : " + NumVol
                );

                PreparedStatement PSselectPossibleVolsForTDM = Connection().prepareStatement(""
                        + " SELECT *, (SELECT NumVol FROM vol WHERE Ville_depart LIKE ?) NumVolDEP "
                        + " FROM vol"
                        + " INNER JOIN Escales e ON e.Ville_escale LIKE Ville_arrivee "
                        + " WHERE NumVol LIKE ? ");

                PSselectPossibleVolsForTDM.setString(1, VilleArrivee);
                PSselectPossibleVolsForTDM.setString(2, NumVol);
                

                ResultSet selectPossibleVolsForTDM = PSselectPossibleVolsForTDM.executeQuery();

                while (selectPossibleVolsForTDM.next() && !ville.equals(selectPossibleVolsForTDM.getString("Ville_arrivee"))) {
                    System.out.println(" => Correspondance : " + selectPossibleVolsForTDM.getString("Ville_arrivee"));

                    PreparedStatement PSselectPossibleVolsForTDM2 = Connection().prepareStatement(""
                            + " SELECT *"
                            + " FROM vol"
                            + " INNER JOIN Escales e ON e.Ville_escale LIKE Ville_arrivee "
                            + " WHERE NumVol LIKE ? ");

                    PSselectPossibleVolsForTDM2.setString(1, selectPossibleVolsForTDM.getString("NumVol"));
                    ResultSet selectPossibleVolsForTDM2 = PSselectPossibleVolsForTDM2.executeQuery();

                    while (selectPossibleVolsForTDM2.next()) {
                        String VilleArrivee2 = selectPossibleVolsForTDM2.getString("Ville_arrivee");
                        String NumVol2 = selectPossibleVolsForTDM2.getString("NumVol");
                        System.out.println(" => Correspondance : " + VilleArrivee2 + " -- Vol n° : " + NumVol2);
                        duree = duree + selectPossibleVolsForTDM2.getInt("Duree_escale");
                        System.out.println(" - Durée : " + duree);
                    }

                }
                System.out.println(" ----------- ");
            }
            System.out.println("Nombre de Départ Possible : " + depart_possible);

            connect.close();

        } catch (SQLException err) {
            System.out.println("SQLException: " + err.getMessage());
            System.out.println("SQLState: " + err.getSQLState());
            System.out.println("VendorError: " + err.getErrorCode());
        }

    }

    public static void main(String[] args) {
        Tp2 tp2 = new Tp2();
//        tp2.Question1();
        tp2.Question2("Paris");

    }

}
