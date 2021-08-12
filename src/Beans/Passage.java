package Beans;

public class Passage {

	private String heure_passage;
	private Bus bus;
	private Point_Arret point_arret;
	private String statut_passage;
	
	
	
	
	
	public Passage() {
		bus=new Bus();
		point_arret=new Point_Arret();
	}
	
	
	public String getHeure_passage() {
		return heure_passage;
	}
	public void setHeure_passage(String heure_passage) {
		this.heure_passage = heure_passage;
	}
	public Bus getBus() {
		return bus;
	}
	public void setStatut_passage(String statut_passage){
		this.statut_passage=statut_passage;
	}
	public String getStatut_passage(){
		return statut_passage;
	}
	public void setBus(Bus bus) {
		this.bus = bus;
	}
	public Point_Arret getPoint_arret() {
		return point_arret;
	}
	public void setPoint_arret(Point_Arret point_arret) {
		this.point_arret = point_arret;
	}
	
	
	
	
}
