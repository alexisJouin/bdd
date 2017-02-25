/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexis
 */
public class Exo2 {

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

    public void creerEmploye() {
        Scanner sc = new Scanner(System.in);
        System.out.println("-- Création de l'employé -- ");
        System.out.println("Entrez un nom pour le nouvel employé :  ");
        String nom = sc.nextLine();
        System.out.println("Entrez un salaire pour le nouvel employé :  ");
        int salaire = Integer.parseInt(sc.nextLine());
        System.out.println("Entrez le numéro de département auquel l'affecter :  ");
        this.displayListDepartements();
        int numDepartement = Integer.parseInt(sc.nextLine());
        try {
            Statement stIns = Connection().createStatement();
            Statement stInsIntoProjet = Connection().createStatement();

            stIns.executeUpdate("INSERT INTO Employe (nom, salaire, date_embauche, id_departement) "
                    + " VALUES('" + nom + "', '" + salaire + "', CURDATE(), '" + numDepartement + "') "
                    + " ON DUPLICATE KEY UPDATE "
                    + " nom = VALUES(nom),"
                    + " salaire = VALUES(nom),"
                    + " id_departement = VALUES(id_departement),"
                    + " date_embauche = VALUES(date_embauche)");

            //Ajout dans le projet Pokémon que j'ai créé
            Statement stEmp = Connection().createStatement();
            ResultSet listEmployes = stEmp.executeQuery("SELECT * FROM employe e WHERE nom LIKE '" + nom + "'");
            int idEmploye;
            while (listEmployes.next()) {
                idEmploye = listEmployes.getInt("id");
                
                stInsIntoProjet.executeUpdate("INSERT INTO participation (matr, codeP) "
                    + " VALUES('" + idEmploye + "', 1) "
                    + " ON DUPLICATE KEY UPDATE "
                    + " matr = VALUES(matr),"
                    + " codeP = VALUES(codeP)");
            }

            

            System.out.println("L'employé " + nom + " a bien été créé.");

        } catch (SQLException ex) {
            Logger.getLogger(Exo2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void displayListEmployes() {
        try {
            Statement stEmp = Connection().createStatement();

            ResultSet listEmployes = stEmp.executeQuery("SELECT * FROM employe e INNER JOIN departement d ON d.id = e.id_departement");

            while (listEmployes.next()) {
                System.out.println("MATRICULE EMPLOYE : " + listEmployes.getInt("id"));
                System.out.println("Nom : " + listEmployes.getString("nom"));
                System.out.println("Salaire : " + listEmployes.getString("salaire") + "€");
                System.out.println("date d'embauche : " + listEmployes.getString("date_embauche"));
                System.out.println("Département : " + listEmployes.getString("d.nom"));
                System.out.println("----------------");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Exo2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void displayListDepartements() {
        try {
            Statement stEmp = Connection().createStatement();

            ResultSet listDpt = stEmp.executeQuery("SELECT * FROM departement");

            System.out.println("Liste des départements :");
            while (listDpt.next()) {
                System.out.println(listDpt.getInt("id") + ") " + listDpt.getString("nom"));
            }
            System.out.println("------------------");

        } catch (SQLException ex) {
            Logger.getLogger(Exo2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifierSalaire() {
        Scanner sc = new Scanner(System.in);
        System.out.println("-- Salaire des l'employés -- ");

        System.out.println("Entrez le matricule de l'employé :  ");
        this.displayListEmployes();
        System.out.println("Entrez le matricule de l'employé :  ");

        int idEmploye = Integer.parseInt(sc.nextLine());
        System.out.println("Entrez un nouveau salaire pour l'employé " + idEmploye + " :");
        int salaire = Integer.parseInt(sc.nextLine());
        try {
            Statement stIns = Connection().createStatement();

            stIns.executeUpdate("UPDATE employe SET salaire = " + salaire + " WHERE id = " + idEmploye);

            System.out.println("Le salaire de " + idEmploye + " a bien mis à jour.");

        } catch (SQLException ex) {
            Logger.getLogger(Exo2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        Exo2 exo2 = new Exo2();

        Scanner sc = new Scanner(System.in);
        int choix = 0;
        while (choix != 666) {
            System.out.println("1) Insérer un nouvel employé ? ");
            System.out.println("2) Afficher tous les employés ? ");
            System.out.println("3) Afficher les départements ? ");
            System.out.println("4) Modifier salaire d'un employé ");
            System.out.println("Taper 666 pour quitter le programme");

            choix = Integer.parseInt(sc.nextLine());
            switch (choix) {
                case 1:
                    exo2.creerEmploye();
                    break;
                case 2:
                    exo2.displayListEmployes();
                    break;

                case 3:
                    exo2.displayListDepartements();
                    break;
                case 4:
                    exo2.modifierSalaire();
                    break;
            }
        }

        System.out.println("FIN DU PROGRAMME");

    }
}
