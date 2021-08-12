package Managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Beans.Ligne;
import Beans.Passassion;
import dao.DAOException;
import dao.DAOFactory;
import dao.DAOUtilitaire;

public class Passassion_managers {

	private static DAOFactory          daoFactory;
	public static final String sql_add_passassion="INSERT INTO passassion VALUES(?,?,NOW(),?,?)";
	
	 public Passassion_managers() {
	        daoFactory=DAOFactory.getInstance();
	    }

	 public boolean add_passassion(Passassion passassion) throws SQLException, java.lang.Exception{
	        
	        
         Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 try {
		       connexion = (Connection) daoFactory.getConnection();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		    		   sql_add_passassion, false,passassion.getListe_employe1().get(0).getNumero_cni(),passassion.getListe_employe2().get(0).getNumero_cni(),passassion.getQuantite_ticket_passe(),passassion.getStatut_passassion());
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

}
