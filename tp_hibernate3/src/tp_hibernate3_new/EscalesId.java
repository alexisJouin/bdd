package tp_hibernate3_new;
// Generated 28 mars 2017 20:06:31 by Hibernate Tools 4.3.1



/**
 * EscalesId generated by hbm2java
 */
public class EscalesId  implements java.io.Serializable {


     private int numVol;
     private String numAeroport;

    public EscalesId() {
    }

    public EscalesId(int numVol, String numAeroport) {
       this.numVol = numVol;
       this.numAeroport = numAeroport;
    }
   
    public int getNumVol() {
        return this.numVol;
    }
    
    public void setNumVol(int numVol) {
        this.numVol = numVol;
    }
    public String getNumAeroport() {
        return this.numAeroport;
    }
    
    public void setNumAeroport(String numAeroport) {
        this.numAeroport = numAeroport;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof EscalesId) ) return false;
		 EscalesId castOther = ( EscalesId ) other; 
         
		 return (this.getNumVol()==castOther.getNumVol())
 && ( (this.getNumAeroport()==castOther.getNumAeroport()) || ( this.getNumAeroport()!=null && castOther.getNumAeroport()!=null && this.getNumAeroport().equals(castOther.getNumAeroport()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getNumVol();
         result = 37 * result + ( getNumAeroport() == null ? 0 : this.getNumAeroport().hashCode() );
         return result;
   }   


}


