package Beans;

public class Garage {

	private String code_garage;
	private String nom_garage;
	private String statut_garage;
	
	public Garage() {
		
	}

	public String getCode_garage() {
		return code_garage;
	}

	public void setCode_garage(String code_garage) {
		this.code_garage = code_garage;
	}

	public String getNom_garage() {
		return nom_garage;
	}

	public void setNom_garage(String nom_garage) {
		this.nom_garage = nom_garage;
	}
	public void setStatut_garage(String statut_garage){
		this.statut_garage=statut_garage;
	}
	public String getStatut_garage(){
		return statut_garage;
	}
	
	
}
