
package Beans;

import java.util.ArrayList;


public class Controlleur extends Employe {
	
    private ArrayList<Rapport> liste_rapport;
    private Brigade brigade;

    public Controlleur() {
        liste_rapport=new ArrayList<Rapport>();
        brigade=new Brigade();
    }

  /*  public boolean isControlleur(){
    	if(this.get_droit()==3) return true;
    	return false;
    }
    */
    
    public Brigade getBrigade() {
		return brigade;
	}




	public void setBrigade(Brigade brigade) {
		this.brigade = brigade;
	}




	public ArrayList<Rapport> getListe_rapport() {
        return liste_rapport;
    }

    public void setListe_rapport(ArrayList<Rapport> liste_rapport) {
        this.liste_rapport = liste_rapport;
    }
    
    
}
