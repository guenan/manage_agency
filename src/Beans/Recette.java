
package Beans;


public class Recette {
    
    private int id_recette;
    private int billet_10000;
    private int billet_5000;
    private int billet_2000;
    private int billet_1000;
    private int billet_500;
    private int piece_500;
    private int piece_100;
    private int piece_50;
    private int piece_25;
    private int piece_10;
    private int piece_5;
    private String statut_recette;
    private String date_enregistrement;
    private Chauffeur chauffeur;
    private Ligne ligne;
    private Assistant assistant;

    public Recette() {
        chauffeur=new Chauffeur();
        assistant=new Assistant();
        ligne=new Ligne();
    }

    public Assistant getAssistant() {
        return assistant;
    }

    public void setAssistant(Assistant assistant) {
        this.assistant = assistant;
    }

    
    
    
    
    
    public String getStatut_recette() {
		return statut_recette;
	}

	public void setStatut_recette(String statut_recette) {
		this.statut_recette = statut_recette;
	}

	public Ligne getLigne() {
        return ligne;
    }

    public void setLigne(Ligne ligne) {
        this.ligne = ligne;
    }

            
    public int getBillet_1000() {
        return billet_1000;
    }

    public void setBillet_1000(int billet_1000) {
        this.billet_1000 = billet_1000;
    }

    public int getBillet_10000() {
        return billet_10000;
    }

    public void setBillet_10000(int billet_10000) {
        this.billet_10000 = billet_10000;
    }

    public int getBillet_2000() {
        return billet_2000;
    }

    public void setBillet_2000(int billet_2000) {
        this.billet_2000 = billet_2000;
    }

    public int getBillet_500() {
        return billet_500;
    }

    public void setBillet_500(int billet_500) {
        this.billet_500 = billet_500;
    }

    public int getBillet_5000() {
        return billet_5000;
    }

    public void setBillet_5000(int billet_5000) {
        this.billet_5000 = billet_5000;
    }

    public Chauffeur getChauffeur() {
        return chauffeur;
    }

    public void setChauffeur(Chauffeur chauffeur) {
        this.chauffeur = chauffeur;
    }

   

    public String getDate_enregistrement() {
        return date_enregistrement;
    }

    public void setDate_enregistrement(String date_enregistrement) {
        this.date_enregistrement = date_enregistrement;
    }

    public int getId_recette() {
        return id_recette;
    }

    public void setId_recette(int id_recette) {
        this.id_recette = id_recette;
    }

    public int getPiece_10() {
        return piece_10;
    }

    public void setPiece_10(int piece_10) {
        this.piece_10 = piece_10;
    }

    public int getPiece_100() {
        return piece_100;
    }

    public void setPiece_100(int piece_100) {
        this.piece_100 = piece_100;
    }

    public int getPiece_25() {
        return piece_25;
    }

    public void setPiece_25(int piece_25) {
        this.piece_25 = piece_25;
    }

    public int getPiece_5() {
        return piece_5;
    }

    public void setPiece_5(int piece_5) {
        this.piece_5 = piece_5;
    }

    public int getPiece_50() {
        return piece_50;
    }

    public void setPiece_50(int piece_50) {
        this.piece_50 = piece_50;
    }

    public int getPiece_500() {
        return piece_500;
    }

    public void setPiece_500(int piece_500) {
        this.piece_500 = piece_500;
    }
    
    
    
}
