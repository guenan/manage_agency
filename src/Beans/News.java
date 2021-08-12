
package Beans;


public class News {
    private int id_news;
    private String titre_news;
    private String contenu_news;
    private String sous_contenu_news;
    private String date_creation;
    private String statut_news;
    private Assistant assistant;
    
    public News() {
    }

    
    
    
    
    
    public String getSous_contenu_news() {
		return sous_contenu_news;
	}






	public void setSous_contenu_news(String sous_contenu_news) {
		this.sous_contenu_news = sous_contenu_news;
	}






	public Assistant getAssistant() {
		return assistant;
	}



	public void setAssistant(Assistant assistant) {
		this.assistant = assistant;
	}



	public String getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
    }

    public String getStatut_news() {
        return statut_news;
    }

    public void setStatut_news(String statut_news) {
        this.statut_news = statut_news;
    }

    public String getTitre_news() {
        return titre_news;
    }

    public void setTitre_news(String titre_news) {
        this.titre_news = titre_news;
    }

    
    
    public String getContenu_news() {
        return contenu_news;
    }

    public void setContenu_news(String contenu_news) {
        this.contenu_news = contenu_news;
    }

    public int getId_news() {
        return id_news;
    }

    public void setId_news(int id_news) {
        this.id_news = id_news;
    }
    
    public String get_sous_contenu(int borneinf,int bornesup){
        String sous_chaine=contenu_news.substring(borneinf, bornesup);
        return sous_chaine;
    }
    
}
