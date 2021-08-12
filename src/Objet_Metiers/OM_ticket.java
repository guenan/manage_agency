
package Objet_Metiers;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;


public class OM_ticket {
    
        
    
    private HttpServletRequest request;   
    private String              resultat_ticket;
    private Map<String, String> erreurs_ticket         = new HashMap<String, String>();

    public OM_ticket(HttpServletRequest request) {
        this.request = request;
    }

//   

    public Map<String, String> getErreurs_recette() {
        return erreurs_ticket;
    }
    
    private void setErreurs( String champ, String message ) {
        erreurs_ticket.put( champ, message );
    }

    public void setErreurs_recette(Map<String, String> erreurs_recette) {
        this.erreurs_ticket = erreurs_recette;
    }

    public String getResultat_recette() {
        return resultat_ticket;
    }

    public void setResultat_recette(String resultat_recette) {
        this.resultat_ticket = resultat_recette;
    }

    public String get_valeur_champ(String nom_champ) throws Exception{
        String value=null;
        
        //attente de code
        
        return value;
    }
    
    public void validation_code_ligne_ticket(String code_ligne){// important pour la ligne entrée dans le filtre de recherche du manager
       
    }
    
    public void validation_date_ticket(String date_ticket){// important pour la date entrée dans le filtre de recherche du manager
       
    }
    
     public int validation_cni_chauffeur(String cni_chauffeur){// important pour cni entré dans le filtre de recherche du manager
         
      int  value=Integer.parseInt(request.getParameter(cni_chauffeur));
        return value;
    }
     
     public String validation_code_bus(String code_bus){// important pour cni entré dans le filtre de recherche du manager
         
      String  value=request.getParameter(code_bus);
        return value;
    }
}
