package Objet_Metiers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import Beans.Bus;
import Beans.Ligne;
import Beans.Point_Arret;

public class OM_ligne {

	private HttpServletRequest request;
    private String              resultat_ligne;
    private Map<String, String> erreurs_ligne         = new HashMap<String, String>();
    
    
    
	public OM_ligne(HttpServletRequest request) {
		super();
		this.request = request;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String getResultat_ligne() {
		return resultat_ligne;
	}
	public void setResultat_ligne(String resultat_ligne) {
		this.resultat_ligne = resultat_ligne;
	}
	public Map<String, String> getErreurs_ligne() {
		return erreurs_ligne;
	}
	public void setErreurs_ligne(Map<String, String> erreurs_ligne) {
		this.erreurs_ligne = erreurs_ligne;
	}
    
	 public String validation_code_ligne(String champ){
	    	String value=request.getParameter(champ);
	    	
	    	return value;
	    }
	 
	 public String validation_point_depart(String champ){
	    	String value=request.getParameter(champ);
	    	
	    	return value;
	    }
	 
	 public String validation_point_arret(String champ){
	    	String value=request.getParameter(champ);
	    	
	    	return value;
	    }
	 
	 public Ligne make_ligne(){
		 Ligne ligne=new Ligne();
	    	
	    if(!validation_code_ligne("code_ligne").isEmpty() && !validation_point_depart("point_depart").isEmpty()&& !validation_point_arret("point_arret").isEmpty()) {
	    		ligne.setCode_ligne(validation_code_ligne("code_ligne"));
	    		ligne.getPoint_depart().setCode_point_arret(validation_point_depart("point_depart"));
	    		ligne.getPoint_arret().setCode_point_arret(validation_point_arret("point_arret"));
	    		ligne.setStatut_ligne("no_archive");
	    		return ligne;
	    		}
	    else{
	    			return null;
	    		}
	    	}
    
}
