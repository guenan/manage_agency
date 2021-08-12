
package Beans;

import java.util.ArrayList;
import java.util.Date;
import java.sql.Time;


public class Bus_Point_Arret {
    private ArrayList<Bus> liste_bus;
    private ArrayList<Point_Arret> liste_point_arret;
    private Time heure_depart;
    private String heure_arrivee;
    private String statut_bus_point_arret;

    public Bus_Point_Arret() {
        liste_bus=new ArrayList<Bus>();
        liste_point_arret=new ArrayList<Point_Arret>(); 
    
    }

    
    
    public String getHeure_arrivee() {
        return heure_arrivee;
    }

    public void setHeure_arrivee(String heure_arrivee) {
        this.heure_arrivee = heure_arrivee;
    }

    public String getstatut_bus_point_arret() {
        return statut_bus_point_arret;
    }

    public void setstatut_point_arret(String statut_bus_point_arret) {
        this.statut_bus_point_arret =statut_bus_point_arret;
    }

    
    
    public Time getHeure_depart() {
        return heure_depart;
    }

    public void setHeure_depart(Time heure_depart) {
        this.heure_depart = heure_depart;
    }

    public ArrayList<Bus> getListe_bus() {
        return liste_bus;
    }

    public void setListe_bus(ArrayList<Bus> liste_bus) {
        this.liste_bus = liste_bus;
    }

    public ArrayList<Point_Arret> getListe_point_arret() {
        return liste_point_arret;
    }

    public void setListe_point_arret(ArrayList<Point_Arret> liste_point_arret) {
        this.liste_point_arret = liste_point_arret;
    }
    
    
}
