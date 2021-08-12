package Beans;

public class Brigade {

	
	private String code_brigade;
	private String nom_brigade;
	private String statut_brigade;
	
	public Brigade() {
		super();
	}

	public String getCode_brigade() {
		return code_brigade;
	}

	public void setCode_brigade(String code_brigade) {
		this.code_brigade = code_brigade;
	}

	public String getNom_brigade() {
		return nom_brigade;
	}

	public void setNom_brigade(String nom_brigade) {
		this.nom_brigade = nom_brigade;
	}
	public void setStatut_brigade(String statut_brigade){
		this.statut_brigade=statut_brigade;
	}
	public String getStatut_brigade(){
		return statut_brigade;
	}
	
	
	
}
