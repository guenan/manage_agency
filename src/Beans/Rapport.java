
package Beans;


public class Rapport {
    
    private int id_rapport;
    private String statut;
    private String objet_rapport;
    private String contenu_rapport;
    private String date_envoie;
    private Controlleur controlleur;

    public Rapport() {
        controlleur=new Controlleur();
    }

    
    
    public String getContenu_rapport() {
        return contenu_rapport;
    }

    public void setContenu_rapport(String contenu_rapport) {
        this.contenu_rapport = contenu_rapport;
    }

    public Controlleur getControlleur() {
        return controlleur;
    }

    public void setControlleur(Controlleur controlleur) {
        this.controlleur = controlleur;
    }

    public String getDate_envoie() {
        return date_envoie;
    }

    public void setDate_envoie(String date_envoie) {
        this.date_envoie = date_envoie;
    }

    public int getId_rapport() {
        return id_rapport;
    }

    public void setId_rapport(int id_rapport) {
        this.id_rapport = id_rapport;
    }

    public String getObjet_rapport() {
        return objet_rapport;
    }

    public void setObjet_rapport(String objet_rapport) {
        this.objet_rapport = objet_rapport;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
    
    
}
