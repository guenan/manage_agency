package Managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Beans.Bus;
import Beans.Passage;
import dao.DAOException;
import dao.DAOFactory;
import dao.DAOUtilitaire;

public class Passage_managers {

	private static DAOFactory          daoFactory;
	public static final String sql_add_passage="INSERT INTO passer VALUES(?,?,?,?)";
	
	
	 public Passage_managers() {
	        daoFactory=DAOFactory.getInstance();
	    }
	 
	 public boolean add_passage(Passage passage) throws SQLException, java.lang.Exception{
	        
	        
         Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 try {
		       connexion = (Connection) daoFactory.getConnection();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		        				sql_add_passage, false,passage.getBus().getImmatriculation(),passage.getPoint_arret().getCode_point_arret(),passage.getHeure_passage(),passage.getStatut_passage());
		       if (preparedStatement.executeUpdate()==1) 
		    	   return true;
		       else 
		    	   return false;
		             	
		        	
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		        
		    } finally {
		    	DAOUtilitaire.silentClosing( resultSet, preparedStatement, connexion );
		    }
    }
	 
}
