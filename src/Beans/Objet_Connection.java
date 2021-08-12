package Beans;

public class Objet_Connection {
	
	private String url;
	private String identifiant;
	private String password;
	
	
	public Objet_Connection(String url, String identifiant, String password) {
		
		super();
		this.url = url;
		this.identifiant = identifiant;
		this.password = password;
	}


	public Objet_Connection() {
		super();
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getIdentifiant() {
		return identifiant;
	}


	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
