package tp_hibernate3_new;
// Generated 28 mars 2017 20:06:31 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Aeroport generated by hbm2java
 */
public class Aeroport  implements java.io.Serializable {


     private String code;
     private Pays pays;
     private String nom;
     private Set volsesForAeroportDepart = new HashSet(0);
     private Set volsesForAeroportArrivee = new HashSet(0);
     private Set escaleses = new HashSet(0);

    public Aeroport() {
    }

	
    public Aeroport(String code) {
        this.code = code;
    }
    public Aeroport(String code, Pays pays, String nom, Set volsesForAeroportDepart, Set volsesForAeroportArrivee, Set escaleses) {
       this.code = code;
       this.pays = pays;
       this.nom = nom;
       this.volsesForAeroportDepart = volsesForAeroportDepart;
       this.volsesForAeroportArrivee = volsesForAeroportArrivee;
       this.escaleses = escaleses;
    }
   
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    public Pays getPays() {
        return this.pays;
    }
    
    public void setPays(Pays pays) {
        this.pays = pays;
    }
    public String getNom() {
        return this.nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    public Set getVolsesForAeroportDepart() {
        return this.volsesForAeroportDepart;
    }
    
    public void setVolsesForAeroportDepart(Set volsesForAeroportDepart) {
        this.volsesForAeroportDepart = volsesForAeroportDepart;
    }
    public Set getVolsesForAeroportArrivee() {
        return this.volsesForAeroportArrivee;
    }
    
    public void setVolsesForAeroportArrivee(Set volsesForAeroportArrivee) {
        this.volsesForAeroportArrivee = volsesForAeroportArrivee;
    }
    public Set getEscaleses() {
        return this.escaleses;
    }
    
    public void setEscaleses(Set escaleses) {
        this.escaleses = escaleses;
    }




}


