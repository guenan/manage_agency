
package Objet_Metiers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import Beans.Bus;

public class OM_bus {
 
	private HttpServletRequest request;
    private String              resultat_bus;
    private Map<String, String> erreurs_bus         = new HashMap<String, String>();
    
    
    
    
	public OM_bus(HttpServletRequest request) {
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
		return resultat_bus;
	}
	public void setResultat_bus(String resultat_bus) {
		this.resultat_bus = resultat_bus;
	}
	public Map<String, String> getErreurs_bus() {
		return erreurs_bus;
	}
	public void setErreurs_bus(Map<String, String> erreurs_bus) {
		this.erreurs_bus = erreurs_bus;
	}
    
    public String validation_immatriculation(String champ){
    	String value=request.getParameter(champ);
    	
    	return value;
    }
    
    public String validation_code_ligne(String champ){
    	String value=request.getParameter(champ);
    	
    	return value;
    }
    
    public int validation_nbre_place(String champ){
    	String value=request.getParameter(champ);
    	
    	return Integer.parseInt(value);
    }
    
    public int validation_capacite(String champ){
    	String value=request.getParameter(champ);
    	
    	return Integer.parseInt(value);
    }
    
    public Bus make_bus(){
    	Bus bus=new Bus();
    	
    if(erreurs_bus.size()==0) {
    		bus.setImmatriculation(validation_immatriculation("immatriculation"));
    		bus.setCapacite_reservoire(validation_capacite("capacite_reservoire"));
    		bus.setNbre_place(validation_nbre_place("nbre_place"));
    		bus.getLigne().setCode_ligne(validation_code_ligne("code_ligne"));
    		bus.setStatut_bus("no_archive");
    		return bus;
    		}
    else{
    			return null;
    		}
    	}
    	
    }
    
	

