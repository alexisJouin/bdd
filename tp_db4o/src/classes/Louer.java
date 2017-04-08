package classes;

import java.util.Date;

public class Louer {

	private int pnum;
	private String veh_id;
	private Date date_prise;
	private Date date_retour;
	private int prix_location;
	
	public Louer(int pnum, String veh_id, Date date_prise, Date date_retour, int prix_location) {
		super();
		this.pnum = pnum;
		this.veh_id = veh_id;
		this.date_prise = date_prise;
		this.date_retour = date_retour;
		this.prix_location = prix_location;
	}
	
	
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public String getVeh_id() {
		return veh_id;
	}
	public void setVeh_id(String veh_id) {
		this.veh_id = veh_id;
	}
	public Date getDate_prise() {
		return date_prise;
	}
	public void setDate_prise(Date date_prise) {
		this.date_prise = date_prise;
	}
	public Date getDate_retour() {
		return date_retour;
	}
	public void setDate_retour(Date date_retour) {
		this.date_retour = date_retour;
	}
	public int getPrix_location() {
		return prix_location;
	}
	public void setPrix_location(int prix_location) {
		this.prix_location = prix_location;
	}
	
	
}
