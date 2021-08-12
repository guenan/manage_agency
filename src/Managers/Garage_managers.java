package Managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Beans.Bus;
import Beans.Garage;
import Beans.Ligne;
import dao.DAOException;
import dao.DAOFactory;
import dao.DAOUtilitaire;

public class Garage_managers {

	private static DAOFactory          daoFactory;
	public static final String sql_add_garage="INSERT INTO garage VALUES(?,?,?)";
	public static final String sql_select_garage="SELECT * FROM GARAGE";
	public static final String sql_select_unique_garage="SELECT * FROM GARAGE where code_garage=?";
	public static final String sql_update_garage="UPDATE GARAGE SET code_garage=?,nom_garage=? where code_garage=?";
	
	 public Garage_managers() {
	        daoFactory=DAOFactory.getInstance();
	    }
	 
	 public boolean add_garage(Garage garage) throws SQLException, java.lang.Exception{
	        
	        
         Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 try {
		       connexion = (Connection) daoFactory.getConnection();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		        				sql_add_garage, false,garage.getCode_garage(),garage.getNom_garage(),garage.getStatut_garage());
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
	 
	 public Garage select_unique_garage(String code_garage) throws SQLException, java.lang.Exception{
	        
	        
         Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 
		 
		 try {
		       connexion = (Connection) daoFactory.getConnection();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		        				sql_select_unique_garage, false,code_garage);
		       resultSet= preparedStatement.executeQuery();
		       if(resultSet.next()){
		    	return   map_garage(resultSet); 
		       }else
		    	   return null;
		    	
		             	
		        	
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		        
		    } finally {
		    	DAOUtilitaire.silentClosing( resultSet, preparedStatement, connexion );
		    }
    }
	 
	 public boolean update_garage(Garage garage) throws SQLException, java.lang.Exception{
	        
	        
         Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 try {
		       connexion = (Connection) daoFactory.getConnection();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		        				sql_update_garage, false,garage.getCode_garage(),garage.getNom_garage(),garage.getCode_garage());
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
	 
	 public static ArrayList<Garage> select_all_garage (){
	        ArrayList<Garage> liste_garage=new ArrayList<Garage>();
	        
	        
	        Connection connexion = null;
			 PreparedStatement preparedStatement = null;
			 ResultSet resultSet = null;
			 
			 try {
			       connexion = (Connection) daoFactory.getConnection();
			       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
			    		   sql_select_garage, false);
			        resultSet=preparedStatement.executeQuery();
			       while(resultSet.next())  {
	                         
	                         liste_garage.add(map_garage(resultSet));
	                       }   	
			        	
			    } catch ( SQLException e ) {
			        throw new DAOException( e );
			    } finally {
			    	DAOUtilitaire.silentClosing( resultSet, preparedStatement, connexion );
			    }
	        
	        //attente de code
	        
	        return liste_garage;
	    }
	 
	 
	 
	 public static Garage map_garage(ResultSet resultset) throws SQLException{
		 Garage garage=new Garage();
        try{
        
        	garage.setCode_garage(resultset.getString("code_garage"));
        	garage.setNom_garage(resultset.getString("nom_garage"));
        	
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        //attente de code
        
        return garage;
     }
}
