
package Beans;


public class Planning {
    
    private Chauffeur chauffeur;
    private Bus bus;
    private Ligne ligne;

    public Planning() {
        chauffeur=new Chauffeur();
        bus=new Bus();
        ligne=new Ligne();
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

    public Ligne getLigne() {
        return ligne;
    }

    public void setLigne(Ligne ligne) {
        this.ligne = ligne;
    }
    
    
    
}
