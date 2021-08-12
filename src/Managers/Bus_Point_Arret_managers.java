package Managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Beans.Bus;
import Beans.Objet_Connection;
import Beans.Bus_Point_Arret;
import dao.DAOException;
import dao.DAOFactory;
import dao.DAOUtilitaire;

public class Bus_Point_Arret_managers {

	private static DAOFactory          daoFactory;
	public static final String sql_add_bus_point_arret="INSERT INTO passer VALUES(?,?,?,?)";
	
	public Bus_Point_Arret_managers() {
        daoFactory=DAOFactory.getInstance();
    }
	
	
	public boolean add_bus_point_arret(Bus_Point_Arret bus_point_arret) throws SQLException, java.lang.Exception{
		Objet_Connection instance =daoFactory.getInformation();    
        
        Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 		 
		 try {
			 
		      // connexion = (Connection) daoFactory.getConnection();
			 connexion=	 DriverManager.getConnection( instance.getUrl(), instance.getIdentifiant(), instance.getPassword() ); 
			 preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		    		   sql_add_bus_point_arret, false,bus_point_arret.getListe_bus().get(0).getImmatriculation(),bus_point_arret.getListe_point_arret().get(0).getCode_point_arret(),bus_point_arret.getHeure_depart(),bus_point_arret.getstatut_bus_point_arret());
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
