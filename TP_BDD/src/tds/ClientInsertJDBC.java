/*
 * Exercice de TDs 
 * Exercie 1, 2 
 */
package tds;

import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author Alexis
 */
public class ClientInsertJDBC {

    public static void main(String args[]) {

        try {

            //Partie connexion BDD
            Class.forName("com.mysql.jdbc.Driver");

            String URL = "jdbc:mysql://localhost:3306/";
            String USER = "root";
            String PASS = "root";
            Connection connect = DriverManager.getConnection(URL, USER, PASS);

            //Connection connect = DriverManager.getConnection("jdbc:derby://localhost:1527/?user=ajouin&password=10061995");
            Statement st = connect.createStatement();

            int createTableProduit = st.executeUpdate("CREATE TABLE IF NOT EXISTS produit ("
                    + " reference INTEGER PRIMARY KEY,"
                    + " designation VARCHAR(50), "
                    + " quantite INTEGER,"
                    + " tva FLOAT,"
                    + " prix_ht FLOAT)"
            );
            System.out.println("SQL  : " + createTableProduit);
            st.executeUpdate("INSERT INTO produit VALUES"
                    + " (0001, 'PC MM MMX', 5, 19.6, 6500) ON DUPLICATE KEY IGNORE,"
                    + " (0002, 'Imprimante HP jet', 20, 19.6, 3500) ON DUPLICATE KEY IGNORE ");

            ResultSet selectEx2 = st.executeQuery("SELECT reference, designation, quantite, tva, prix_ht FROM produit");

            //Saisir un nom de table pour resortir son schéma
            Scanner sc = new Scanner(System.in);
            System.out.println("Donner le nom de la table : ");
            String tableSaisie = sc.nextLine();
            System.out.println("Table saisie : " + tableSaisie);

            ResultSet selectTable = st.executeQuery("SELECT * FROM " + tableSaisie);
            ResultSetMetaData rsmd = selectTable.getMetaData();
            int nbColonnes = rsmd.getColumnCount();
            //On get le schéma de la table
            for (int i = 1; i <= nbColonnes; i++) {
                String typeCol = rsmd.getColumnTypeName(i);
                System.out.println("TYPE colonne : " + typeCol);
                String nomCol = rsmd.getColumnName(i);
                System.out.println("NOM colonne : " + nomCol);
            }

            //Afficher tout le contenu de la table
            while (selectEx2.next()) {
                System.out.println("Référence : " + selectEx2.getInt("reference"));
                System.out.println("Désignation : " + selectEx2.getString("designation"));
                System.out.println("Quantité : " + selectEx2.getInt("quantite"));
                System.out.println("TVA : " + selectEx2.getFloat("tva"));
                System.out.println("Prix HT : " + selectEx2.getFloat("prix_ht"));
            }

            //Question 3
            System.out.println("Donner une valeur  : ");
            String valeurSaisie = sc.nextLine();
            int valeurSaisieInt = Integer.parseInt(valeurSaisie);
            System.out.println("Valeur saisie : " + valeurSaisie);
            //ResultSet selectWithCondition = st.executeQuery("SELECT reference, designation, quantite, tva, prix_ht FROM produit WHERE prix_ht > " + valeurSaisieInt);
            PreparedStatement ps = connect.prepareStatement("SELECT reference, designation, quantite, tva, prix_ht FROM produit WHERE prix_ht > ?");
            ps.setInt(valeurSaisieInt, 1);
            ResultSet rs = ps.executeQuery();
            System.out.println(rs);

            //Question 4
            System.out.println("Rentrer la requête SQL  : ");
            String requeteSaisie = sc.nextLine();
            System.out.println("Requête saisie : " + requeteSaisie);
            ResultSet selectRequeteSaisie = st.executeQuery(requeteSaisie);

            st.close();
            connect.close();
        } catch (SQLException err) {
            System.out.println("SQLException: " + err.getMessage());
            System.out.println("SQLState: " + err.getSQLState());
            System.out.println("VendorError: " + err.getErrorCode());
        } catch (ClassNotFoundException err) {
            System.out.println("Erreur : " + err);
        }

    }
}
