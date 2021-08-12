package Objet_Metiers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import Beans.Passage;
import Beans.Point_Arret;

public class OM_passage {
	
	private HttpServletRequest request;
    private String              resultat_passage;
    private Map<String, String> erreurs_passage        = new HashMap<String, String>();
    
    
    
	public OM_passage(HttpServletRequest request) {
		super();
		this.request = request;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String getResultat_passage() {
		return resultat_passage;
	}
	public void setResultat_passage(String resultat_passage) {
		this.resultat_passage = resultat_passage;
	}
	public Map<String, String> getErreurs_passage() {
		return erreurs_passage;
	}
	public void setErreurs_passage(Map<String, String> erreurs_passage) {
		this.erreurs_passage = erreurs_passage;
	}
    
	public String validation_immatriculation(String champ){
    	String value=request.getParameter(champ);
    	
    	return value;
    }
	
	public String validation_code_point_arret(String champ){
    	String value=request.getParameter(champ);
    	
    	return value;
    }
	
	public String validation_heure_passage(String champ){
    	String value=request.getParameter(champ);
    	
    	return value;
    }
	
	
	public Passage make_passage(){
    	Passage passage=new Passage();
    	
   if(erreurs_passage.size() == 0) {
    	passage.getBus().setImmatriculation(validation_immatriculation("immatriculation"));
    	passage.getPoint_arret().setCode_point_arret(validation_code_point_arret("code_point_arret"));
    	passage.setHeure_passage(validation_heure_passage("heure_passage"));
    	passage.setStatut_passage("no_archive");
    		return passage;
  		}
    else{
    			return null;
    		}
    	}
}
