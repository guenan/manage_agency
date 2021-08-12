
package Beans;

import java.util.ArrayList;


public class Ligne_Point_Arret {
    private ArrayList<Ligne> liste_ligne;
    private ArrayList<Point_Arret> liste_point_arret;

    public Ligne_Point_Arret() {
        liste_ligne=new ArrayList<Ligne>();
        liste_point_arret=new ArrayList<Point_Arret>();
    }

    
    
    
    public ArrayList<Ligne> getListe_ligne() {
        return liste_ligne;
    }

    public void setListe_ligne(ArrayList<Ligne> liste_ligne) {
        this.liste_ligne = liste_ligne;
    }

    public ArrayList<Point_Arret> getListe_point_arret() {
        return liste_point_arret;
    }

    public void setListe_point_arret(ArrayList<Point_Arret> liste_point_arret) {
        this.liste_point_arret = liste_point_arret;
    }
    
    
}
