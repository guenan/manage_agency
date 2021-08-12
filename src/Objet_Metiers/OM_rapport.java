package Objet_Metiers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import Beans.Controlleur;
import Beans.Rapport;

public class OM_rapport {

	private HttpServletRequest request;
    private String              resultat_rapport;
    private Map<String, String> erreurs_rapport   = new HashMap<String, String>();
    
    
    
	public OM_rapport(HttpServletRequest request) {
		super();
		this.request = request;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String getResultat_rapport() {
		return resultat_rapport;
	}
	public void setResultat_rapport(String resultat_rapport) {
		this.resultat_rapport = resultat_rapport;
	}
	public Map<String, String> getErreurs_rapport() {
		return erreurs_rapport;
	}
	public void setErreurs_rapport(Map<String, String> erreurs_rapport) {
		this.erreurs_rapport = erreurs_rapport;
	}
    
	 public String validation_id_rapport(String champ){
	    	String value=request.getParameter(champ);
	    	
	    	return value;
	    }
	 public int validation_numero_cni(String champ){
	    	String value=request.getParameter(champ);
	    	
	    	return Integer.parseInt(value);
	    }
	 public String validation_statut(String champ){
	    	String value=request.getParameter(champ);
	    	
	    	return value;
	    }
	 public String validation_objet_rapport(String champ){
	    	String value=request.getParameter(champ);
	    	
	    	return value;
	    }
	 public String validation_contenu_rapport(String champ){
	    	String value=request.getParameter(champ);
	    	
	    	return value;
	    }
	 public String validation_date_envoie(String champ){
	    	String value=request.getParameter(champ);
	    	
	    	return value;
	    }
	 public Rapport make_Rapport(){
		 Rapport rapport=new Rapport();
		 HttpSession session=request.getSession();
	     Controlleur controlleur=(Controlleur) session.getAttribute("user_session");
	    
	    	if(!validation_objet_rapport("objet_rapport").isEmpty() && !validation_contenu_rapport("contenu rapport").isEmpty()){
	    		rapport.setObjet_rapport(validation_objet_rapport("objet_rapport"));
	    		rapport.setContenu_rapport(validation_contenu_rapport("contenu rapport"));
	    		rapport.getControlleur().setNumero_cni(controlleur.getNumero_cni());
	    		//rapport.getControlleur().setNumero_cni(1);
	    		rapport.setStatut("no_read");
	    		return rapport;
	    }
	    else{
			return null;
		}
	    	
	    }
	 
}
