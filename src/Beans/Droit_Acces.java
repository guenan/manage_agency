package Beans;

import java.util.ArrayList;


public class Droit_Acces {
    
    private int id_droit;
    private String nom_droit;
    private String description_droit;
    private ArrayList<Employe> liste_employe_droit;

    public Droit_Acces() {
      liste_employe_droit=new ArrayList<Employe>();  
    }

    
    
    public String getDescription_droit() {
        return description_droit;
    }

    public void setDescription_droit(String description_droit) {
        this.description_droit = description_droit;
    }

    public int getId_droit() {
        return id_droit;
    }

    public void setId_droit(int id_droit) {
        this.id_droit = id_droit;
    }

    public ArrayList<Employe> getListe_employe_droit() {
        return liste_employe_droit;
    }

    public void setListe_employe_droit(ArrayList<Employe> liste_employe_droit) {
        this.liste_employe_droit = liste_employe_droit;
    }

    public String getNom_droit() {
        return nom_droit;
    }

    public void setNom_droit(String nom_droit) {
        this.nom_droit = nom_droit;
    }
    
    
}
