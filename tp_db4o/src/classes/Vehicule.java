package classes;

import java.util.Date;

public class Vehicule {

	private String veh_id;
	private String marque;
	private String modele;
	private Date date_achat;
	private Date date_vente;
	private int prix_achat;
	private int prix_vendu;
	
	
	public Vehicule(String veh_id, String marque, String modele, Date date_achat, Date date_vente,
			int prix_achat, int prix_vendu) {
		super();
		this.veh_id = veh_id;
		this.marque = marque;
		this.modele = modele;
		this.date_achat = date_achat;
		this.date_vente = date_vente;
		this.prix_achat = prix_achat;
		this.prix_vendu = prix_vendu;
	}

	public String getVeh_id() {
		return veh_id;
	}
	public void setVeh_id(String veh_id) {
		this.veh_id = veh_id;
	}

	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public String getModele() {
		return modele;
	}
	public void setModele(String modele) {
		this.modele = modele;
	}
	public Date getDate_achat() {
		return date_achat;
	}
	public void setDate_achat(Date date_achat) {
		this.date_achat = date_achat;
	}
	public Date getDate_vente() {
		return date_vente;
	}
	public void setDate_vente(Date date_vente) {
		this.date_vente = date_vente;
	}
	public int getPrix_achat() {
		return prix_achat;
	}
	public void setPrix_achat(int prix_achat) {
		this.prix_achat = prix_achat;
	}
	public int getPrix_vendu() {
		return prix_vendu;
	}
	public void setPrix_vendu(int prix_vendu) {
		this.prix_vendu = prix_vendu;
	}
	
	@Override
	public String toString() {
		return "Vehicule [veh_id=" + veh_id + ", marque=" + marque + ", modele=" + modele
				+ ", date_achat=" + date_achat + ", date_vente=" + date_vente + ", prix_achat=" + prix_achat
				+ ", prix_vendu=" + prix_vendu + "]";
	}
	
}
