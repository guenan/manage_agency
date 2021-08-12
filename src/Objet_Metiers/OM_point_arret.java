package Objet_Metiers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import Beans.Bus;
import Beans.Point_Arret;

public class OM_point_arret {

	private HttpServletRequest request;
    private String              resultat_point_arret;
    private Map<String, String> erreurs_point_arret         = new HashMap<String, String>();
    
	public OM_point_arret(HttpServletRequest request) {
		super();
		this.request = request;
	}
	
	

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getResultat_bus() {
		return resultat_point_arret;
	}

	public void setResultat_bus(String resultat_point_arret) {
		this.resultat_point_arret = resultat_point_arret;
	}

	public Map<String, String> getErreurs_bus() {
		return erreurs_point_arret;
	}

	public void setErreurs_bus(Map<String, String> erreurs_point_arret) {
		this.erreurs_point_arret = erreurs_point_arret;
	}
	
	 public String validation_code_point_arret(String champ){
	    	String value=request.getParameter(champ);
	    	
	    	return value;
	    }
	 
	 public String validation_emplacement(String champ){
	    	String value=request.getParameter(champ);
	    	
	    	return value;
	    }
    
	 public Point_Arret make_point_arret(){
	    	Point_Arret point_arret=new Point_Arret();
	    	
	   if(!validation_code_point_arret("code_point_arret").isEmpty() && !validation_emplacement("emplacement").isEmpty()) {
	    		
	    	point_arret.setCode_point_arret(validation_code_point_arret("code_point_arret"));
	    	point_arret.setEmplacement(validation_emplacement("emplacement"));
	    	point_arret.setStatut_point_arret("no_archive");	
	    	return point_arret;
	  		}
	    else{
	    			return null;
	    		}
	    	}
             
}
