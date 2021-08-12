
package Beans;

import java.util.ArrayList;


public class Ligne {
    
    private String code_ligne;
    private Point_Arret point_arret;
    private Point_Arret point_depart;
    private ArrayList<Point_Arret> liste_point_arret;
    private String statut_ligne; 

    public Ligne() {
        liste_point_arret=new ArrayList<Point_Arret>(); 
        point_arret=new Point_Arret();
        point_depart=new Point_Arret();
    }

    public ArrayList<Point_Arret> getListe_point_arret() {
        return liste_point_arret;
    }

    public void setListe_point_arret(ArrayList<Point_Arret> liste_point_arret) {
        this.liste_point_arret = liste_point_arret;
    }

    public Point_Arret getPoint_depart() {
        return point_depart;
    }

    public void setPoint_depart(Point_Arret point_depart) {
        this.point_depart = point_depart;
    }

    public String getCode_ligne() {
        return code_ligne;
    }

    public void setCode_ligne(String code_ligne) {
        this.code_ligne = code_ligne;
    }  
 
    public Point_Arret getPoint_arret() {
        return point_arret;
    }

    public void setPoint_arret(Point_Arret point_arret) {
        this.point_arret = point_arret;
    }

    public Point_Arret getPointdepart() {
        return point_depart;
    }

    public void setPointdepart(Point_Arret pointdepart) {
        this.point_depart = pointdepart;
    }
    
    public void setStatut_ligne(String statut_ligne){
    	this.statut_ligne=statut_ligne;
    	 }
    public String getStatut_ligne(){
    	return statut_ligne;
    }
    
    
}
