
package Beans;

import java.util.ArrayList;


public class Chauffeur extends Employe{
    private String date_conduite;
    private ArrayList<Recette> liste_recette;
    private Ligne chauffeur_ligne;
    private Bus bus;
    private Ligne ligne;
    
    public Chauffeur() {
        liste_recette=new ArrayList<Recette>();
        chauffeur_ligne=new Ligne();
        bus=new Bus();
        ligne=new Ligne();
    }

    
    
    public Bus getBus() {
		return bus;
	}



	public void setBus(Bus bus) {
		this.bus = bus;
	}



	public Ligne getLigne() {
		return ligne;
	}



	public void setLigne(Ligne ligne) {
		this.ligne = ligne;
	}



	public Ligne getChauffeur_ligne() {
        return chauffeur_ligne;
    }

    public void setChauffeur_ligne(Ligne chauffeur_ligne) {
        this.chauffeur_ligne = chauffeur_ligne;
    }

    public String getDate_conduite() {
        return date_conduite;
    }

    public void setDate_conduite(String heure_service) {
        this.date_conduite = date_conduite;
    }

    public ArrayList<Recette> getListe_recette() {
        return liste_recette;
    }

    public void setListe_recette(ArrayList<Recette> liste_recette) {
        this.liste_recette = liste_recette;
    }
    
    
    
}
