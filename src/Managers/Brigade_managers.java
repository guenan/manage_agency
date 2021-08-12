package Managers;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import Beans.Brigade;
import Beans.Bus;
import Beans.Ligne;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import dao.DAOException;
import dao.DAOFactory;
import dao.DAOUtilitaire;

public class Brigade_managers {
     
	private static DAOFactory          daoFactory;
	 public static final String sql_add_brigade="INSERT INTO brigade(code_brigade,nom_brigade,statut_brigade) VALUES(?,?,?)";
	 public static final String sql_select_brigade="SELECT * FROM BRIGADE";
	 public static final String sql_select_unique_brigade="SELECT * FROM BRIGADE where code_brigade=?";
	 public static final String sql_update_brigade="UPDATE BRIGADE SET code_brigade=?,nom_brigade=? WHERE code_brigade=?";
	 
	 
	 
	 
	 public Brigade_managers() {
		super();
		 daoFactory=DAOFactory.getInstance();
	}


	public boolean add_brigade(Brigade brigade) throws SQLException, java.lang.Exception{
	        
	     Connection connect=null;   
         
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 try {
		       
		//connect = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/bd_agence", "postgres", "nangue" );
			  connect=(Connection) DAOFactory.getInstance();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connect,sql_add_brigade, false,brigade.getCode_brigade() ,brigade.getNom_brigade(),brigade.getStatut_brigade());
		       if (preparedStatement.executeUpdate()==1) 
		    	   return true   ;
		       else 
		    	   return false;
		             	
		        	
		    } catch (Exception e ) {
		        throw new DAOException( e );
		        
		    } finally {
		    	DAOUtilitaire.silentClosing( resultSet, preparedStatement, connect );
		    }
    }
	
	public Brigade select_unique_brigade(String code_brigade) throws SQLException, java.lang.Exception{
        
        
        Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 
		 
		 try {
		       connexion = (Connection) daoFactory.getConnection();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		        				sql_select_unique_brigade, false,code_brigade);
		       resultSet= preparedStatement.executeQuery();
		       if(resultSet.next()){
		    	return   map_brigade(resultSet); 
		       }else
		    	   return null;
		    	
		             	
		        	
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		        
		    } finally {
		    	DAOUtilitaire.silentClosing( resultSet, preparedStatement, connexion );
		    }
   }
	
	public boolean update_brigade(Brigade brigade) throws SQLException, java.lang.Exception{
        
	     Connection connect=null;   
        
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 try {
		       
		//connect = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/bd_agence", "postgres", "nangue" );
			  connect=(Connection) DAOFactory.getInstance();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connect,sql_update_brigade, false,brigade.getCode_brigade() ,brigade.getNom_brigade(),brigade.getCode_brigade());
		       if (preparedStatement.executeUpdate()==1) 
		    	   return true   ;
		       else 
		    	   return false;
		             	
		        	
		    } catch (Exception e ) {
		        throw new DAOException( e );
		        
		    } finally {
		    	DAOUtilitaire.silentClosing( resultSet, preparedStatement, connect );
		    }
   }
	 
	 
	 public static ArrayList<Brigade> select_all_brigade (){
	        ArrayList<Brigade> liste_brigade=new ArrayList<Brigade>();
	        
	        
	        Connection connexion = null;
			 PreparedStatement preparedStatement = null;
			 ResultSet resultSet = null;
			 
			 try {
			       connexion = (Connection) daoFactory.getConnection();
			       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
			    		   sql_select_brigade, false);
			        resultSet=preparedStatement.executeQuery();
			       while(resultSet.next())  {
	                         
	                         liste_brigade.add(map_brigade(resultSet));
	                       }   	
			        	
			    } catch ( SQLException e ) {
			        throw new DAOException( e );
			    } finally {
			    	DAOUtilitaire.silentClosing( resultSet, preparedStatement, connexion );
			    }
	        
	        //attente de code
	        
	        return liste_brigade;
	    }
	 
	 
	 public static Brigade map_brigade(ResultSet resultset) throws SQLException{
         Brigade brigade=new Brigade();
        try{
        
        	brigade.setCode_brigade(resultset.getString("code_brigade"));
        	brigade.setNom_brigade(resultset.getString("nom_brigade"));
        	
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        //attente de code
        
        return brigade;
     }
	 
	 public static void main(String args[]){
		 Brigade brigade=new Brigade();
		 Brigade_managers bm=new Brigade_managers();
		 
		 brigade.setCode_brigade("brig2");
		 brigade.setNom_brigade("brigade2");
		 try {
			bm.add_brigade(brigade);
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	 }
	
}
