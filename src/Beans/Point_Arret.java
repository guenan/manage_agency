
package Beans;

import java.util.ArrayList;


public class Point_Arret {
    
    private String code_point_arret;
    private String emplacement;
    private ArrayList<String>liste_horaire;
    private String statut_point_arret;

    public Point_Arret() {
    	liste_horaire=new ArrayList<String>();
    }
    
    

    
    
    public ArrayList<String> getListe_horaire() {
		return liste_horaire;
	}





	public void setListe_horaire(ArrayList<String> liste_horaire) {
		this.liste_horaire = liste_horaire;
	}





	public String getCode_point_arret() {
        return code_point_arret;
    }

    public void setCode_point_arret(String code_point_arret) {
        this.code_point_arret = code_point_arret;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }
    public void setStatut_point_arret(String statut_point_arret){
    	this.statut_point_arret=statut_point_arret;
    }
    public String getStatut_point_arret(){
    	return statut_point_arret;
    }
}
