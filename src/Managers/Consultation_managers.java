package Managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Beans.Bus;
import Beans.Consultation;
import dao.DAOException;
import dao.DAOFactory;
import dao.DAOUtilitaire;

public class Consultation_managers {

	private static DAOFactory          daoFactory;
	public static final String sql_add_consultation="INSERT INTO CONSULTATION VALUES(?,?,?,NOW(),?,?)";
	
	
	 public Consultation_managers() {
	        daoFactory=DAOFactory.getInstance();
	    }
	 
	 public boolean add_bus(Consultation consultation) throws SQLException, java.lang.Exception{
	        
	        
         Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 try {
		       connexion = (Connection) daoFactory.getConnection();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		        				sql_add_consultation, false,consultation.getGarage().getCode_garage(),consultation.getBus().getImmatriculation(),consultation.getEtat_bus(),consultation.getDescription_consultation(),consultation.getStatut_consultation());
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
