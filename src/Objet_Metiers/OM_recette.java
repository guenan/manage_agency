
package Objet_Metiers;
import Beans.Employe;
import Beans.Recette;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class OM_recette {
 public final String USER_SESSION="user_session";
 private HttpServletRequest request;   
 private String              resultat_recette;
 private Map<String, String> erreurs_recette         = new HashMap<String, String>();

    public OM_recette(HttpServletRequest request) {
        this.request = request;
    }

    public Map<String, String> getErreurs_recette() {
        return erreurs_recette;
    }

    public void setErreurs_recette(Map<String, String> erreurs_recette) {
        this.erreurs_recette = erreurs_recette;
    }

    public String getResultat_recette() {
        return resultat_recette;
    }

    public void setResultat_recette(String resultat_recette) {
        this.resultat_recette = resultat_recette;
    }
 
   private void setErreurs( String champ, String message ) {
        erreurs_recette.put( champ, message );
    }
    
   public String get_valeur_champ(String nom_champ) throws Exception{
        String value=null;
        
        //attente de code
        
        return value;
    }
   
   
     
   
    public int validation_idrecette(String id_recette){
         
    	int id= Integer.parseInt(request.getParameter(id_recette));
        
        return id;
    }
    
    public int validation_billet_10000(String billet_10000){      
       
        int id= Integer.parseInt(request.getParameter(billet_10000));
        
        return id;
    }
    
    public int validation_billet_5000(String billet_5000){      
    	int   id= Integer.parseInt(request.getParameter(billet_5000));
        
        return id;
    }
    
    public int validation_billet_2000(String billet_2000){      
    	int   id= Integer.parseInt(request.getParameter(billet_2000));
        
        return id;
    }
    public int validation_billet_1000(String billet_1000){      
    	int  id= Integer.parseInt(request.getParameter(billet_1000));
        
        return id;
    }
    public int validation_billet_500(String billet_500){      
    	int id= Integer.parseInt(request.getParameter(billet_500));
        
        return id;
    }
    public int validation_piece_500(String piece_500){      
    	int id= Integer.parseInt(request.getParameter(piece_500));
        
        return id;
    }
    public int validation_piece_100(String piece_100){      
    	int   id= Integer.parseInt(request.getParameter(piece_100));
        
        return id;
    }
    public int validation_piece_50(String piece_50){      
    	int id= Integer.parseInt(request.getParameter(piece_50));
        
        return id;
    }
    public int validation_piece_25(String piece_25){      
    	int id= Integer.parseInt(request.getParameter(piece_25));
        
        return id;
    }
    public int validation_piece_10(String piece_10){      
    	int id= Integer.parseInt(request.getParameter(piece_10));
        
        return id;
    }
    public int validation_piece_5(String piece_5){      
    	int  id= Integer.parseInt(request.getParameter(piece_5));
        
        return id;  
    }    
    
    public String validation_date_news(String date_recette){// important pour la date entrée dans le filtre de recherche du manager
        String value=request.getParameter(date_recette);    
        
        return value;
    }
    
    public String validation_code_ligne(String code_ligne){// important pour la ligne entrée dans le filtre de recherche du manager
       String value=null;   
        value=request.getParameter(code_ligne);
        return value;
    }
    
    public long validation_cni_chauffeur(String cni_chauffeur){// important pour cni entré dans le filtre de recherche du manager
         
      long  value=Integer.parseInt(request.getParameter(cni_chauffeur));
        return value;
    }
    public long validation_cni_assistant(String cni_chauffeur){// important pour cni entré dans le filtre de recherche du manager
         
      long  value=Integer.parseInt(request.getParameter(cni_chauffeur));
        return value;
    }
    public Recette make_recette_to_update(){
        Recette recette=new Recette();
        
        if(erreurs_recette.size() == 0){
        recette.setId_recette(validation_idrecette("id_recette"));
        recette.setBillet_10000(validation_billet_10000("billet_10000"));
        recette.setBillet_5000(validation_billet_5000("billet_5000"));
        recette.setBillet_2000(validation_billet_2000("billet_2000"));
        recette.setBillet_1000(validation_billet_1000("billet_1000"));
        recette.setBillet_500(validation_billet_500("billet_500"));
        recette.setPiece_500(validation_piece_500("piece_500"));
        recette.setPiece_100(validation_piece_100("piece_100"));
        recette.setPiece_50(validation_piece_50("piece_50"));
        recette.setPiece_25(validation_piece_25("piece_25"));
        recette.setPiece_10(validation_piece_10("piece_10"));
        recette.setPiece_5(validation_piece_5("piece_5"));
        
        //attente de code
        }else{
                System.out.println("mauvaise creation de la recette to update");
            }
        return recette;
    }
    
    public Recette make_recette_to_add(){
        Recette recette=new Recette();
        Employe employe=(Employe)request.getSession().getAttribute(USER_SESSION);
          
            if(erreurs_recette.size() == 0){  
            	
        
        recette.getChauffeur().setNumero_cni(validation_cni_chauffeur("numero_cni"));
        recette.getAssistant().setNumero_cni(employe.getNumero_cni());
        
        recette.setBillet_10000(validation_billet_10000("billet_10000"));
        recette.setBillet_5000(validation_billet_5000("billet_5000"));
        recette.setBillet_2000(validation_billet_2000("billet_2000"));
        recette.setBillet_1000(validation_billet_1000("billet_1000"));
        recette.setBillet_500(validation_billet_500("billet_500"));
        recette.setPiece_500(validation_piece_500("piece_500"));
        recette.setPiece_100(validation_piece_100("piece_100"));
        recette.setPiece_50(validation_piece_50("piece_50"));
        recette.setPiece_25(validation_piece_25("piece_25"));
        recette.setPiece_10(validation_piece_10("piece_10"));
        recette.setPiece_5(validation_piece_5("piece_5"));
        recette.setStatut_recette("no_archive");
        
        
        System.out.println(recette.getBillet_1000());
        System.out.println(recette.getBillet_10000());
        System.out.println(recette.getBillet_2000());
        System.out.println(recette.getPiece_5());
        System.out.println(recette.getDate_enregistrement());
        
        
        return recette;
        
            }else{
                System.out.println("mauvaise creation de la recette to add");
                return null;
            }
        
        //attente de code
        
        
    }
    
    
    
}
