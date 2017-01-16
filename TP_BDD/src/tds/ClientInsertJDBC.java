/*
 * Exercice de TDs 
 * Exercie 1, 2 
 */
package tds;

import java.sql.*;

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
        }catch(ClassNotFoundException err){
            System.out.println("Erreur : " + err);
        }

    }
}
