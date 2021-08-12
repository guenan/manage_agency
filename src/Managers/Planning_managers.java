
package Managers;

import Beans.Chauffeur;
import Beans.Bus;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import Beans.Objet_Connection;




 import Beans.Planning;
import Beans.Point_Arret;

import dao.DAOException;
import dao.DAOFactory;
import dao.DAOUtilitaire;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.util.ArrayList;


public class Planning_managers {
    
    //attente de requete sql
   // attention tous ces champs sont des cles public static final String sql_update_planning="UPDATE PLANNING SET NUMERO_CNI=?, CODE_LIGNE=?,IMMATRICULATION=? WHERE ";
    public static final String sql_get_planning="SELECT CHAUFFEUR.NUMERO_CNI,CHAUFFEUR.NOM_EMPLOYE,CHAUFFEUR.PRENOM_EMPLOYE,BUS.IMMATRICULATION,BUS.NBRE_PLACE,LIGNE.CODE_LIGNE ,LIGNE.POINT_DEPART,LIGNE.POINT_ARRET FROM CHAUFFEUR,BUS,LIGNE WHERE CHAUFFEUR.IMMATRICULATION=BUS.IMMATRICULATION AND BUS.CODE_LIGNE=LIGNE.CODE_LIGNE";
    public static final String sql_get_bus_point_arret="SELECT DISTINCT ON (POINT_ARRET.CODE_POINT_ARRET, POINT_ARRET.EMPLACEMENT) POINT_ARRET.CODE_POINT_ARRET, POINT_ARRET.EMPLACEMENT  FROM PASSER,BUS,POINT_ARRET WHERE PASSER.IMMATRICULATION=BUS.IMMATRICULATION AND PASSER.CODE_POINT_ARRET=POINT_ARRET.CODE_POINT_ARRET AND BUS.IMMATRICULATION=?";
    public static final String sql_get_horaire_bus="SELECT PASSER.HEURE_PASSAGE FROM PASSER,BUS,POINT_ARRET WHERE PASSER.IMMATRICULATION=BUS.IMMATRICULATION AND PASSER.CODE_POINT_ARRET=POINT_ARRET.CODE_POINT_ARRET AND BUS.IMMATRICULATION=? AND POINT_ARRET.CODE_POINT_ARRET=?";
    public static final String sql_get_chauffeur_bus="SELECT NUMERO_CNI,NOM_EMPLOYE,PRENOM_EMPLOYE FROM CHAUFFEUR,BUS WHERE CHAUFFEUR.IMMATRICULATION=BUS.IMMATRICULATION AND BUS.IMMATRICULATION=?";
    private static DAOFactory  daoFactory;

    public Planning_managers() {
       // daoFactory=DAOFactory.getInstance();
    }
    Objet_Connection instance=DAOFactory.getInformation();
    
    public void update_planning(Planning planning){
        
    }
    
    public ArrayList<Planning> get_planning(){
        ArrayList<Planning> liste_planning=new ArrayList<Planning>();
        Planning planning=null;
         PreparedStatement preparedStatement1 = null;
		 ResultSet resultSet1 = null;
         PreparedStatement preparedStatement2 = null;
		 ResultSet resultSet2 = null;
		 
		 
		 try {
		          //  connexion = (Connection) daoFactory.getConnection();
                    Connection  connexion = DriverManager.getConnection( instance.getUrl(), instance.getIdentifiant(), instance.getPassword() );
		       
		       preparedStatement1 = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		        				sql_get_planning, false);
		        resultSet1=preparedStatement1.executeQuery();
		      
                        while(resultSet1.next()){
                            
                        
                       liste_planning.add(map_planning(resultSet1));   
                            
                       }   	
		        	
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } 
        
       
        
        return liste_planning;
    }
    
     public Planning  map_planning(ResultSet resultset) throws SQLException{
    	 Bus bus=new Bus();
    	 
 		 PreparedStatement preparedStatement1 = null;
 		 ResultSet resultSet1 = null;
         PreparedStatement preparedStatement2 = null;
                 
 		 ResultSet resultSet2 = null;
 		
         
         Planning planning=new Planning();
         try{
        	   //  connexion = (Connection) daoFactory.getConnection();
        	Connection connexion = DriverManager.getConnection( instance.getUrl(), instance.getIdentifiant(), instance.getPassword() );
        	
        	
        	
        	 
        	 preparedStatement1 = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
          		   sql_get_bus_point_arret, false, resultset.getString("IMMATRICULATION"));
             
            resultSet1=preparedStatement1.executeQuery();
            
           while(resultSet1.next()){
                
               planning.getBus().getListe_point_arret().add(map_point_arret(resultSet1,resultset.getString("IMMATRICULATION")));
               
            }
           
           
        	 
           preparedStatement2 = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
          		   sql_get_chauffeur_bus, false, resultset.getString("IMMATRICULATION"));
           resultSet2=preparedStatement2.executeQuery();
           
           while(resultSet2.next()){
               
               
               Chauffeur chauffeur=new Chauffeur();
               
               chauffeur.setNumero_cni(resultSet2.getInt("numero_cni"));
               chauffeur.setNom_employe(resultSet2.getString("nom_employe"));
               chauffeur.setPrenom_employe(resultSet2.getString("prenom_employe"));    
               
               planning.getBus().getListe_chauffeur().add(chauffeur);      
           
           
           }
           
        	 
         planning.getChauffeur().setNumero_cni(resultset.getInt("NUMERO_CNI"));
         planning.getChauffeur().setNom_employe(resultset.getString("NOM_EMPLOYE"));
         planning.getChauffeur().setPrenom_employe(resultset.getString("PRENOM_EMPLOYE"));
         
         planning.getBus().setImmatriculation(resultset.getString("IMMATRICULATION"));
         planning.getBus().setNbre_place(resultset.getInt("NBRE_PLACE"));
                 
         planning.getLigne().setCode_ligne(resultset.getString("CODE_LIGNE"));
         planning.getLigne().getPoint_depart().setCode_point_arret(resultset.getString("POINT_DEPART"));
         planning.getLigne().getPoint_arret().setCode_point_arret(resultset.getString("POINT_ARRET"));
        	 
        
         return planning;
         }
         catch(SQLException e){
        	 e.printStackTrace();
             return null;
         }
         //en attente de code
         
     }
     
     public Point_Arret map_point_arret(ResultSet resultset,String immatriculation) throws SQLException{
    	 
    	 PreparedStatement preparedStatement1 = null;
    	 PreparedStatement preparedStatement2 = null;
 		 ResultSet resultSet1 = null;
         Point_Arret point_arret=new Point_Arret();
         
         try{
                //  connexion = (Connection) daoFactory.getConnection();
        	 Connection connexion = DriverManager.getConnection( instance.getUrl(), instance.getIdentifiant(), instance.getPassword() );
        	 preparedStatement2 = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
        			 sql_get_bus_point_arret, false, immatriculation,immatriculation);      
         
        
         
         point_arret.setCode_point_arret(resultset.getString("CODE_POINT_ARRET"));
         point_arret.setEmplacement(resultset.getString("EMPLACEMENT"));
         
         preparedStatement1 = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
        		 sql_get_horaire_bus, false, immatriculation,resultset.getString("CODE_POINT_ARRET"));
         
         resultSet1=preparedStatement1.executeQuery();
         
         while(resultSet1.next()){
        	 
        	 point_arret.getListe_horaire().add(resultSet1.getDate("HEURE_PASSAGE").toString());
         }
        
         }catch(SQLException e){
        	 e.printStackTrace();
        	 return null;
             
         }
         //en attente de code
         return point_arret;
     }
     
     public static void main(String args[]){
         
        
         Planning_managers pm=new Planning_managers();
         ArrayList<Planning> alp=new ArrayList<Planning>();
         
    alp= pm.get_planning() ;
 
   for(int i=0;i<alp.get(0).getBus().getListe_point_arret().get(0).getListe_horaire().size();i++)
           System.out.println(alp.get(1).getBus().getListe_point_arret().get(0).getListe_horaire().get(i)  +"   ");
   
   for(int i=0;i<alp.get(0).getBus().getListe_point_arret().size();i++){
           System.out.print(alp.get(0).getBus().getListe_point_arret().get(i).getCode_point_arret()+"   ");
           System.out.println(alp.get(0).getBus().getListe_point_arret().get(i).getEmplacement()+"   ");
          
   }
   
   System.out.println();
   
   System.out.println(alp.get(1).getBus().getListe_chauffeur().get(0).getNom_employe());
           
           
           
           
           
   
     }
    
}
