
package Beans;

import java.util.ArrayList;


public class Bus {
    
    
    private String immatriculation;
    private int nbre_place;
    private int capacite_reservoire;
    private String statut_bus;
    private Ligne ligne;
    private ArrayList<Point_Arret> liste_point_arret;
    private ArrayList<Chauffeur> liste_chauffeur;
    
    
    public Bus() {
        liste_point_arret=new ArrayList<Point_Arret>();
        liste_chauffeur=new ArrayList<Chauffeur> ();
        ligne = new Ligne();
    }

    public ArrayList<Chauffeur> getListe_chauffeur() {
        return liste_chauffeur;
    }

    public void setListe_chauffeur(ArrayList<Chauffeur> liste_chauffeur) {
        this.liste_chauffeur = liste_chauffeur;
    }

    
    
    public String getStatut_bus (){
    	return statut_bus;
    }
    
    public void setStatut_bus(String statut_bus){
    	this.statut_bus=statut_bus;
    }
    
    
    
    
    public ArrayList<Point_Arret> getListe_point_arret() {
		return liste_point_arret;
	}






	public void setListe_point_arret(ArrayList<Point_Arret> liste_point_arret) {
		this.liste_point_arret = liste_point_arret;
	}






	public Ligne getLigne() {
		return ligne;
	}






	public void setLigne(Ligne ligne) {
		this.ligne = ligne;
	}






	public int getCapacite_reservoire() {
		return capacite_reservoire;
	}




	public void setCapacite_reservoire(int capacite_reservoire) {
		this.capacite_reservoire = capacite_reservoire;
	}




	public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public int getNbre_place() {
        return nbre_place;
    }

    public void setNbre_place(int nbre_place) {
        this.nbre_place = nbre_place;
    }
    
    
}
