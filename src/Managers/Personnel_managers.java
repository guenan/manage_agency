
package Managers;

import Beans.Administrateur;
import Beans.Assistant;
import Beans.Chef_Agence;
import Beans.Employe;
import Beans.Garagiste;
import dao.DAOFactory;
import dao.DAOUtilitaire;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Date;
import Beans.Chauffeur;
import Beans.Controlleur;
import java.util.ArrayList;
import Beans.Objet_Connection;

public class Personnel_managers {
    //liste des requettes sql
    private static final String sql_add_assistant   = "INSERT INTO assistant(numero_cni,id_droit,nom_employe,prenom_employe,date_naissance,sexe,date_recrutement,salaire,licencie,loggin,password) VALUES (?,?, ?, ?, ?,?, NOW(), ?, ?, ?,? ) ";
    private static final String sql_add_garagiste     = "INSERT INTO garagiste(numero_cni,id_droit,nom_employe,prenom_employe,date_naissance,sexe,date_recrutement,salaire,licencie,loggin,password,code_garage,poste) VALUES (?,?, ?, ?, ?,?, NOW(), ?, ?, ?,? ,?,?)";
    private static final String sql_add_chauffeur   ="INSERT INTO chauffeur (numero_cni,id_droit,nom_employe,prenom_employe,date_naissance,sexe,date_recrutement,salaire,licencie,immatriculation,date_conduite) VALUES (?,?, ?, ?, ?, ?, NOW(), ?,?,?,NOW())";
    private static final String sql_add_controlleur ="INSERT INTO controlleur (numero_cni,id_droit,nom_employe,prenom_employe,date_naissance,sexe,date_recrutement,salaire,licencie,loggin,password,code_brigade) VALUES (?,?, ?, ?, ?,?, NOW(), ?, ?, ?,?,?)";
    private static final String sql_add_chef_agence = "INSERT INTO chef_agence (numero_cni,id_droit,nom_employe,prenom_employe,date_naissance,sexe,date_recrutement,salaire,licencie,loggin,password) VALUES(?,?, ?, ?, ?,?, NOW(), ?, ?, ?,?)";
    private static final String sql_authentify_employe = "SELECT numero_cni,loggin,password,nom_employe,licencie FROM administrateur WHERE loggin=? and password=? and licencie='non_licencie'";
    private static final String sql_send_employe_to_session = "SELECT id_droit,numero_cni,nom_employe,prenom_employe FROM administrateur WHERE numero_cni=?";   
    private static final String sql_delete_employe="UPDATE  employe SET licencie='licencie' WHERE numero_cni=?"; 
    private static final String sql_exist_employe="SELECT immatriculation,loggin FROM administrateur where immatriculation=? or loggin=?";
    private static final String sql_list_connecte="SELECT * FROM administrateur,droit_acces where statut_en_ligne=? and administrateur.id_droit= droit_acces.id_droit";
    private static final String sql_update_statut="update administrateur set statut_en_ligne=? where numero_cni=? ";
    
    
    private static final String sql_print_all_chauffeur="SELECT * FROM CHAUFFEUR WHERE licencie='non_licencie' ";
    private static final String sql_print_all_GARAGISTE="SELECT * FROM GARAGISTE WHERE licencie='non_licencie' ";
    private static final String sql_print_all_ASSISTANT="SELECT * FROM ASSISTANT WHERE licencie='non_licencie' ";
    private static final String sql_print_all_CONTROLLEUR="SELECT * FROM CONTROLLEUR WHERE licencie='non_licencie' ";
    private static final String sql_print_all_CHEF_AGENCE="SELECT * FROM CHEF_AGENCE WHERE licencie='non_licencie' ";
    private static final String sql_search_employe="SELECT * FROM employe, droit_acces WHERE employe.id_droit=droit_acces.id_droit AND numero_cni=?";
    
    
    
    
    
    private static DAOFactory          daoFactory;
    private static DAOUtilitaire       daoUtilitaire;
    Connection connexion=null;

    public Personnel_managers() {
        daoFactory=DAOFactory.getInstance();
    }
    
    
    public boolean add_controlleur(Controlleur controlleur ){
          try{
        Connection connexion=daoFactory.getConnection();
        PreparedStatement requette =  DAOUtilitaire.initialisationRequetePreparee(connexion, sql_add_controlleur, true,controlleur.getNumero_cni(),controlleur.getDroit_employe().getId_droit(),controlleur.getNom_employe(),controlleur.getPrenom_employe(),controlleur.getDate_naissance(),controlleur.getSexe(),controlleur.getSalaire(),controlleur.getLicencie(),controlleur.getLoggin(),controlleur.getPassword(),controlleur.getBrigade().getCode_brigade() );
        int status = requette.executeUpdate();
        
        if (status==1)
            return true;
        else
            return false;
        
        }
        catch(SQLException e){
         e.printStackTrace();
         return false;
        }
    
    }
    
    
    public boolean add_chauffeur(Chauffeur chauffeur ){
        try{
        Connection connexion=daoFactory.getConnection();
        PreparedStatement requette = DAOUtilitaire.initialisationRequetePreparee(connexion, sql_add_chauffeur, true,chauffeur.getNumero_cni(),chauffeur.getDroit_employe().getId_droit(),chauffeur.getNom_employe(),chauffeur.getPrenom_employe(),chauffeur.getDate_naissance(),chauffeur.getSexe(),chauffeur.getSalaire(),chauffeur.getLicencie(),chauffeur.getBus().getImmatriculation());
        int status= requette.executeUpdate();
        if (status==1) return true;
        else return false;
        
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        
    }
    
    
    public Employe search_employe(long num_cni){
  	  
  	  long numero_cni=0;
  	  String nom_emp="";
  	  String prenom_emp="";
  	  String nom_droit="";
  	  String statu="";
  	  String sex="";
  	  String date_naissance="";
  	String date_recrutement="";
  	  long salaire=0;
  	  Objet_Connection instance =daoFactory.getInformation();
  	  PreparedStatement preparedStatement = null;
  		 ResultSet resultSet = null;
  		 
  	  
  	  try{
  		// Connection connexion=daoFactory.getConnection();
  		Connection connexion=	 DriverManager.getConnection( instance.getUrl(), instance.getIdentifiant(), instance.getPassword() );
  		  preparedStatement =daoUtilitaire.initialisationRequetePreparee(connexion, sql_search_employe, false, num_cni);
  		  resultSet =preparedStatement.executeQuery();
  		 Employe employe=new Employe();
  		  while(resultSet.next()){
  			  numero_cni=resultSet.getLong("numero_cni");
  			  nom_emp=resultSet.getString("nom_employe");
  			  prenom_emp=resultSet.getString("prenom_employe");
  			nom_droit=resultSet.getString("nom_droit");
  			  sex=resultSet.getString("sexe");
  			statu=resultSet.getString("licencie");
  			date_naissance=resultSet.getString("date_naissance");
  			salaire=resultSet.getLong("salaire");
  			date_recrutement=(resultSet.getTimestamp("date_recrutement")).toString();
  			  
  		

  		  }
  		  
  		employe.setNumero_cni(numero_cni);
		  employe.setNom_employe(nom_emp);
		  employe.setPrenom_employe(prenom_emp);
		  employe.setLicencie(statu);
		  employe.setDate_naissance(date_naissance);
		  employe.setSexe(sex);
		  employe.setSalaire(salaire);
		  employe.getDroit_employe().setNom_droit(nom_droit);
		  employe.setDate_recrutement(date_recrutement);
		  
		  
		  
		  System.out.println(employe.getNom_employe());
		  System.out.println(employe.getPrenom_employe());
  		return employe;
  		  		  
  	  }
  	  catch(SQLException e){
  		  e.printStackTrace();
  		  return null;
  	  }
  		 
  		 
  	 
    }
    
    public boolean add_assistant(Assistant assistant){
        
        try{
        Connection connexion=daoFactory.getConnection();
        PreparedStatement requette = DAOUtilitaire.initialisationRequetePreparee(connexion, sql_add_assistant, true,assistant.getNumero_cni(),assistant.getDroit_employe().getId_droit(),assistant.getNom_employe(),assistant.getPrenom_employe(),assistant.getDate_naissance(),assistant.getSexe(),assistant.getSalaire(),assistant.getLicencie(),assistant.getLoggin(),assistant.getPassword());
        int status= requette.executeUpdate();
          
             if(status==1) return true;
             else return false;
               
        }
        catch(SQLException e){
            e.printStackTrace();
        return false;
        }
    }
    
    public boolean add_garagiste(Garagiste garagiste){
        try{
        Connection connexion=daoFactory.getConnection();
        PreparedStatement requette = DAOUtilitaire.initialisationRequetePreparee(connexion, sql_add_garagiste, true,garagiste.getNumero_cni(),garagiste.getDroit_employe().getId_droit(),garagiste.getNom_employe(),garagiste.getPrenom_employe(),garagiste.getDate_naissance(),garagiste.getSexe(),garagiste.getSalaire(),garagiste.getLicencie(),garagiste.getLoggin(),garagiste.getPassword(),garagiste.getGarage().getCode_garage(),garagiste.getPoste());
        int status= requette.executeUpdate();
         if(status==1) return true;
             else return false;
        }
        catch(SQLException ex){
              ex.printStackTrace();
        return false;
        }
    }
    public boolean add_chef_agence(Chef_Agence chef_agence){
    
        try{
        Connection connexion=daoFactory.getConnection();
        PreparedStatement requette =  DAOUtilitaire.initialisationRequetePreparee(connexion, sql_add_chef_agence, true,chef_agence.getNumero_cni(),chef_agence.getDroit_employe().getId_droit(),chef_agence.getNom_employe(),chef_agence.getPrenom_employe(),chef_agence.getDate_naissance(),chef_agence.getSexe(),chef_agence.getSalaire(),chef_agence.getLicencie(),chef_agence.getLoggin(),chef_agence.getPassword() );
        int status = requette.executeUpdate();
        if (status==1) return true;
        else return false;
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        
    }
    
    public Employe authentify_employe(Employe employe){
        Employe employe1=new Employe();
        ResultSet resultat=null;
        try{
        Connection connexion=daoFactory.getConnection();
        PreparedStatement requette = DAOUtilitaire.initialisationRequetePreparee(connexion, sql_authentify_employe, false,employe.getLoggin(),employe.getPassword() );
        resultat=requette.executeQuery();

        if(resultat == null) return null;
        else{
              if (resultat.next()){
                employe1.setNumero_cni(resultat.getLong("numero_cni"));
                employe1.setPassword(resultat.getString("password"));
                employe1.setLoggin(resultat.getString("loggin"));
             
                
                //on modifie le statut de l'utilisateur authentifié dans la bd
                
                Personnel_managers.update_statut("on_line", resultat.getInt("numero_cni"));
                return employe1;
               }   else return null;       
        }
       }
        catch(SQLException e){
            
        System.out.println(e.getMessage());
        return null;
        }
        
   //     return employe1;

    }
   
    
    public Employe send_employe_to_session (Employe employe){
         
        long num_cni=0;
        int id_droit=0;
        String nom="";
        String prenom="";
        try{
            Connection connexion = daoFactory.getConnection();
            PreparedStatement requette=DAOUtilitaire.initialisationRequetePreparee(connexion, sql_send_employe_to_session, false,employe.getNumero_cni());
            ResultSet resultat=requette.executeQuery();
            while (resultat.next()){
                num_cni=resultat.getLong("numero_cni");               
                id_droit=resultat.getInt("id_droit");
                nom=resultat.getString("nom_employe");
                prenom=resultat.getString("prenom_employe");
                }
            employe.setNumero_cni(num_cni);
            employe.getDroit_employe().setId_droit(id_droit);
            employe.setNom_employe(nom);
            employe.setPrenom_employe(prenom);
            }
        
        catch(SQLException e){
        
        }
        return employe;
    }


    
    public int delete_employe(int numero_cni){
        try{
            Connection connexion=daoFactory.getConnection();
            PreparedStatement requette=DAOUtilitaire.initialisationRequetePreparee(connexion, sql_delete_employe, true, numero_cni);
            int nbre_ligne_modifie = requette.executeUpdate();
            return nbre_ligne_modifie;
        }
        catch(SQLException e){
            e.printStackTrace();
            return 0;
        }
    
    }
    
        
        public ArrayList<Employe> select_all_chauffeur(){
            ArrayList<Employe> liste_employe=new ArrayList<Employe>();
            
            long num_cni=0;
            String nom_emp="";
            String prenom_emp="";
            String date_recrut=null;
            int salaire=0;
            
            try{
                Connection connexion=daoFactory.getConnection();
                PreparedStatement requette=DAOUtilitaire.initialisationRequetePreparee(connexion, sql_print_all_chauffeur, false);
                ResultSet resultat=requette.executeQuery();
                while (resultat.next()){
                    num_cni=resultat.getInt("numero_cni");
                    nom_emp=resultat.getString("nom_employe");
                    prenom_emp=resultat.getString("prenom_employe");
                    date_recrut= resultat.getTimestamp("date_recrutement").toString();
                    salaire=resultat.getInt("salaire");
                    
                    
                    Employe employe=new Employe();
                    
                    employe.setNumero_cni(num_cni);
                    employe.setNom_employe(nom_emp);
                    employe.setPrenom_employe(prenom_emp);
                    employe.setDate_recrutement(date_recrut);
                    employe.setSalaire(salaire);
                    employe.setStatut(resultat.getString("statut_en_ligne"));
                    
                    
                    liste_employe.add(employe);
                }
                return liste_employe;
            }
            catch(SQLException e){
            	
            	return null;
                
            }
        
        }
        
        public ArrayList<Employe> select_all_GARAGISTE(){
            ArrayList<Employe> liste_employe=new ArrayList<Employe>();
            
            long num_cni=0;
            String nom_emp="";
            String prenom_emp="";
            String date_recrut=null;
            int salaire=0;
            
            try{
                Connection connexion=daoFactory.getConnection();
                PreparedStatement requette=DAOUtilitaire.initialisationRequetePreparee(connexion, sql_print_all_GARAGISTE, false);
                ResultSet resultat=requette.executeQuery();
                while (resultat.next()){
                    num_cni=resultat.getInt("numero_cni");
                    nom_emp=resultat.getString("nom_employe");
                    prenom_emp=resultat.getString("prenom_employe");
                    date_recrut= resultat.getTimestamp("date_recrutement").toString();
                    salaire=resultat.getInt("salaire");
                    
                    Employe employe=new Employe();
                    
                    employe.setNumero_cni(num_cni);
                    employe.setNom_employe(nom_emp);
                    employe.setPrenom_employe(prenom_emp);
                    employe.setDate_recrutement(date_recrut);
                    employe.setSalaire(salaire);
                    employe.setStatut(resultat.getString("statut_en_ligne"));
                    
                    
                    liste_employe.add(employe);
                }
                return liste_employe;
            }
            catch(SQLException e){
            	
            	return null;
                
            }
        
        }
        
        public ArrayList<Employe> select_all_controlleur(){
            ArrayList<Employe> liste_employe=new ArrayList<Employe>();
            
            long num_cni=0;
            String nom_emp="";
            String prenom_emp="";
            String date_recrut=null;
            int salaire=0;
            
            try{
                Connection connexion=daoFactory.getConnection();
                PreparedStatement requette=DAOUtilitaire.initialisationRequetePreparee(connexion, sql_print_all_CONTROLLEUR, false);
                ResultSet resultat=requette.executeQuery();
                while (resultat.next()){
                    num_cni=resultat.getInt("numero_cni");
                    nom_emp=resultat.getString("nom_employe");
                    prenom_emp=resultat.getString("prenom_employe");
                    date_recrut= resultat.getTimestamp("date_recrutement").toString();
                    salaire=resultat.getInt("salaire");
                    
                    Employe employe=new Employe();
                    
                    employe.setNumero_cni(num_cni);
                    employe.setNom_employe(nom_emp);
                    employe.setPrenom_employe(prenom_emp);
                    employe.setDate_recrutement(date_recrut);
                    employe.setSalaire(salaire);
                    employe.setStatut(resultat.getString("statut_en_ligne"));
                  
                     
                    liste_employe.add(employe);
                }
                return liste_employe;
            }
            catch(SQLException e){
            	
            	return null;
                
            }
        
        }
        
        public ArrayList<Employe> select_all_assistant(){
            ArrayList<Employe> liste_employe=new ArrayList<Employe>();
            
            long num_cni=0;
            String nom_emp="";
            String prenom_emp="";
            String date_recrut=null;
            int salaire=0;
            
            try{
                Connection connexion=daoFactory.getConnection();
                PreparedStatement requette=DAOUtilitaire.initialisationRequetePreparee(connexion, sql_print_all_ASSISTANT, false);
                ResultSet resultat=requette.executeQuery();
                while (resultat.next()){
                    num_cni=resultat.getInt("numero_cni");
                    nom_emp=resultat.getString("nom_employe");
                    prenom_emp=resultat.getString("prenom_employe");
                    date_recrut= resultat.getTimestamp("date_recrutement").toString();
                    salaire=resultat.getInt("salaire");
                    
                    Employe employe=new Employe();
                    
                    employe.setNumero_cni(num_cni);
                    employe.setNom_employe(nom_emp);
                    employe.setPrenom_employe(prenom_emp);
                    employe.setDate_recrutement(date_recrut);
                    employe.setSalaire(salaire);
                    employe.setStatut(resultat.getString("statut_en_ligne"));
                    
                    
                    liste_employe.add(employe);
                }
                return liste_employe;
            }
            catch(SQLException e){
            	
            	return null;
                
            }
        
        }
        
        public ArrayList<Employe> select_all_chef_agence(){
            ArrayList<Employe> liste_employe=new ArrayList<Employe>();
            
            long num_cni=0;
            String nom_emp="";
            String prenom_emp="";
            String date_recrut=null;
            int salaire=0;
            
            try{
                Connection connexion=daoFactory.getConnection();
                PreparedStatement requette=DAOUtilitaire.initialisationRequetePreparee(connexion, sql_print_all_CHEF_AGENCE, false);
                ResultSet resultat=requette.executeQuery();
                while (resultat.next()){
                    num_cni=resultat.getInt("numero_cni");
                    nom_emp=resultat.getString("nom_employe");
                    prenom_emp=resultat.getString("prenom_employe");
                    date_recrut= resultat.getTimestamp("date_recrutement").toString();
                    salaire=resultat.getInt("salaire");
                    Employe employe=new Employe();
                    
                    employe.setNumero_cni(num_cni);
                    employe.setNom_employe(nom_emp);
                    employe.setPrenom_employe(prenom_emp);
                    employe.setDate_recrutement(date_recrut);
                    employe.setSalaire(salaire);
                    employe.setStatut(resultat.getString("statut_en_ligne"));
                    
                    
                    liste_employe.add(employe);
                }
                return liste_employe;
            }
            catch(SQLException e){
            	
            	return null;
                
            }
        
        }
        
        public boolean existEmploye(Administrateur employe){
        	
       	 try{
                Connection connexion=daoFactory.getConnection();
                PreparedStatement requette=DAOUtilitaire.initialisationRequetePreparee(connexion, sql_exist_employe, false,employe.getNumero_cni(),employe.getLoggin());
                ResultSet resultat=requette.executeQuery();
                
                if(resultat != null)
               	 return true;
                else
                    return false;
       	 }
            catch(SQLException e){
            	
            	return false;
                
            }
       }
        
public ArrayList<Employe> select_all_connecte(String statut){
        	
            ArrayList<Employe> liste_connecte=new ArrayList<Employe>();
            
           
            
            try{
            	
            	
                Connection connexion=daoFactory.getConnection();
                PreparedStatement requette=DAOUtilitaire.initialisationRequetePreparee(connexion, sql_list_connecte, false,statut);
                ResultSet resultat=requette.executeQuery();
                while (resultat.next()){
                 long   num_cni=resultat.getInt("numero_cni");
                 String  nom_emp=resultat.getString("nom_employe");
                 String  prenom_emp=resultat.getString("prenom_employe");
                 String  nom_droit= resultat.getString("nom_droit");
                 
                    Employe employe=new Employe();
                    
                    employe.setNumero_cni(num_cni);
                    employe.setNom_employe(nom_emp);
                    employe.setPrenom_employe(prenom_emp);
                    employe.getDroit_employe().setNom_droit(nom_droit);
                    
                    
                    liste_connecte.add(employe);
                }
                return liste_connecte;
            }
            catch(SQLException e){
            	
            	return null;
                
            }
        
        }

public static void update_statut(String statut,long cni) throws SQLException{
	
	try{
	Connection connexion=daoFactory.getConnection();
    PreparedStatement requette=DAOUtilitaire.initialisationRequetePreparee(connexion, sql_update_statut, false,statut,cni);
    int resultat=requette.executeUpdate();
	}
	catch(SQLException e){
		
	}
}

public static boolean delete_employe(String licencie,int cni) throws SQLException{
	boolean result=true;
	try{
	Connection connexion=daoFactory.getConnection();
    PreparedStatement requette=DAOUtilitaire.initialisationRequetePreparee(connexion, sql_update_statut, false,licencie,cni);
    int resultat=requette.executeUpdate();
    if (resultat==0){result=false; }
    if (resultat >0 ){result=true;}
    
	}
	catch(SQLException e){
		
	}
       return result;
}



}
