
package Managers;

import Beans.News;
import Beans.Suggestion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import dao.DAOException;
import dao.DAOFactory;
import dao.DAOUtilitaire;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class News_managers {
    public static final String sql_add_suggestion="INSERT INTO SUGGESTION(pseudo,message,date_suggestion,statut_suggestion) VALUES(?,?,NOW(),?)";
    public static final String sql_get_list_suggestion="SELECT * FROM SUGGESTION where STATUT_SUGGESTION=? ORDER BY DATE_SUGGESTION DESC";
    public static final String sql_suggestion_date="SELECT * FROM SUGGESTION WHERE date_suggestion=?";
    //liste des requetes sql
    public static final String sql_add_news  = "INSERT INTO NEWS(numero_cni,titre_news ,contenu_news,date_creation,statut_news) VALUES (?,?,?, NOW(), ?)";
    public static final String sql_delete_news= "DELETE FROM NEWS WHERE id_news=? ";
    public static final String sql_update_news="UPDATE NEWS SET titre_news=? , contenu_news=? , date_creation=? , statut_news=? where id_news=?";
    public static final String sql_get_unique_news="SELECT * FROM NEWS WHERE id_news=?";
    public static final String sql_get_list_news="SELECT id_news, titre_news, contenu_news, date_creation, statut_news FROM NEWS WHERE STATUT_NEWS='publie' ORDER BY date_creation DESC LIMIT ?";
    public static final String sql_news_title="SELECT * FROM NEWS WHERE titre_news LIKE '%?%'";
    public static final String sql_news_date="SELECT * FROM NEWS WHERE date_creation=?";
    public static final String sql_news_date_title="SELECT * FROM NEWS WHERE date_creation=? AND titre_news LIKE '%?%'";
    
    
    
    private static DAOFactory          daoFactory;

    public News_managers() {
        daoFactory=DAOFactory.getInstance();
    }
    
    
    
    
    public boolean add_news(News news) throws SQLException, java.lang.Exception{
        
        
         Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 try {
		       connexion = (Connection) daoFactory.getConnection();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		        				sql_add_news, false,news.getAssistant().getNumero_cni(),news.getTitre_news() ,news.getContenu_news() ,news.getStatut_news());
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
    
     public boolean add_suggestion(Suggestion suggestion) throws SQLException, java.lang.Exception{
        
        
         Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 try {
		       connexion = (Connection) daoFactory.getConnection();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		        				sql_add_suggestion, false,suggestion.getPseudo(),suggestion.getMessage(),suggestion.getStatut_suggestion());
		         
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
    
    public static void delete_news(int id){
          Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 try {
		       connexion = (Connection) daoFactory.getConnection();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		        				sql_delete_news, false,id);
		         preparedStatement.executeUpdate();
		             	
		        	
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		    	DAOUtilitaire.silentClosing( resultSet, preparedStatement, connexion );
		    }
    }
    
    public boolean update_news(News news){
        
         Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 try {
		       connexion = (Connection) daoFactory.getConnection();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
sql_update_news, false,news.getTitre_news() ,news.getContenu_news() ,news.getDate_creation() ,news.getStatut_news(),news.getId_news());
		         preparedStatement.executeUpdate();
		         
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
    
    public static News get_unique_news(int id){
        News news=new News();
        
       Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 try {
		       connexion = (Connection) daoFactory.getConnection();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		        				sql_get_unique_news, false,id);
		        resultSet=preparedStatement.executeQuery();
		       if(resultSet.next())  {
                         news=map_news(resultSet);
                       }   	
		        	
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		    	DAOUtilitaire.silentClosing( resultSet, preparedStatement, connexion );
		    }
        
        return news;
    }
    
    public static ArrayList<News> get_list_news(int nbre_news){
        ArrayList<News> liste_news=new ArrayList<News>();
        
        
        Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 try {
		       connexion = (Connection) daoFactory.getConnection();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		        				sql_get_list_news, false,nbre_news);
		        resultSet=preparedStatement.executeQuery();
		       while(resultSet.next())  {
                         
                         liste_news.add(map_news(resultSet));
                       }   	
		        	
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		    	DAOUtilitaire.silentClosing( resultSet, preparedStatement, connexion );
		    }
        //attente de code
        
        return liste_news;
    }
    
    public static ArrayList<Suggestion> get_list_suggestion(String statut){
        ArrayList<Suggestion> liste_suggestion=new ArrayList<Suggestion>();
        
        
        Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 try {
		       connexion = (Connection) daoFactory.getConnection();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		        				sql_get_list_suggestion, false,statut);
		        resultSet=preparedStatement.executeQuery();
		       while(resultSet.next())  {
                         
                         liste_suggestion.add(map_suggestion(resultSet));
                       }   	
		        	
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		    	DAOUtilitaire.silentClosing( resultSet, preparedStatement, connexion );
		    }
        //attente de code
        
        return liste_suggestion;
    }
    
    public static ArrayList<News> news_title(String titre_news){
        ArrayList<News> liste_news=new ArrayList<News>();
        
        
        Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 try {
		       connexion = (Connection) daoFactory.getConnection();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		        				sql_news_title, false,titre_news);
		        resultSet=preparedStatement.executeQuery();
		       while(resultSet.next())  {
                         
                         liste_news.add(map_news(resultSet));
                       }   	
		        	
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		    	DAOUtilitaire.silentClosing( resultSet, preparedStatement, connexion );
		    }
        //attente de code
        
        return liste_news;
        
    }
    
     public static ArrayList<News> news_date(String date_news){
        ArrayList<News> liste_news=new ArrayList<News>();
       
        
        Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 try {
		       connexion = (Connection) daoFactory.getConnection();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		        				sql_news_date, false,date_news);
		        resultSet=preparedStatement.executeQuery();
		       while(resultSet.next())  {
                        
                         liste_news.add(map_news(resultSet));
                       }   	
		        	
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		    	DAOUtilitaire.silentClosing( resultSet, preparedStatement, connexion );
		    }
        
        //attente de code
        
        return liste_news;
    }
     
     public static ArrayList<Suggestion> suggestion_date(String date_suggestion){
        ArrayList<Suggestion> liste_suggestion=new ArrayList<Suggestion>();
       
        
        Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 try {
		       connexion = (Connection) daoFactory.getConnection();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		        				sql_suggestion_date, false,date_suggestion);
		        resultSet=preparedStatement.executeQuery();
		       while(resultSet.next())  {
                        
                         liste_suggestion.add(map_suggestion(resultSet));
                       }   	
		        	
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		    	DAOUtilitaire.silentClosing( resultSet, preparedStatement, connexion );
		    }
        
        //attente de code
        
        return liste_suggestion;
    }
     
     public static ArrayList<News> news_date_title(String date_news,String titre_news){
        ArrayList<News> liste_news=new ArrayList<News>();
        
        
        Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 try {
		       connexion = (Connection) daoFactory.getConnection();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		        				sql_news_date_title, false,date_news,titre_news);
		        resultSet=preparedStatement.executeQuery();
		       while(resultSet.next())  {
                         
                         liste_news.add(map_news(resultSet));
                       }   	
		        	
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		    	DAOUtilitaire.silentClosing( resultSet, preparedStatement, connexion );
		    }
        
        //attente de code
        
        return liste_news;
    }
     
     public static News map_news( ResultSet resultSet) throws SQLException{
        
    	  News news=new News();
          
          news.setId_news(resultSet.getInt("id_news"));
          news.setStatut_news(resultSet.getString("statut_news"));   
          news.setTitre_news(resultSet.getString("titre_news"));
          news.setDate_creation(resultSet.getTimestamp("date_creation").toString());
          news.setContenu_news(resultSet.getString("contenu_news")); 
          
          news.setSous_contenu_news(news.get_sous_contenu(0, 40));
    	 
        
        return news;
     }
     
     
     public static Suggestion map_suggestion(ResultSet resultset) throws SQLException{
         Suggestion suggestion=new Suggestion();
        try{
         
         suggestion.setPseudo(resultset.getString("pseudo"));
         suggestion.setMessage(resultset.getString("message"));
         suggestion.setDate_suggestion(resultset.getTimestamp("date_suggestion").toString());
        
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        //attente de code
        
        return suggestion;
     }
}
