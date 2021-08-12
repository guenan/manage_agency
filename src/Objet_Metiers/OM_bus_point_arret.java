package Objet_Metiers;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import Beans.Bus;
import Beans.Bus_Point_Arret;
import Beans.Passage;
import Beans.Point_Arret;

public class OM_bus_point_arret {

	private HttpServletRequest request;
    private String              resultat_bus_point_arret;
    private Map<String, String> erreurs_bus_point_arret         = new HashMap<String, String>();
    
    public OM_bus_point_arret(HttpServletRequest request) {
		super();
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getResultat_bus_point_arret() {
		return resultat_bus_point_arret;
	}

	public void setResultat_bus_point_arret(String resultat_bus_point_arret) {
		this.resultat_bus_point_arret = resultat_bus_point_arret;
	}

	public Map<String, String> getErreurs_bus_point_arret() {
		return erreurs_bus_point_arret;
	}

	public void setErreurs_bus_point_arret(
			Map<String, String> erreurs_bus_point_arret) {
		this.erreurs_bus_point_arret = erreurs_bus_point_arret;
	}
    
	public String validation_immatriculation(String champ){
    	String value=request.getParameter(champ);
    	
    	return value;
    }
	
	public String validation_code_point_arret(String champ){
    	String value=request.getParameter(champ);
    	
    	return value;
    }
	
	public String validation_heure(String champ){
    	String value=request.getParameter(champ);
    	
    	return value;
    }
	

	public Bus_Point_Arret make_bus_point_arret(){
		Bus_Point_Arret bus_point_arret=new Bus_Point_Arret();
    	
   if(erreurs_bus_point_arret.size() == 0) {
	   Bus bus=new Bus();
	  	 
	   Point_Arret point_arret=new Point_Arret();
	   Date date=new Date();
	   bus.setImmatriculation(validation_immatriculation("immatriculation"));
	   point_arret.setCode_point_arret(validation_code_point_arret("code_point_arret"));
	   
	   bus_point_arret.getListe_bus().add(bus);
	   bus_point_arret.getListe_point_arret().add(point_arret);
	   bus_point_arret.setListe_bus( bus_point_arret.getListe_bus());
	   bus_point_arret.setListe_point_arret(bus_point_arret.getListe_point_arret());
	   bus_point_arret.setHeure_depart(Time.valueOf((validation_heure("heure_depart"))));
	  
	   
	   //date.setHours(Integer.parseInt(validation_heure("heure_depart").split(":")[0]));
	   bus_point_arret.setstatut_point_arret("no_archive");
	   System.out.println(bus_point_arret.getListe_bus().get(0).getImmatriculation());
	   System.out.println(bus_point_arret.getListe_point_arret().get(0).getCode_point_arret());
	   System.out.println(bus_point_arret.getHeure_depart());
	   System.out.println(bus_point_arret.getstatut_bus_point_arret());
    		return bus_point_arret;
  		}
    else{
    			return null;
    		}
    	}
	
}
