package Managers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Time;

import Beans.Bus;
import Beans.News;
import Beans.Objet_Connection;
import Beans.Point_Arret;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;


import dao.DAOException;
import dao.DAOFactory;
import dao.DAOUtilitaire;


public class Bus_managers {
    
	private static DAOFactory          daoFactory;
	public static final String sql_add_bus="INSERT INTO BUS VALUES(?,?,?,?,?)";
	public static final String sql_select_bus="SELECT * FROM BUS WHERE STATUT_BUS=?";
	public static final String sql_select_unique_bus="SELECT * FROM BUS where immatriculation=?";
	public static final String sql_update_bus="UPDATE BUS SET immatriculation=?,code_ligne=? nbre_place=? capacite_reservoire=? WHERE immatriculation=?";
	public static final String sql_get_bus_point_arret="SELECT DISTINCT ON (POINT_ARRET.CODE_POINT_ARRET, POINT_ARRET.EMPLACEMENT) POINT_ARRET.CODE_POINT_ARRET, POINT_ARRET.EMPLACEMENT  FROM PASSER,BUS,POINT_ARRET WHERE PASSER.IMMATRICULATION=BUS.IMMATRICULATION AND PASSER.CODE_POINT_ARRET=POINT_ARRET.CODE_POINT_ARRET AND BUS.IMMATRICULATION=?";
	public static final String sql_get_horaire_bus="SELECT PASSER.HEURE_PASSAGE FROM PASSER,BUS,POINT_ARRET WHERE PASSER.IMMATRICULATION=BUS.IMMATRICULATION AND PASSER.CODE_POINT_ARRET=POINT_ARRET.CODE_POINT_ARRET AND BUS.IMMATRICULATION=? AND POINT_ARRET.CODE_POINT_ARRET=? ORDER BY heure_passage";
	       
	Objet_Connection instance=daoFactory.getInformation();
	
	 public Bus_managers() {
	        daoFactory=DAOFactory.getInstance();
	    }
	 
	 
	
	 public ArrayList<String> get_horaire_bus_point_arret(String immatriculation,String code_point_arret){
		 
		 ArrayList<String> liste_horaire=new ArrayList<String>();
		 
		 Connection connexion = null;
 		 PreparedStatement preparedStatement1 = null;
 		 
 		
 		
         
      
         try{
        	   //  connexion = (Connection) daoFactory.getConnection();
                      connexion = DriverManager.getConnection( instance.getUrl(), instance.getIdentifiant(), instance.getPassword() );
		       
        	 
        	 preparedStatement1 = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
        			 sql_get_horaire_bus, false, immatriculation,code_point_arret);
             
            ResultSet resultSet=preparedStatement1.executeQuery();
            
           while(resultSet.next()){
                
        	  
               liste_horaire.add(resultSet.getTime("heure_passage").toString());
                           
            }
           
           
           
           return liste_horaire;
        	 
		 
	 }catch(SQLException e){
		 
		 return null;
		 
	 }
		 
	 }
	 
	 public ArrayList<Point_Arret> get_bus_point_arret(String immatriculation){
		 
		 Connection connexion=null;
 		 PreparedStatement preparedStatement1 = null;
 		
 		ArrayList<Point_Arret> liste_point_arret=new ArrayList<Point_Arret>();     
 		
         
      
         try{
        	   //  connexion = (Connection) daoFactory.getConnection();
                     connexion = DriverManager.getConnection( instance.getUrl(), instance.getIdentifiant(), instance.getPassword());
		       
        	 
        	 preparedStatement1 = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
        			 sql_get_bus_point_arret, false, immatriculation);
             
            ResultSet resultSet1=preparedStatement1.executeQuery();
            
           while(resultSet1.next()){
        	   Point_Arret point_arret=new Point_Arret();
                
        	   point_arret.setCode_point_arret(resultSet1.getString("CODE_POINT_ARRET"));
               point_arret.setEmplacement(resultSet1.getString("EMPLACEMENT"));
               
               liste_point_arret.add(point_arret);
              
                           
            }
          
           
           return liste_point_arret;
        	 
		 
	 }catch(SQLException e){
		 
		 return null;
		 
	 }
         
	 }
    
	
	 public boolean add_bus(Bus bus) throws SQLException, java.lang.Exception{
	        
	        
         Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 try {
		       connexion = (Connection) daoFactory.getConnection();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		        				sql_add_bus, false,bus.getImmatriculation() ,bus.getLigne().getCode_ligne(), bus.getNbre_place() ,bus.getCapacite_reservoire(),bus.getStatut_bus());
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
	 
	 public Bus select_unique_bus(String immatriculation) throws SQLException, java.lang.Exception{
	        
	        
         Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 
		 
		 try {
		       connexion = (Connection) daoFactory.getConnection();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		        				sql_select_unique_bus, false,immatriculation);
		       resultSet= preparedStatement.executeQuery();
		       if(resultSet.next()){
		    	return   map_bus(resultSet); 
		       }else
		    	   return null;
		    	
		             	
		        	
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		        
		    } finally {
		    	DAOUtilitaire.silentClosing( resultSet, preparedStatement, connexion );
		    }
    }
	 
	 
	 public boolean update_bus(Bus bus) throws SQLException, java.lang.Exception{
	        
	        
         Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 try {
		       connexion = (Connection) daoFactory.getConnection();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		    		   sql_update_bus, false,bus.getImmatriculation(),bus.getLigne().getCode_ligne(), bus.getNbre_place() ,bus.getCapacite_reservoire(),bus.getImmatriculation());
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
	 
	 public static ArrayList<Bus> select_all_bus (){
	        ArrayList<Bus> liste_bus=new ArrayList<Bus>();
	        Bus bus=new Bus();
	        bus.setStatut_bus("no_archive");
	        
	        
	        Connection connexion = null;
			 PreparedStatement preparedStatement = null;
			 ResultSet resultSet = null;
			 
			 try {
			       connexion = (Connection) daoFactory.getConnection();
			       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,sql_select_bus, false,bus.getStatut_bus());
			        resultSet=preparedStatement.executeQuery();
			       while(resultSet.next())  {
	                         
	                         liste_bus.add(map_bus(resultSet));
	                       }   	
			        	
			    } catch ( SQLException e ) {
			        throw new DAOException( e );
			    } finally {
			    	DAOUtilitaire.silentClosing( resultSet, preparedStatement, connexion );
			    }
	        
	        //attente de code
	        
	        return liste_bus;
	    }
	 
	 
	 public static Bus map_bus(ResultSet resultset) throws SQLException{
         Bus bus=new Bus();
        try{
        
        	bus.setImmatriculation(resultset.getString("immatriculation"));
        	bus.setCapacite_reservoire(resultset.getInt("capacite_reservoire"));
        	bus.setNbre_place(resultset.getInt("nbre_place"));
        	
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        //attente de code
        
        return bus;
     }
     
}
