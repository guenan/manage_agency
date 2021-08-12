
package Beans;

import java.util.ArrayList;


public class Passassion {
    private ArrayList<Employe> liste_employe1;
    private ArrayList<Employe> liste_employe2;
    private int quantite_ticket_passe;
    private String statut_passassion;

    public Passassion() {
       liste_employe1=new ArrayList<Employe>();
       liste_employe2=new ArrayList<Employe>();
    }

    
    
    public int getQuantite_ticket_passe() {
		return quantite_ticket_passe;
	}



	public void setQuantite_ticket_passe(int quantite_ticket_passe) {
		this.quantite_ticket_passe = quantite_ticket_passe;
	}
	
	public void setStatut_passassion(String statut_passassion){
		this.statut_passassion=statut_passassion;
	}
	public String getStatut_passassion(){
		return statut_passassion;
	}



	public ArrayList<Employe> getListe_employe1() {
        return liste_employe1;
    }

    public void setListe_employe1(ArrayList<Employe> liste_employe1) {
        this.liste_employe1 = liste_employe1;
    }

    public ArrayList<Employe> getListe_employe2() {
        return liste_employe2;
    }

    public void setListe_employe2(ArrayList<Employe> liste_employe2) {
        this.liste_employe2 = liste_employe2;
    }
    
    
}
