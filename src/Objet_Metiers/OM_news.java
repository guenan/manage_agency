
package Objet_Metiers;

import Beans.Employe;
import Beans.News;
import Beans.Suggestion;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class OM_news {
    
    private HttpServletRequest request;
    private String              resultat_news;
    private Map<String, String> erreurs_news         = new HashMap<String, String>();
    private Map<String, String> erreurs_suggestion         = new HashMap<String, String>();
    
    
    public OM_news(HttpServletRequest request) {
        this.request = request;
    }

    public Map<String, String> getErreurs() {
        return erreurs_news;
    }
    
    private void setErreurs( String champ, String message ) {
        erreurs_news.put( champ, message );
    }

    public Map<String, String> getErreurs_news() {
        return erreurs_news;
    }

    public void setErreurs_news(Map<String, String> erreurs_news) {
        this.erreurs_news = erreurs_news;
    }

    public Map<String, String> getErreurs_suggestion() {
        return erreurs_suggestion;
    }

    public void setErreurs_suggestion(Map<String, String> erreurs_suggestion) {
        this.erreurs_suggestion = erreurs_suggestion;
    }

    public String getResultat_news() {
        return resultat_news;
    }

    public void setResultat_news(String resultat_news) {
        this.resultat_news = resultat_news;
    }
    
    
    
    public void setErreurs(Map<String, String> erreurs) {
        this.erreurs_news = erreurs;
    }

    public String getResultat() {
        return resultat_news;
    }

    public void setResultat(String resultat) {
        this.resultat_news = resultat;
    }
    
    
    
 
    public String get_valeur_champ(String nom_champ) throws Exception{
        String value=null;
       value= request.getParameter(nom_champ);
        //attente de code
        
        return value;
    }
    
    
    public News make_news_to_add(){
        News news=new News();
        HttpSession session=request.getSession();
        Employe employe=(Employe)session.getAttribute("user_session");
        
        if(!validation_titre_news("titre_news").isEmpty() && !validation_contenu_news("contenu_news").isEmpty()){
        news.setTitre_news(validation_titre_news("titre_news"));
        news.setContenu_news(validation_contenu_news("contenu_news"));
        news.setStatut_news("no_publie");    
        news.getAssistant().setNumero_cni(employe.getNumero_cni());
        System.out.println(news.getContenu_news());
        System.out.println(news.getTitre_news());
        return news;
               
        }else{
            System.out.println("mauvaise fabrication de l'objet news to add");
            return null;
        }
                
        //attente de code
        
        
    }
    
    public News make_news_to_update(){
        News news=new News();
        
        if(erreurs_news.isEmpty()){
        news.setId_news(validation_idnews("id_news"));
        news.setTitre_news(validation_titre_news("titre_news"));
        news.setContenu_news(validation_contenu_news("contenu_news"));
        news.setStatut_news("visible");                
               
        }else{
            System.out.println("mauvaise fabrication de l'objet news to update");
        }
        //attente de code
        
        return news;
    }
    
    public Suggestion make_suggestion_to_add(){
        
        Suggestion suggestion=new Suggestion();
        if(!validation_pseudo("pseudo").isEmpty() && !validation_message("message").isEmpty()){
        suggestion.setPseudo(validation_pseudo("pseudo"));
        suggestion.setMessage(validation_message("message"));
        suggestion.setStatut_suggestion("no_read");
        
        return suggestion;
        
        }else{
            System.err.println("mauvaise fabrication de l'objet suggestion to add");
            return null;
        }
        //attente de code
        
        
    }
    
    public int validation_idnews(String id_news){
        int id=0;
        id=Integer.parseInt(request.getParameter(id_news));
        
        return id;
    }
    
    public String validation_titre_news(String titre_news){
        String value=null;   
        value=request.getParameter(titre_news);
        return value;
    }
    
     public String validation_statut_news(String statut_news){
        String value=null;   
        value=request.getParameter(statut_news);
        return value;
    }
    
    public String validation_contenu_news(String contenu_news){
        String value=null;   
        value=request.getParameter(contenu_news);
        return value;
    }
    
    public String validation_date_news(String date_news){// important pour la date entrer dans le filtre de recherche du manager
       String value=null;   
        value=request.getParameter(date_news);
        return value;
    }
    
    public String validation_pseudo(String pseudo_suggestion){// important pour la date entrer dans le filtre de recherche du manager
      String value=null;
        value=request.getParameter(pseudo_suggestion);
        return value;
    }
    
    public String validation_message(String message_suggestion){// important pour la date entrer dans le filtre de recherche du manager
                String value=null;
               value= request.getParameter(message_suggestion);
               return value;
    }
    
    public int validation_numero_cni(String champ){
    	String value=request.getParameter(champ);
    	
    	return Integer.parseInt(value);
    }
}
