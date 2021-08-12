package Managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Beans.Bus;
import Beans.Point_Arret;
import dao.DAOException;
import dao.DAOFactory;
import dao.DAOUtilitaire;

public class Point_Arret_managers {

	private static DAOFactory          daoFactory;
	public static final String sql_add_point_arret="INSERT INTO point_arret VALUES(?,?,?)";
	public static final String sql_select_point_arret="select * from point_arret";
	public static final String sql_select_unique_point_arret="select * from point_arret where code_point_arret=?";
	public static final String sql_update_point_arret="UPDATE point_arret SET code_point_arret=?,emplacement=? where code_point_arret=?";
	
	public Point_Arret_managers() {
        daoFactory=DAOFactory.getInstance();
    }
	
	 public boolean add_point_arret(Point_Arret point_arret) throws SQLException, java.lang.Exception{
	        
	        
         Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 try {
		       connexion = (Connection) daoFactory.getConnection();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		    		   sql_add_point_arret, false,point_arret.getCode_point_arret(),point_arret.getEmplacement(),point_arret.getStatut_point_arret());
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
	 
	 public Point_Arret select_unique_point_arret(String code_point_arret) throws SQLException, java.lang.Exception{
	        
	        
         Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 
		 
		 try {
		       connexion = (Connection) daoFactory.getConnection();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		        				sql_select_unique_point_arret, false,code_point_arret);
		       resultSet= preparedStatement.executeQuery();
		       if(resultSet.next()){
		    	return   map_point_arret(resultSet); 
		       }else
		    	   return null;
		    	
		             	
		        	
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		        
		    } finally {
		    	DAOUtilitaire.silentClosing( resultSet, preparedStatement, connexion );
		    }
    }
	 
	 public boolean update_point_arret(Point_Arret point_arret) throws SQLException, java.lang.Exception{
	        
	        
         Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 try {
		       connexion = (Connection) daoFactory.getConnection();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		    		   sql_update_point_arret, false,point_arret.getCode_point_arret(),point_arret.getEmplacement(),point_arret.getCode_point_arret());
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
	 
	 public static ArrayList<Point_Arret> select_all_point_arret(){
	        ArrayList<Point_Arret> liste_point_arret=new ArrayList<Point_Arret>();
	        
	        
	        Connection connexion = null;
			 PreparedStatement preparedStatement = null;
			 ResultSet resultSet = null;
			 
			 try {
			       connexion = (Connection) daoFactory.getConnection();
			       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,sql_select_point_arret, false);
			        resultSet=preparedStatement.executeQuery();
			       while(resultSet.next())  {
	                         
			    	   liste_point_arret.add(map_point_arret(resultSet));
	                       }   	
			        	
			    } catch ( SQLException e ) {
			        throw new DAOException( e );
			    } finally {
			    	DAOUtilitaire.silentClosing( resultSet, preparedStatement, connexion );
			    }
	        
	        //attente de code
	        
	        return liste_point_arret;
	    }
	 
	 public static Point_Arret map_point_arret(ResultSet resultset) throws SQLException{
		 Point_Arret point_arret=new Point_Arret();
        try{
        
        	point_arret.setCode_point_arret(resultset.getString("code_point_arret"));
        	point_arret.setEmplacement(resultset.getString("emplacement"));
        	
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        //attente de code
        
        return point_arret;
     }
	
}
