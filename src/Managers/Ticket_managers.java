
package Managers;

import Beans.Bus_Ligne;
import dao.DAOFactory;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import dao.DAOException;
import dao.DAOUtilitaire;
import java.sql.SQLException;


public class Ticket_managers {
    
    //attente des requetes sql
    
    public static final String sql_add_repartition="INSERT INTO BUS_LIGNE VALUES(?,?,?,?,?,?,?)";
    public static final String sql_repartition_date="SELECT HEURE_DEPART,QUANTITE_TICKET_RECUE, C.NUMERO_CNI ,C.NOM_EMPLOYE , C.PRENOM_EMPLOYE,L.CODE_LIGNE ,L.POINT_DEPART, L.POINT_ARRET,B.IMMATRICULATION, B.NBRE_PLACE,A.NUMERO_CNI, A.NOM_EMPLOYE, A.PRENOM_EMPLOYE FROM ASSISTANT AS A, CHAUFFEUR AS C, BUS AS B, LIGNE AS L, BUS_LIGNE AS BL WHERE A.NUMERO_CNI=BL.NUMERO_CNI_A AND C.NUMERO_CNI=BL.NUMERO_CNI AND L.CODE_LIGNE=BL.CODE_LIGNE AND B.IMMATRICULATION=BL.IMMATRICULATION AND to_char(HEURE_DEPART,'YYYY-MM-DD')=?";
    public static final String sql_repartition_ligne_date="SELECT HEURE_DEPART,QUANTITE_TICKET_RECUE, C.NUMERO_CNI ,C.NOM_EMPLOYE , C.PRENOM_EMPLOYE,L.CODE_LIGNE ,L.POINT_DEPART, L.POINT_ARRET,B.IMMATRICULATION, B.NBRE_PLACE, A.NUMERO_CNI, A.NOM_EMPLOYE, A.PRENOM_EMPLOYE FROM CHAUFFEUR AS C, BUS AS B, LIGNE AS L, BUS_LIGNE AS BL WHERE A.NUMERO_CNI=BL.NUMERO_CNI_A AND C.NUMERO_CNI=BL.NUMERO_CNI AND L.CODE_LIGNE=BL.CODE_LIGNE AND B.IMMATRICULATION=BL.IMMATRICULATION AND L.CODE_LIGNE=? AND to_char(HEURE_DEPART,'YYYY-MM-DD')=?";
    
    private static DAOFactory  daoFactory;

    public Ticket_managers() {
        
        daoFactory=DAOFactory.getInstance();  
        
            }
    
    
    
    
    public void add_repartition(Bus_Ligne bus_ligne){
        
         Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 try {
		       connexion = (Connection) daoFactory.getConnection();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		        				sql_add_repartition,false,bus_ligne.getChauffeur().getNumero_cni(),bus_ligne.getLigne().getCode_ligne(),bus_ligne.getBus().getImmatriculation(),bus_ligne.getHeure_depart(),bus_ligne.getHeure_arrivee(),bus_ligne.getQuantite_ticket_recue(),bus_ligne.getQuantite_restante());
		         preparedStatement.executeUpdate();
		             	
		        	
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		    	DAOUtilitaire.silentClosing( resultSet, preparedStatement, connexion );
		    }
        
        
    }
    
    public ArrayList<Bus_Ligne> repartition_date(String date){
        ArrayList<Bus_Ligne> list_bus_ligne=new ArrayList<Bus_Ligne>();
        
         Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 try {
		       connexion = (Connection) daoFactory.getConnection();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		        				sql_repartition_date,false,date);
		        resultSet=preparedStatement.executeQuery();
		         
                        while(resultSet.next()){
                            list_bus_ligne.add(map(resultSet));
                        }
                        
		        	
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		    	DAOUtilitaire.silentClosing( resultSet, preparedStatement, connexion );
		    }
        
        //en attente de code
        
        return list_bus_ligne;
    }
    
    public ArrayList<Bus_Ligne> repartition_ligne_date(String code_ligne,String date){
        ArrayList<Bus_Ligne> list_bus_ligne=new ArrayList<Bus_Ligne>();
        
        Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 try {
		       connexion = (Connection) daoFactory.getConnection();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		        				sql_repartition_ligne_date,false,code_ligne,date);
		        resultSet=preparedStatement.executeQuery();
		         
                        while(resultSet.next()){
                            list_bus_ligne.add(map(resultSet));
                        }
                        
		        	
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		    	DAOUtilitaire.silentClosing( resultSet, preparedStatement, connexion );
		    }
        
        //en attente de code
        
        return list_bus_ligne;
    }
    
    public static Bus_Ligne map(ResultSet resultset) throws SQLException{
         Bus_Ligne bus_ligne=new Bus_Ligne();
       // ,,  , , , ,, ,, ,, ,          
      
         bus_ligne.setHeure_depart(resultset.getTimestamp("HEURE_DEPART").toString());
         bus_ligne.setQuantite_ticket_recue(resultset.getInt("QUANTITE_TICKET_RECUE"));
         
         bus_ligne.getChauffeur().setNumero_cni(resultset.getInt("C.NUMERO_CNI"));
         bus_ligne.getChauffeur().setNom_employe(resultset.getString("C.NOM_EMPLOYE"));                
         bus_ligne.getChauffeur().setPrenom_employe(resultset.getString("C.PRENOM_EMPLOYE"));
         
         bus_ligne.getLigne().setCode_ligne(resultset.getString("L.CODE_LIGNE"));
         bus_ligne.getLigne().getPoint_depart().setCode_point_arret(resultset.getString("L.POINT_DEPART"));
         bus_ligne.getLigne().getPoint_arret().setCode_point_arret(resultset.getString("L.POINT_ARRET"));
         
         bus_ligne.getAssistant().setNumero_cni(resultset.getInt("A.NUMERO_CNI"));
         bus_ligne.getAssistant().setNom_employe(resultset.getString("A.NOM_EMPLOYE"));
         bus_ligne.getAssistant().setPrenom_employe(resultset.getString("A.PRENOM_EMPLOYE"));        
                 
         bus_ligne.getBus().setImmatriculation(resultset.getString("B.IMMATRICULATION"));
         bus_ligne.getBus().setNbre_place(resultset.getInt("B.NBRE_PLACE"));
        //attente de code
        
        return bus_ligne;
     }
}
