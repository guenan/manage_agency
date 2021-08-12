package Beans;

public class Sortie {

	private Assistant assistant;
	private Chauffeur chauffeur;
	private String heure_depart;
	private int quantite_ticket_recue;
	public Assistant getAssistant() {
		return assistant;
	}
	public void setAssistant(Assistant assistant) {
		this.assistant = assistant;
	}
	public Chauffeur getChauffeur() {
		return chauffeur;
	}
	public void setChauffeur(Chauffeur chauffeur) {
		this.chauffeur = chauffeur;
	}
	public String getHeure_depart() {
		return heure_depart;
	}
	public void setHeure_depart(String heure_depart) {
		this.heure_depart = heure_depart;
	}
	public int getQuantite_ticket_recue() {
		return quantite_ticket_recue;
	}
	public void setQuantite_ticket_recue(int quantite_ticket_recue) {
		this.quantite_ticket_recue = quantite_ticket_recue;
	}
	
	
}
