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
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/test?user=minty&password=greatsqldb");
            Statement st = connect.createStatement();

            int createTableProduit = st.executeUpdate("CREATE TABLE produit("
                    + " reference primary key,"
                    + " designation VARCHAR(50), "
                    + " quantite number"
                    + " tva float,"
                    + " prix_ht float)"
            );
            System.out.println("SQL  : " + createTableProduit);
            st.executeUpdate("INSERT INTO produit VALUES (0001, 'PC MM MMX', 5, 19.6, 6500)");

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
                System.out.println("TYPE colonne : " + nomCol);
            }

            //Afficher tout le contenu de la table
            while (selectEx2.next()) {
                System.out.println("Référence : " + selectEx2.getInt("reference"));
                System.out.println("Désignation : " + selectEx2.getString("designation"));
                System.out.println("Quantité : " + selectEx2.getInt("quantite"));
                System.out.println("TVA : " + selectEx2.getFloat("tva"));
                System.out.println("Prix HT : " + selectEx2.getFloat("prix_ht"));
            }

            st.close();
            connect.close();
        } catch (SQLException err) {
            System.out.println("Erreur SQL : " + err);
        } catch (ClassNotFoundException err) {
            System.out.println("Erreur : " + err);
        }

    }
}
