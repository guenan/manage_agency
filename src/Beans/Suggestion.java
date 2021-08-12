
package Beans;


public class Suggestion {
    
    private String pseudo;
    private String message;
    private String date_suggestion;
    private String statut_suggestion;
    
    public Suggestion() {
    }

    public String getStatut_suggestion() {
        return statut_suggestion;
    }

    public void setStatut_suggestion(String statut_suggestion) {
        this.statut_suggestion = statut_suggestion;
    }

        
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getDate_suggestion() {
        return date_suggestion;
    }

    public void setDate_suggestion(String date_suggestion) {
        this.date_suggestion = date_suggestion;
    }
    
    
}
