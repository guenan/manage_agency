package Objet_Metiers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import Beans.Employe;
import Beans.Passassion;

public class OM_passassion {

	
	private HttpServletRequest request;
    private String              resultat_passassion;
    private Map<String, String> erreurs_passassion        = new HashMap<String, String>();
    
	public OM_passassion(HttpServletRequest request) {
		super();
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getResultat_passassion() {
		return resultat_passassion;
	}

	public void setResultat_passassion(String resultat_passassion) {
		this.resultat_passassion = resultat_passassion;
	}

	public Map<String, String> getErreurs_passassion() {
		return erreurs_passassion;
	}

	public void setErreurs_passassion(Map<String, String> erreurs_passassion) {
		this.erreurs_passassion = erreurs_passassion;
	}
    
    
	public int validation_numero_cni(String champ){
    	String value=request.getParameter(champ);
    	
    	return Integer.parseInt(value);
    }
	
	 public String validation_date_passassion(String date_news){// important pour la date entrer dans le filtre de recherche du manager
	       String value=null;   
	        value=request.getParameter(date_news);
	        return value;
	    }
	 
	 public int validation_quantite_ticket(String champ){
		 
		    int value=0;   
	        value=Integer.parseInt(request.getParameter(champ));
	        return value;
	    }
	 
	 
	 public Passassion make_passassion_to_add(){
		 Passassion passassion=new Passassion();
	     
	          
	            if(erreurs_passassion.size() == 0){  
	         Employe employe1=new Employe();
	         Employe employe2=new Employe();
	         
	         employe1.setNumero_cni(validation_numero_cni("numero_cni"));
	         employe2.setNumero_cni(validation_numero_cni("numero_cni_1"));
	         
	        passassion.getListe_employe1().add(employe1);
	        passassion.getListe_employe2().add(employe2);
	        passassion.setQuantite_ticket_passe(validation_quantite_ticket("quantite_ticket_passee"));
	        passassion.setStatut_passassion("no_archive");
	        return passassion;
	            }else{
	                System.out.println("mauvais ajout de la passassion");
	                return null;
	            }
	        
	        //attente de code
	        
	        
	    }
	    
    
}
