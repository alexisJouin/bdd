package tp_db4o;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

import classes.Vehicule;

public class Main {
	
	private static ObjectContainer db;

	public static void test(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			Date dateAchat = sdf.parse("21/01/2017");
			Date dateVente = sdf.parse("31/03/2017");
			
			Vehicule vehicule1 = new Vehicule("PG-JLM-2017","Opel", "CORSA", dateAchat, dateVente, 8999, 8599);
			db.store(vehicule1);
			System.out.println("Stored " + vehicule1);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "db.bd4o");
		try {
			
			test();
			
		}finally {
			db.close();
		}
	
	}

}


