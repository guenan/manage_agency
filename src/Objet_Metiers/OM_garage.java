package Objet_Metiers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import Beans.Garage;
import Beans.News;

public class OM_garage {

	  
    private HttpServletRequest request;
    private String              resultat_garage;
    private Map<String, String> erreurs_garage         = new HashMap<String, String>();
 
    
    public OM_garage(HttpServletRequest request) {
        this.request = request;
    }


	public HttpServletRequest getRequest() {
		return request;
	}


	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}


	public String getResultat_garage() {
		return resultat_garage;
	}


	public void setResultat_garage(String resultat_garage) {
		this.resultat_garage = resultat_garage;
	}


	public Map<String, String> getErreurs_garage() {
		return erreurs_garage;
	}


	public void setErreurs_garage(Map<String, String> erreurs_garage) {
		this.erreurs_garage = erreurs_garage;
	}
    
    
	 public String validation_code_garage(String code_garage){
	        String value=null;   
	        value=request.getParameter(code_garage);
	        return value;
	    }
	 
	 public String validation_nom_garage(String nom_garage){
	        String value=null;   
	        value=request.getParameter(nom_garage);
	        return value;
	    }
	 
	 public Garage make_garage_to_add(){
		 Garage garage=new Garage();
	        if(!validation_code_garage("code_garage").isEmpty() && !validation_nom_garage("nom_garage").isEmpty()){
	        	garage.setCode_garage(validation_code_garage("code_garage"));
	        	garage.setNom_garage(validation_nom_garage("nom_garage"));
	        	garage.setStatut_garage("no_archive");   
	        return garage;
	               
	        }else{
	            System.out.println("mauvaise fabrication de l'objet garage to add");
	            return null;
	        }
     }
}