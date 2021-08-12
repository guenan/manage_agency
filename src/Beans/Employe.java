
package Beans;


public class Employe {
    
    private long numero_cni;
    private String nom_employe;
    private String prenom_employe;
    private String date_naissance;
    private String sexe;
    private String date_recrutement;
    private String loggin;
    private String password;
    private long salaire;
    private String licencie;
    private String heure_service;
    private Droit_Acces droit_employe;
    private String statut;
    
    

    public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public Employe() {
        droit_employe=new Droit_Acces();
    }

    public int get_droit(){
    	return droit_employe.getId_droit();
    }
    
    public String getHeure_service() {
        return heure_service;
    }

    public void setHeure_service(String heure_service) {
        this.heure_service = heure_service;
    }

    
    
    public String getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getDate_recrutement() {
        return date_recrutement;
    }

    public void setDate_recrutement(String date_recrutement) {
        this.date_recrutement = date_recrutement;
    }

    public Droit_Acces getDroit_employe() {
        return droit_employe;
    }

    public void setDroit_employe(Droit_Acces droit_employe) {
        this.droit_employe = droit_employe;
    }

    public String getLicencie() {
        return licencie;
    }

    public void setLicencie(String licencie) {
        this.licencie = licencie;
    }

    public String getLoggin() {
        return loggin;
    }

    public void setLoggin(String loggin) {
        this.loggin = loggin;
    }

    public String getNom_employe() {
        return nom_employe;
    }

    public void setNom_employe(String nom_employe) {
        this.nom_employe = nom_employe;
    }

    public long getNumero_cni() {
        return numero_cni;
    }

    public void setNumero_cni(long numero_cni) {
        this.numero_cni = numero_cni;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrenom_employe() {
        return prenom_employe;
    }

    public void setPrenom_employe(String prenom_employe) {
        this.prenom_employe = prenom_employe;
    }

    public long getSalaire() {
        return salaire;
    }

    public void setSalaire(long salaire) {
        this.salaire = salaire;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }
    
    public boolean isGaragiste(){
    	if(this.get_droit()==2) return true;
    	return false;
    }
    
    public boolean isAssistant(){
    	if(this.get_droit()==4) return true;
    	return false;
    }
    
    public boolean isChef_agence(){
    	if(this.get_droit()==5) return true;
    	return false;
    }
    
    public boolean isControlleur(){
    	if(this.get_droit()==3) return true;
    	return false;
    }
    
}
