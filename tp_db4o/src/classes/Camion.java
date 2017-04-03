package classes;

import java.util.Date;

public class Camion extends Vehicule {

	private int capaciteChargement;
	
	public Camion(String veh_id, String marque, String modele, Date date_achat, Date date_vente, int prix_achat,
			int prix_vendu, int capaciteCharement) {
		super(veh_id, marque, modele, date_achat, date_vente, prix_achat, prix_vendu);
		this.capaciteChargement = capaciteCharement;
	}

	public int getCapaciteChargement() {
		return capaciteChargement;
	}

	public void setCapaciteChargement(int capaciteChargement) {
		this.capaciteChargement = capaciteChargement;
	}

}
