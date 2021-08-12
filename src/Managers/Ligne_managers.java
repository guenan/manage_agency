package Managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Beans.Bus;
import Beans.Ligne;
import dao.DAOException;
import dao.DAOFactory;
import dao.DAOUtilitaire;

public class Ligne_managers {

	private static DAOFactory          daoFactory;
	public static final String sql_add_ligne="INSERT INTO ligne VALUES(?,?,?,?)";
	public static final String sql_select_ligne="SELECT * FROM LIGNE";
	public static final String sql_select_unique_ligne="SELECT * FROM LIGNE where code_ligne=?";
	public static final String sql_update_ligne="UPDATE LIGNE SET code_ligne=?,code_ligne=? ,point_depart=? ,point_arret=? WHERE code_ligne=?";
	
	 public Ligne_managers() {
	        daoFactory=DAOFactory.getInstance();
	    }
	 
	 public boolean add_ligne(Ligne ligne) throws SQLException, java.lang.Exception{
	        
	        
         Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 try {
		       connexion = (Connection) daoFactory.getConnection();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		    		   sql_add_ligne, false,ligne.getCode_ligne() ,ligne.getPoint_depart().getCode_point_arret() ,ligne.getPoint_arret().getCode_point_arret(),ligne.getStatut_ligne());
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
	 
	 public Ligne select_unique_ligne(String code_ligne) throws SQLException, java.lang.Exception{
	        
	        
         Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 
		 
		 try {
		       connexion = (Connection) daoFactory.getConnection();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		        				sql_select_unique_ligne, false,code_ligne);
		       resultSet= preparedStatement.executeQuery();
		       if(resultSet.next()){
		    	return   map_ligne(resultSet); 
		       }else
		    	   return null;
		    	
		             	
		        	
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		        
		    } finally {
		    	DAOUtilitaire.silentClosing( resultSet, preparedStatement, connexion );
		    }
    }
	 
	 public boolean update_ligne(Ligne ligne) throws SQLException, java.lang.Exception{
	        
	        
         Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 try {
		       connexion = (Connection) daoFactory.getConnection();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		    		   sql_update_ligne, false,ligne.getCode_ligne(),ligne.getPoint_depart().getCode_point_arret() ,ligne.getPoint_arret().getCode_point_arret(),ligne.getCode_ligne() );
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
	 
	 public static ArrayList<Ligne> select_all_ligne (){
	        ArrayList<Ligne> liste_ligne=new ArrayList<Ligne>();
	        
	        
	        Connection connexion = null;
			 PreparedStatement preparedStatement = null;
			 ResultSet resultSet = null;
			 
			 try {
			       connexion = (Connection) daoFactory.getConnection();
			       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
			    		   sql_select_ligne, false);
			        resultSet=preparedStatement.executeQuery();
			       while(resultSet.next())  {
	                         
	                         liste_ligne.add(map_ligne(resultSet));
	                       }   	
			        	
			    } catch ( SQLException e ) {
			        throw new DAOException( e );
			    } finally {
			    	DAOUtilitaire.silentClosing( resultSet, preparedStatement, connexion );
			    }
	        
	        //attente de code
	        
	        return liste_ligne;
	    }
	 
	 
	 public static Ligne map_ligne(ResultSet resultset) throws SQLException{
         Ligne ligne=new Ligne();
        try{
        
        	ligne.setCode_ligne(resultset.getString("code_ligne"));
        	ligne.getPoint_depart().setCode_point_arret(resultset.getString("point_depart"));
        	ligne.getPoint_arret().setCode_point_arret(resultset.getString("point_arret"));
        	
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        //attente de code
        
        return ligne;
     }
}
