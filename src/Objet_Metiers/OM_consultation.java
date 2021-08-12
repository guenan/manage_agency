package Objet_Metiers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import Beans.Consultation;
import Beans.Garage;

public class OM_consultation {

	private HttpServletRequest request;
    private String              resultat_consultation;
    private Map<String, String> erreurs_consultation         = new HashMap<String, String>();
    
	public OM_consultation(HttpServletRequest request) {
		super();
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getResultat_gonsultation() {
		return resultat_consultation;
	}

	public void setResultat_gonsultation(String resultat_gonsultation) {
		this.resultat_consultation = resultat_gonsultation;
	}

	public Map<String, String> getErreurs_consultation() {
		return erreurs_consultation;
	}

	public void setErreurs_consultation(Map<String, String> erreurs_consultation) {
		this.erreurs_consultation = erreurs_consultation;
	}
	
	public String validation_code_garage(String code_garage){
        String value=null;   
        value=request.getParameter(code_garage);
        return value;
    }
	
	 public String validation_immatriculation(String champ){
	    	String value=request.getParameter(champ);
	    	
	    	return value;
	    }
	 
	 public String validation_etat_bus(String champ){
	    	String value=request.getParameter(champ);
	    	
	    	return value;
	    }

	 public String validation_date_consultation(String champ){
	    	String value=request.getParameter(champ);
	    	
	    	return value;
	    }
	 
	 public String validation_description_consultation(String champ){
	    	String value=request.getParameter(champ);
	    	
	    	return value;
	    }
	 
	 public Consultation make_consultation_to_add(){
		 Consultation consultation=new Consultation();
	        if(erreurs_consultation.size() == 0){
	        	consultation.setEtat_bus(validation_etat_bus("etat_bus"));
	        	consultation.getGarage().setCode_garage(validation_code_garage("code_garage"));
	        	consultation.getBus().setImmatriculation(validation_immatriculation("immatriculation"));
	        	consultation.setDescription_consultation(validation_description_consultation("description_consultation"));
	        	consultation.setStatut_consultation("no_archive");
	        	
	        return consultation;
	               
	        }else{
	            System.out.println("mauvaise fabrication de l'objet garage to add");
	            return null;
	        }
     }
    
    
    
}
