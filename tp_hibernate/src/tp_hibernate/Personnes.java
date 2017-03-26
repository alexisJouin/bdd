package tp_hibernate;
// Generated 27 f�vr. 2017 10:14:54 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Personnes generated by hbm2java
 */
public class Personnes  implements java.io.Serializable {


     private Integer idpersonne;
     private String nompersonne;
     private String prenompersonne;
     private Date datenaisspersonne;
     private Integer coeffpersonne;
     private Set grpperses = new HashSet(0);

    public Personnes() {
    }

    public Personnes(String nompersonne, String prenompersonne, Date datenaisspersonne, Integer coeffpersonne, Set grpperses) {
       this.nompersonne = nompersonne;
       this.prenompersonne = prenompersonne;
       this.datenaisspersonne = datenaisspersonne;
       this.coeffpersonne = coeffpersonne;
       this.grpperses = grpperses;
    }
   
    public Integer getIdpersonne() {
        return this.idpersonne;
    }
    
    public void setIdpersonne(Integer idpersonne) {
        this.idpersonne = idpersonne;
    }
    public String getNompersonne() {
        return this.nompersonne;
    }
    
    public void setNompersonne(String nompersonne) {
        this.nompersonne = nompersonne;
    }
    public String getPrenompersonne() {
        return this.prenompersonne;
    }
    
    public void setPrenompersonne(String prenompersonne) {
        this.prenompersonne = prenompersonne;
    }
    public Date getDatenaisspersonne() {
        return this.datenaisspersonne;
    }
    
    public void setDatenaisspersonne(Date datenaisspersonne) {
        this.datenaisspersonne = datenaisspersonne;
    }
    public Integer getCoeffpersonne() {
        return this.coeffpersonne;
    }
    
    public void setCoeffpersonne(Integer coeffpersonne) {
        this.coeffpersonne = coeffpersonne;
    }
    public Set getGrpperses() {
        return this.grpperses;
    }
    
    public void setGrpperses(Set grpperses) {
        this.grpperses = grpperses;
    }




}

