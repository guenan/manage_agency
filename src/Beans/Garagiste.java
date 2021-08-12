package Beans;

public class Garagiste extends Employe {
	private String poste;
	private Garage garage;
	
	
	
	
	public Garagiste() {
		super();
		garage=new Garage();
	}
	public String getPoste() {
		return poste;
	}
	public void setPoste(String poste) {
		this.poste = poste;
	}
	public Garage getGarage() {
		return garage;
	}
	public void setGarage(Garage garage) {
		this.garage = garage;
	}
	
	/*public boolean isGaragiste(){
    	if(this.get_droit()==2) return true;
    	return false;
    }*/

}
