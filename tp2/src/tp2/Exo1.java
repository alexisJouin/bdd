package tp2;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Exo1 {

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
            Logger.getLogger(Exo1.class.getName()).log(Level.SEVERE, null, ex);
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
        String VilleArrivee;
        String VilleDepart;
        int duree = 0;
        int depart_possible = 0;

        try {
            Statement st = Connection().createStatement();
            ResultSet selectDepart = st.executeQuery("SELECT * FROM `vol` WHERE Ville_depart LIKE '" + ville + "'");
            System.out.println("Les différents vols pour un tour du monde au départ de " + ville + " : ");
            System.out.println(" ----------- ");

            while (selectDepart.next()) {
                depart_possible++;
                String NumVol = selectDepart.getString("NumVol");
                VilleDepart = selectDepart.getString("Ville_depart");
                VilleArrivee = selectDepart.getString("Ville_arrivee");
                System.out.println("Vol n° " + depart_possible);
                System.out.println("Num Vol de " + ville + " : " + NumVol + " " + VilleDepart + " à " + VilleArrivee);

                String VilleArriveeCurrent;
                String VilleDepartCurrent;

                //Selection des destinations possibles
                PreparedStatement ps = Connection().prepareStatement(
                        " SELECT * FROM vol INNER JOIN Escales e ON e.Ville_escale LIKE Ville_arrivee WHERE NumVol LIKE ?");
                ps.setString(1, NumVol);
                ResultSet arriveePossible = ps.executeQuery();

                while (arriveePossible.next()) {
                    String NumVolCorr = arriveePossible.getString("NumVol");
                    VilleDepartCurrent = arriveePossible.getString("Ville_depart");
                    VilleArriveeCurrent = arriveePossible.getString("Ville_arrivee");

//                    while (!VilleArriveeCurrent.equals(ville)) {
//                    while (!VilleArriveeCurrent !=) {
                    System.out.println("Correspondance -> vol n°" + NumVolCorr + " de " + VilleDepartCurrent + " à " + VilleArriveeCurrent);

                    PreparedStatement PSselectPossibleVolsForTDM2 = Connection().prepareStatement(""
                            + " SELECT *"
                            + " FROM vol"
                            + " INNER JOIN Escales e ON e.Ville_escale LIKE Ville_arrivee "
                            + " WHERE Ville_depart LIKE ? ");

                    PSselectPossibleVolsForTDM2.setString(1, VilleArriveeCurrent);
                    ResultSet selectPossibleVolsForTDM2 = PSselectPossibleVolsForTDM2.executeQuery();

                    while (selectPossibleVolsForTDM2.next()) {

                        VilleDepartCurrent = selectPossibleVolsForTDM2.getString("Ville_arrivee");
//                            VilleArriveeCurrent = selectPossibleVolsForTDM2.getString("Ville_depart");
                        String NumVol2 = selectPossibleVolsForTDM2.getString("NumVol");
                        System.out.println(" => Correspondance : " + VilleDepartCurrent + " -- Vol n° : " + NumVol2);
                        duree = duree + selectPossibleVolsForTDM2.getInt("Duree_escale");
                        System.out.println(" - Durée : " + duree);
                    }
//                    }
                }

                System.out.println(" Durée total : " + duree);
                System.out.println(" ----------- ");
            }
            System.out.println("Nombre de Départ Possible : " + depart_possible + ", pour une durée moyenne de " + duree / depart_possible + " heure(s).");

            connect.close();

        } catch (SQLException err) {
            System.out.println("SQLException: " + err.getMessage());
            System.out.println("SQLState: " + err.getSQLState());
            System.out.println("VendorError: " + err.getErrorCode());
        }

    }

    public void Question3() {
        try {
            String numVol;
            String villeArrivee;
            String villeDepart;
            Timestamp heureDepart;
            Timestamp heureArrivee;
            Timestamp duree;
            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

            Statement st = Connection().createStatement();
            ResultSet selectVols = st.executeQuery("SELECT * FROM `vol`");

            while (selectVols.next()) {
                Statement stIns = Connection().createStatement();
                numVol = selectVols.getString("NumVol");
                villeArrivee = selectVols.getString("Ville_Arrivee");
                villeDepart = selectVols.getString("Ville_Depart");
                heureArrivee = selectVols.getTimestamp("Heure_arrive");
                heureDepart = selectVols.getTimestamp("Heure_depart");

                CharSequence seq = java.nio.CharBuffer.wrap("R");
                
                if (!numVol.contains("R")) {
                    cal1.setTimeInMillis(heureDepart.getTime());
                    cal1.add(Calendar.HOUR, 2);
                    Timestamp newHeureDepart = new Timestamp(cal1.getTime().getTime());

                    cal2.setTimeInMillis(heureArrivee.getTime());
                    cal2.add(Calendar.HOUR, 2);
                    Timestamp newHeureArrivee = new Timestamp(cal2.getTime().getTime());

                    String formattedDateD = sdf.format(newHeureDepart);
                    String formattedDateA = sdf.format(newHeureArrivee);

                    System.out.println("Vol n° " + numVol + " Ville départ : " + villeDepart + " Ville arrivée : " + villeArrivee);
                    System.out.println("New Heure " + formattedDateD + "   -> " + formattedDateA);

                    stIns.executeUpdate("INSERT INTO vol (NumVol, Heure_depart, Heure_arrive, Ville_depart, Ville_arrivee) "
                            + " VALUES('" + numVol + "R', '" + formattedDateD + "', '" + formattedDateA + "', '" + villeArrivee + "', '" + villeDepart + "' ) "
                            + " ON DUPLICATE KEY UPDATE "
                            + " Heure_depart = VALUES(Heure_depart),"
                            + " Heure_arrive= VALUES(Heure_arrive),"
                            + " Ville_depart= VALUES(Ville_depart),"
                            + " Ville_arrivee= VALUES(Ville_arrivee)");
                }
            }
//              
            connect.close();

        } catch (SQLException err) {
            System.out.println("SQLException: " + err.getMessage());
            System.out.println("SQLState: " + err.getSQLState());
            System.out.println("VendorError: " + err.getErrorCode());
        }
    }

    public static void main(String[] args) {
        Exo1 exo1 = new Exo1();
        exo1.Question1();
        exo1.Question2("Paris");
        exo1.Question3();
    }

}
