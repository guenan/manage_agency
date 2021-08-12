
package Beans;


public class Bus_Ligne {
    private Chauffeur chauffeur;
    private Bus bus;
    private Ligne ligne;
    private Assistant assistant;
    private String heure_depart;
    private String heure_arrivee;
    private int quantite_ticket_recue;
    private int quantite_restante;

    public Bus_Ligne() {
        chauffeur=new Chauffeur();
        bus=new Bus();
        ligne=new Ligne();
    }

    public Assistant getAssistant() {
        return assistant;
    }

    public void setAssistant(Assistant assistant) {
        this.assistant = assistant;
    }

    
    
    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Chauffeur getChauffeur() {
        return chauffeur;
    }

    public void setChauffeur(Chauffeur chauffeur) {
        this.chauffeur = chauffeur;
    }

    public String getHeure_arrivee() {
        return heure_arrivee;
    }

    public void setHeure_arrivee(String heure_arrivee) {
        this.heure_arrivee = heure_arrivee;
    }
    
    

    public String getHeure_depart() {
        return heure_depart;
    }

    public void setHeure_depart(String heure_depart) {
        this.heure_depart = heure_depart;
    }

    public Ligne getLigne() {
        return ligne;
    }

    public void setLigne(Ligne ligne) {
        this.ligne = ligne;
    }

    public int getQuantite_restante() {
        return quantite_restante;
    }

    public void setQuantite_restante(int quantite_restante) {
        this.quantite_restante = quantite_restante;
    }

    public int getQuantite_ticket_recue() {
        return quantite_ticket_recue;
    }

    public void setQuantite_ticket_recue(int quantite_ticket_recue) {
        this.quantite_ticket_recue = quantite_ticket_recue;
    }
    
    
}
