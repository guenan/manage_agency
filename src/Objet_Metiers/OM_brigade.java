package Objet_Metiers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import Beans.Brigade;

public class OM_brigade {

	private HttpServletRequest request;
    private String              resultat_brigade;
    private Map<String, String> erreurs_brigade   = new HashMap<String, String>();
    
    public OM_brigade(HttpServletRequest request) {
		super();
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getResultat_brigade() {
		return resultat_brigade;
	}

	public void setResultat_brigade(String resultat_brigade) {
		this.resultat_brigade = resultat_brigade;
	}

	public Map<String, String> getErreurs_brigade() {
		return erreurs_brigade;
	}

	public void setErreurs_brigade(Map<String, String> erreurs_brigade) {
		this.erreurs_brigade = erreurs_brigade;
	}
	
	  public String validation_code_brigade(String champ){
	    	String value=request.getParameter(champ);
	    	
	    	return value;
	    }
	    
	    public String validation_nom_brigade(String champ){
	    	String value=request.getParameter(champ);
	    	
	    	return value;
	    }
	    
	    public Brigade make_brigade(){
	    	Brigade brigade=new Brigade();
	    	
	    	
	    	if(!validation_code_brigade("code_brigade").isEmpty() && !validation_nom_brigade("nom_brigade").isEmpty()){
	    		
	    		brigade.setCode_brigade(validation_code_brigade("code_brigade"));
	    		brigade.setNom_brigade(validation_nom_brigade("nom_brigade"));
	    		brigade.setStatut_brigade("no_archive");
	    		
	    		return brigade;
	    }
	    else{
			return null;
		}
	    	
	    }
    
}
