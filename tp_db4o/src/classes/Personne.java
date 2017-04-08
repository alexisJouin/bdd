package classes;

public class Personne {
	
	private int pnum;
	private String nom;
	private String prenom;
	
	public Personne(int pnum, String nom, String prenom) {
		super();
		this.pnum = pnum;
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Override
	public String toString() {
		return "Personne [pnum=" + pnum + ", nom=" + nom + ", prenom=" + prenom + "]";
	}
}
