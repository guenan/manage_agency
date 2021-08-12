package Managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Beans.Bus;
import Beans.Rapport;
import dao.DAOException;
import dao.DAOFactory;
import dao.DAOUtilitaire;

public class Rapport_managers {

	private static DAOFactory          daoFactory;
	public static final String sql_add_rapport="INSERT INTO rapport(numero_cni,statut,objet_rapport,contenu_rapport,date_envoie) VALUES(?,?,?,?,NOW()";
	public static final String sql_select_rapport="select * from rapport where statut=?";
	public Rapport_managers() {
        daoFactory=DAOFactory.getInstance();
    }
	
	public boolean add_rapport(Rapport rapport) throws SQLException, java.lang.Exception{
        
        
        Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 try {
		       connexion = (Connection) daoFactory.getConnection();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		        				sql_add_rapport, false,rapport.getControlleur().getNumero_cni(),rapport.getStatut(),rapport.getObjet_rapport(),rapport.getContenu_rapport());
		       if (preparedStatement.executeUpdate()==1) 
		    	   return true   ;
		       else 
		    	   return false;
		             	
		        	
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		        
		    } finally {
		    	DAOUtilitaire.silentClosing( resultSet, preparedStatement, connexion );
		    }
   }
	
	 public static ArrayList<Rapport> get_list_raport(String statut){
		 ArrayList<Rapport>liste_rapport=new ArrayList<Rapport>();
	        
	        
	        Connection connexion = null;
			 PreparedStatement preparedStatement = null;
			 ResultSet resultSet = null;
			 
			 try {
			       connexion = (Connection) daoFactory.getConnection();
			       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
			    		   sql_select_rapport, false,statut);
			        resultSet=preparedStatement.executeQuery();
			       while(resultSet.next())  {
	                         
			    	   liste_rapport.add(map_rapport(resultSet));
	                       }   	
			        	
			    } catch ( SQLException e ) {
			        throw new DAOException( e );
			    } finally {
			    	DAOUtilitaire.silentClosing( resultSet, preparedStatement, connexion );
			    }
	        //attente de code
	        
	        return liste_rapport;
	    }
	 
	 
	 public static Rapport map_rapport(ResultSet resultset) throws SQLException{
		 Rapport rapport=new Rapport();
        try{
         
         rapport.setId_rapport(resultset.getInt("id_rapport"));
         rapport.setContenu_rapport(resultset.getString("contenu_rapport"));
         rapport.setDate_envoie((resultset.getTimestamp("date_envoie")).toString());
         rapport.setObjet_rapport(resultset.getString("objet_rapport"));
         rapport.setStatut(resultset.getString("statut"));
         rapport.getControlleur().setNumero_cni(resultset.getInt("numero_cni"));
         
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        //attente de code
        
        return rapport;
     }
	
}
