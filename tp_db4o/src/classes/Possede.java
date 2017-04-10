package classes;

import java.util.Date;

public class Possede {
	
	private int pnum;
	private String veh_id;
	private Date date;
	private int prix;
	
	public Possede(int pnum, String veh_id, Date date, int prix) {
		super();
		this.pnum = pnum;
		this.veh_id = veh_id;
		this.date = date;
		this.prix = prix;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}

	@Override
	public String toString() {
		return "Possede [pnum=" + pnum + ", veh_id=" + veh_id + ", date=" + date + ", prix=" + prix + "]";
	}
	
}
