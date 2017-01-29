/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partie3;

import java.util.Scanner;

/**
 *
 * @author Alexis
 */
public class Generale {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1) Insérer une nouvelle personne ? ");
        System.out.println("2) Afficher les personnes ? ");
        System.out.println("3) Afficher les personnes d'un certain groupe ? ");
        System.out.println("4) Exécution d'une requête SQL personnalisée ");

        int choix = Integer.parseInt(sc.nextLine());
        switch (choix) {
            case 1:
                new Exo1();

                break;
            case 2:
                new Exo2();
                break;

            case 3:
                new Exo3();
                break;
            case 4:
                new Ex4();
                break;
        }

    }
}
