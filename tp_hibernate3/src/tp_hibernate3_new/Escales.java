package tp_hibernate3_new;
// Generated 28 mars 2017 20:06:31 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Escales generated by hbm2java
 */
public class Escales  implements java.io.Serializable {


     private EscalesId id;
     private Aeroport aeroport;
     private Vols vols;
     private Date tempsVol;
     private Date duree;

    public Escales() {
    }

	
    public Escales(EscalesId id, Aeroport aeroport, Vols vols) {
        this.id = id;
        this.aeroport = aeroport;
        this.vols = vols;
    }
    public Escales(EscalesId id, Aeroport aeroport, Vols vols, Date tempsVol, Date duree) {
       this.id = id;
       this.aeroport = aeroport;
       this.vols = vols;
       this.tempsVol = tempsVol;
       this.duree = duree;
    }
   
    public EscalesId getId() {
        return this.id;
    }
    
    public void setId(EscalesId id) {
        this.id = id;
    }
    public Aeroport getAeroport() {
        return this.aeroport;
    }
    
    public void setAeroport(Aeroport aeroport) {
        this.aeroport = aeroport;
    }
    public Vols getVols() {
        return this.vols;
    }
    
    public void setVols(Vols vols) {
        this.vols = vols;
    }
    public Date getTempsVol() {
        return this.tempsVol;
    }
    
    public void setTempsVol(Date tempsVol) {
        this.tempsVol = tempsVol;
    }
    public Date getDuree() {
        return this.duree;
    }
    
    public void setDuree(Date duree) {
        this.duree = duree;
    }




}


