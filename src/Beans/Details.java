package Beans;
import java.util.ArrayList;

public class Details {
	private String code_point_arret;
	private ArrayList<String> liste_horaire; 

	public Details() {
			
	}
public void setCode_point_point_arret(String code_point_arret){
	this.code_point_arret=code_point_arret;
}
public String getCode_point_arret(){
	return code_point_arret;
}
public void setListe_horaire(ArrayList<String> liste_horaire){
	
	this.liste_horaire=liste_horaire;
}
public ArrayList<String> getListe_horaire(){
	return liste_horaire;
}
}
