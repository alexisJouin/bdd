package classes;

import java.util.Date;

public class Camionnette extends Vehicule {

	private int nbPassagers;
	
	public Camionnette(String veh_id, String marque, String modele, Date date_achat, Date date_vente, int prix_achat,
			int prix_vendu, int nbPassagers) {
		super(veh_id, marque, modele, date_achat, date_vente, prix_achat, prix_vendu);
		this.nbPassagers = nbPassagers;
	}

	public int getNbPassagers() {
		return nbPassagers;
	}

	public void setNbPassagers(int nbPassagers) {
		this.nbPassagers = nbPassagers;
	}

}
