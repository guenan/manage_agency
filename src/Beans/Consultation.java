package Beans;

public class Consultation {

	 private String etat_bus;
	 private String date_consultation;
	 private String description_consultation;
	 private Bus bus;
	 private Garage garage;
	 private String statut_consultation;
	 
	 
	 
	 
	public Consultation() {
		bus=new Bus();
		garage=new Garage();
	}
	
	public String getEtat_bus() {
		return etat_bus;
	}
	public void setEtat_bus(String etat_bus) {
		this.etat_bus = etat_bus;
	}
	public String getStatut_consultation(){
		return statut_consultation;
	}
	public void setStatut_consultation(String statut_consultation){
		this.statut_consultation=statut_consultation;
	}
	public String getDate_consultation() {
		return date_consultation;
	}
	public void setDate_consultation(String date_consultation) {
		this.date_consultation = date_consultation;
	}
	public String getDescription_consultation() {
		return description_consultation;
	}
	public void setDescription_consultation(String description_consultation) {
		this.description_consultation = description_consultation;
	}
	public Bus getBus() {
		return bus;
	}
	public void setBus(Bus bus) {
		this.bus = bus;
	}
	public Garage getGarage() {
		return garage;
	}
	public void setGarage(Garage garage) {
		this.garage = garage;
	}
	 
	 
	 
}
