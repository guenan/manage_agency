
package Managers;

import Beans.Recette;
import dao.DAOFactory;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import dao.DAOException;
import dao.DAOUtilitaire;
import java.sql.SQLException;
import Beans.Objet_Connection;
import Beans.Objet_Connection;



public class Recette_managers {
    
    //attente de requete sql
	public static final String sql_add_recette="INSERT INTO recettes(numero_cni, numero_cni_1, billet_10000, billet_5000, billet_2000, billet_1000, billet_500, piece_500, piece_100, piece_50, piece_25, piece_10, piece_5, date_enregistrement, statut_recette)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,NOW(),?)";
	public static final String sql_update_recette="UPDATE RECETTES SET CODE_LINE=?,NUMERO_CNI_C=? , NUMERO_CNI_A=? , BILLET_10000=? , BILLET_5000=? , BILLET_2000=? ,BILLET_1000= ?, BILLET_500=?, PIECE_500=?,PIECE_100=?,PIECE_50=?,PIECE_25=?,PIECE_10=?,PIECE_5=?,numero_cni_1=? WHERE ID_RECETTE=?";
    public static final String sql_recette_date="SELECT ID_RECETTE,BILLET_10000,BILLET_5000,BILLET_2000,BILLET_1000,BILLET_500,PIECE_500,PIECE_100,PIECE_50,PIECE_25,PIECE_10,PIECE_5,DATE_ENREGISTREMENT,C.NUMERO_CNI as cni_chauffeur,C.NOM_EMPLOYE as nom_chauffeur, C.PRENOM_EMPLOYE as prenom_chauffeur,A.NUMERO_CNI as cni_assistant, A.NOM_EMPLOYE as nom_assistant, A.PRENOM_EMPLOYE as prenom_assistant, L.CODE_LIGNE as code_ligne,L.POINT_DEPART as point_depart_ligne, L.POINT_ARRET as point_arret_ligne FROM RECETTES AS R,CHAUFFEUR AS C,LIGNE AS L,ASSISTANT AS A,BUS AS B WHERE C.NUMERO_CNI=R.NUMERO_CNI AND B.CODE_LIGNE=L.CODE_LIGNE AND C.IMMATRICULATION = B.IMMATRICULATION AND A.NUMERO_CNI=R.NUMERO_CNI_1 AND  to_char(DATE_ENREGISTREMENT,'YYYY-MM-DD')=?";
    public static final String sql_recette_ligne_date="SELECT ID_RECETTE,BILLET_10000,BILLET_5000,BILLET_2000,BILLET_1000,BILLET_500,PIECE_500,PIECE_100,PIECE_50,PIECE_25,PIECE_10,PIECE_5,DATE_ENREGISTREMENT,C.NUMERO_CNI as cni_chauffeur,C.NOM_EMPLOYE as nom_chauffeur, C.PRENOM_EMPLOYE as prenom_chauffeur,A.NUMERO_CNI as cni_assistant, A.NOM_EMPLOYE as nom_assistant, A.PRENOM_EMPLOYE as prenom_assistant, L.CODE_LIGNE as code_ligne,L.POINT_DEPART as point_depart_ligne, L.POINT_ARRET as point_arret_ligne FROM RECETTES AS R,CHAUFFEUR AS C,LIGNE AS L,ASSISTANT AS A,BUS AS B WHERE C.NUMERO_CNI=R.NUMERO_CNI AND B.CODE_LIGNE=L.CODE_LIGNE AND C.IMMATRICULATION = B.IMMATRICULATION AND A.NUMERO_CNI=R.NUMERO_CNI_1 AND  AND CODE_LIGNE=? AND to_char(DATE_ENREGISTREMENT,'YYYY-MM-DD')=?";    
    public static final String sql_recette_chauffeur_date="SELECT ID_RECETTE,BILLET_10000,BILLET_5000,BILLET_2000,BILLET_1000,BILLET_500,PIECE_500,PIECE_100,PIECE_50,PIECE_25,PIECE_10,PIECE_5,DATE_ENREGISTREMENT,STATUT_RECETTE,C.NUMERO_CNI as cni_chauffeur,C.NOM_EMPLOYE as nom_chauffeur, C.PRENOM_EMPLOYE as prenom_chauffeur,A.NUMERO_CNI as cni_assistant, A.NOM_EMPLOYE as nom_assistant, A.PRENOM_EMPLOYE as prenom_assistant, L.CODE_LIGNE as code_ligne,L.POINT_DEPART as point_depart_ligne, L.POINT_ARRET as point_arret_ligne FROM RECETTES AS R,CHAUFFEUR AS C,LIGNE AS L,ASSISTANT AS A,BUS AS B WHERE C.NUMERO_CNI=R.NUMERO_CNI AND B.CODE_LIGNE=L.CODE_LIGNE AND C.IMMATRICULATION = B.IMMATRICULATION AND A.NUMERO_CNI=R.NUMERO_CNI_1 AND  AND CNI_CHAUFFEUR=? AND to_char(DATE_ENREGISTREMENT,'YYYY-MM-DD')=?";    
    
    public static final String sql_recette_chauffeur_no_achirve="SELECT ID_RECETTE,BILLET_10000,BILLET_5000,BILLET_2000,BILLET_1000,BILLET_500,PIECE_500,PIECE_100,PIECE_50,PIECE_25,PIECE_10,PIECE_5,DATE_ENREGISTREMENT,STATUT_RECETTE,C.NUMERO_CNI as cni_chauffeur,C.NOM_EMPLOYE as nom_chauffeur, C.PRENOM_EMPLOYE as prenom_chauffeur,A.NUMERO_CNI as cni_assistant, A.NOM_EMPLOYE as nom_assistant, A.PRENOM_EMPLOYE as prenom_assistant, L.CODE_LIGNE as code_ligne,L.POINT_DEPART as point_depart_ligne, L.POINT_ARRET as point_arret_ligne FROM RECETTES AS R,CHAUFFEUR AS C,LIGNE AS L,ASSISTANT AS A,BUS AS B WHERE C.NUMERO_CNI=R.NUMERO_CNI AND B.CODE_LIGNE=L.CODE_LIGNE AND C.IMMATRICULATION = B.IMMATRICULATION AND A.NUMERO_CNI=R.NUMERO_CNI_1 AND  AND STATUT_RECETTE=? AND cni_chauffeur=?";    
    public static final String sql_affiche_unique_recette="SELECT ID_RECETTE,BILLET_10000,BILLET_5000,BILLET_2000,BILLET_1000,BILLET_500,PIECE_500,PIECE_100,PIECE_50,PIECE_25,PIECE_10,PIECE_5,DATE_ENREGISTREMENT,C.NUMERO_CNI as cni_chauffeur,C.NOM_EMPLOYE as nom_chauffeur, C.PRENOM_EMPLOYE as prenom_chauffeur,A.NUMERO_CNI as cni_assistant, A.NOM_EMPLOYE as nom_assistant, A.PRENOM_EMPLOYE as prenom_assistant, L.CODE_LIGNE as code_ligne,L.POINT_DEPART as point_depart_ligne, L.POINT_ARRET as point_arret_ligne FROM RECETTES AS R,CHAUFFEUR AS C,LIGNE AS L,ASSISTANT AS A,BUS AS B WHERE C.NUMERO_CNI=R.NUMERO_CNI AND B.CODE_LIGNE=L.CODE_LIGNE AND C.IMMATRICULATION = B.IMMATRICULATION AND A.NUMERO_CNI=R.NUMERO_CNI_1 AND  ID_RECETTE=?";
    
    public static final String sql_yesterday_recette="SELECT ID_RECETTE,BILLET_10000,BILLET_5000,BILLET_2000,BILLET_1000,BILLET_500,PIECE_500,PIECE_100,PIECE_50,PIECE_25,PIECE_10,PIECE_5,DATE_ENREGISTREMENT,C.NUMERO_CNI as cni_chauffeur,C.NOM_EMPLOYE as nom_chauffeur, C.PRENOM_EMPLOYE as prenom_chauffeur,A.NUMERO_CNI as cni_assistant, A.NOM_EMPLOYE as nom_assistant, A.PRENOM_EMPLOYE as prenom_assistant, L.CODE_LIGNE as code_ligne,L.POINT_DEPART as point_depart_ligne, L.POINT_ARRET as point_arret_ligne FROM RECETTES AS R,CHAUFFEUR AS C,LIGNE AS L,ASSISTANT AS A,BUS AS B WHERE C.NUMERO_CNI=R.NUMERO_CNI AND B.CODE_LIGNE=L.CODE_LIGNE AND C.IMMATRICULATION = B.IMMATRICULATION AND A.NUMERO_CNI=R.NUMERO_CNI_1 AND  to_char(DATE_ENREGISTREMENT,'YYYY-MM-DD')=?";
    public static final String sql_yesterday_date="SELECT current_date -1";
    public static final String sql_rechercher_recette="SELECT ID_RECETTE,BILLET_10000,BILLET_5000,BILLET_2000,BILLET_1000,BILLET_500,PIECE_500,PIECE_100,PIECE_50,PIECE_25,PIECE_10,PIECE_5,DATE_ENREGISTREMENT,C.NOM_EMPLOYE as nom_chauffeur, C.PRENOM_EMPLOYE as prenom_chauffeur FROM RECETTES AS R,CHAUFFEUR AS C WHERE R.STATUT_RECETTE='no_archive' AND C.NUMERO_CNI=R.NUMERO_CNI AND   DATE_ENREGISTREMENT=? ORDER BY id_recette";
    public static final String sql_archiver_recette="UPDATE RECETTES SET STATUT_RECETTE='archive' WHERE id_recette=? ";
    
    private static DAOFactory          daoFactory;

    public void Recette_managers() {
        daoFactory=DAOFactory.getInstance();
    }
    
    
    public boolean archiver_recette(int id_recette ){
    	
    	Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 try {
		       connexion = (Connection) daoFactory.getConnection();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		    		   sql_archiver_recette,false,id_recette);
		      int value=   preparedStatement.executeUpdate();
		      if (value==1){
		    	  return true;
		      }    	
		      else return false;
		        	
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		    	DAOUtilitaire.silentClosing( resultSet, preparedStatement, connexion );
		    }
       
    }
   
    
    
    
    public ArrayList<Recette> search_recette(Timestamp date_enregistrement){
    	ArrayList<Recette> liste_recette=new ArrayList<Recette>();
    
    	PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 Objet_Connection instance =daoFactory.getInformation();
		 
		 try {
		      // connexion = (Connection) daoFactory.getConnection();
			 Connection connexion=	 DriverManager.getConnection( instance.getUrl(), instance.getIdentifiant(), instance.getPassword() );
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		        				sql_add_recette,false,date_enregistrement);
		        resultSet=preparedStatement.executeQuery(); 
		        while (resultSet.next()){
		        	Recette recette=new Recette();
		        	recette.setId_recette(resultSet.getInt("id_recette"));
		        	recette.setBillet_1000(resultSet.getInt("billet_1000"));
		        	recette.setBillet_10000(resultSet.getInt("billet_10000"));
		        	recette.setBillet_2000(resultSet.getInt("billet_2000"));
		        	recette.setBillet_500(resultSet.getInt("billet_500"));
		        	recette.setBillet_5000(resultSet.getInt("billet_5000"));
		        	recette.setPiece_10(resultSet.getInt("piece_10"));
		        	recette.setPiece_100(resultSet.getInt("piece_100"));
		        	recette.setPiece_25(resultSet.getInt("piece_25"));
		        	recette.setPiece_5(resultSet.getInt("piece_5"));
		        	recette.setPiece_50(resultSet.getInt("piece_50"));
		        	recette.setPiece_500(resultSet.getInt("piece_500"));
		        	recette.getChauffeur().setNom_employe(resultSet.getString("nom_chauffeur"));
		        	liste_recette.add(recette);
		        }
		        	
		        return liste_recette;
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    }
    
    
    }
  
    
    
    
    public boolean add_recette(Recette recette){
        
        
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 Objet_Connection instance =daoFactory.getInformation();
		 
		 try {
		      // connexion = (Connection) daoFactory.getConnection();
			 Connection connexion=	 DriverManager.getConnection( instance.getUrl(), instance.getIdentifiant(), instance.getPassword() );
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		        				sql_add_recette,false,recette.getChauffeur().getNumero_cni(),recette.getAssistant().getNumero_cni(),recette.getBillet_10000(),recette.getBillet_5000(),recette.getBillet_2000(),recette.getBillet_1000(),recette.getBillet_500(),recette.getPiece_500(),recette.getPiece_100(),recette.getPiece_50(),recette.getPiece_25(),recette.getPiece_10(),recette.getPiece_5(),recette.getStatut_recette());
		       if (preparedStatement.executeUpdate()==1) 
		    	   return true;
		       else 
		    	   return false;
		             	
		        	
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } 
        
    }
    
    public int update_recette(Recette recette){
        
        Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 try {
		       connexion = (Connection) daoFactory.getConnection();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		        				sql_update_recette,false,recette.getLigne().getCode_ligne(),recette.getChauffeur().getNumero_cni(),recette.getAssistant().getNumero_cni(),recette.getBillet_10000(),recette.getBillet_5000(),recette.getBillet_2000(),recette.getBillet_1000(),recette.getBillet_500(),recette.getPiece_500(),recette.getPiece_100(),recette.getPiece_50(),recette.getPiece_25(),recette.getPiece_10(),recette.getPiece_5(),recette.getAssistant().getNumero_cni(),recette.getId_recette());
		      int value=   preparedStatement.executeUpdate();
		         return value;    	
		        	
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		    	DAOUtilitaire.silentClosing( resultSet, preparedStatement, connexion );
		    }
        
    }
    
    public static ArrayList<Recette> recette_date(String date_news){
        ArrayList<Recette> liste_recettes=new ArrayList<Recette>();
        
        
         Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 try {
		       connexion = (Connection) daoFactory.getConnection();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		        				sql_recette_date,false,date_news);
		        resultSet=preparedStatement.executeQuery();
		         
                        while(resultSet.next()){
                            liste_recettes.add(map_recette(resultSet));
                        }
                        
		        	
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		    	DAOUtilitaire.silentClosing( resultSet, preparedStatement, connexion );
		    }
        
        //attente de code
        
        return liste_recettes;
    }
    
    public static Recette unique_recette(String id_news){
        Recette recette=new Recette();
        
        
         Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 try {
		       connexion = (Connection) daoFactory.getConnection();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		    		   sql_affiche_unique_recette,false,id_news);
		        resultSet=preparedStatement.executeQuery();
		         
                        if(resultSet.next()){
                        	recette=map_recette(resultSet);
                        }
                        
		        	
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		    	DAOUtilitaire.silentClosing( resultSet, preparedStatement, connexion );
		    }
        
        //attente de code
        
        return recette;
    }
    
    public static ArrayList<Recette> recette_yeterday(String date_news){
        ArrayList<Recette> liste_recettes=new ArrayList<Recette>();
        
        
        
		 PreparedStatement preparedStatement = null;
		 PreparedStatement preparedStatement1 = null;
		 ResultSet resultSet = null;
		 ResultSet resultSet1 = null;
		 Objet_Connection instance=DAOFactory.getInformation();
		 
		 try {
			 Connection connexion1=	 DriverManager.getConnection( instance.getUrl(), instance.getIdentifiant(), instance.getPassword() );
			// Connection connexion1 = daoFactory.getConnection();
		       preparedStatement1 = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion1,sql_yesterday_date,false);
		       resultSet1=preparedStatement1.executeQuery();   
		       if(resultSet1.next()){
		    	   date_news=resultSet1.getDate(1).toString();
		       }
		       
		       
		       
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion1,
		        				sql_yesterday_recette,false,date_news);
		        resultSet=preparedStatement.executeQuery();
		         
                        while(resultSet.next()){
                            liste_recettes.add(map_recette(resultSet));
                        }
                        
		        	
		    } catch ( SQLException e ) {
		    	
		    	e.printStackTrace();
		    	
		        //throw new DAOException( e );
		    } finally {
		    //	DAOUtilitaire.silentClosing( resultSet, preparedStatement, connexion );
		    }
        
        //attente de code
        
        return liste_recettes;
    }
    
    public static ArrayList<Recette> recette_ligne_date(String code_ligne,String date){
        ArrayList<Recette> liste_recettes=new ArrayList<Recette>();        
        
         Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 try {
		       connexion = (Connection) daoFactory.getConnection();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		        				sql_recette_ligne_date,false,code_ligne,date);
		        resultSet=preparedStatement.executeQuery();
		         
                        while(resultSet.next()){
                            liste_recettes.add(map_recette(resultSet));
                        }
                        
		        	
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		    	DAOUtilitaire.silentClosing( resultSet, preparedStatement, connexion );
		    }
        
        //attente de code
        
        return liste_recettes;
    }
    
    public static ArrayList<Recette> recette_chauffeur_date(String cni_chauffeur,String date){
        ArrayList<Recette> liste_recettes=new ArrayList<Recette>();
        
        Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 try {
		       connexion = (Connection) daoFactory.getConnection();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		        				sql_recette_chauffeur_date,false,cni_chauffeur,date);
		        resultSet=preparedStatement.executeQuery();
		         
                        while(resultSet.next()){
                            liste_recettes.add(map_recette(resultSet));
                        }
                        
		        	
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		    	DAOUtilitaire.silentClosing( resultSet, preparedStatement, connexion );
		    }
        
        //attente de code
        
        return liste_recettes;
    }
    
    
    
    public static ArrayList<Recette> recette_chauffeur_no_archive(String statut_recette,String cni){
        ArrayList<Recette> liste_recettes=new ArrayList<Recette>();
        
        Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 
		 try {
		       connexion = (Connection) daoFactory.getConnection();
		       preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion,
		    		   sql_recette_chauffeur_no_achirve ,false,statut_recette,cni);
		        resultSet=preparedStatement.executeQuery();
		         
                        while(resultSet.next()){
                            liste_recettes.add(map_recette(resultSet));
                        }
                        
		        	
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		    	DAOUtilitaire.silentClosing( resultSet, preparedStatement, connexion );
		    }
        
        //attente de code
        
        return liste_recettes;
    }
    
    public static Recette map_recette(ResultSet resultset) throws SQLException{
        Recette recette=new Recette();
          
        try{
         recette.setId_recette(resultset.getInt("id_recette"));
         recette.setBillet_10000(resultset.getInt("BILLET_10000"));
         recette.setBillet_5000(resultset.getInt("BILLET_5000"));
         recette.setBillet_2000(resultset.getInt("BILLET_2000"));               
         recette.setBillet_1000(resultset.getInt("BILLET_1000"));
         recette.setBillet_500(resultset.getInt("BILLET_500"));
         recette.setPiece_500(resultset.getInt("PIECE_500"));                       
         recette.setPiece_100(resultset.getInt("PIECE_100"));
         recette.setPiece_50(resultset.getInt("PIECE_50"));
         recette.setPiece_25(resultset.getInt("PIECE_25"));               
         recette.setPiece_10(resultset.getInt("PIECE_10"));
         recette.setPiece_5(resultset.getInt("PIECE_5"));
         recette.setDate_enregistrement(resultset.getTimestamp("DATE_ENREGISTREMENT").toString());
                 
         recette.getChauffeur().setNumero_cni(resultset.getInt("cni_chauffeur"));
         recette.getChauffeur().setNom_employe(resultset.getString("nom_chauffeur"));
         recette.getChauffeur().setPrenom_employe(resultset.getString("prenom_chauffeur"));         
                 
         recette.getAssistant().setNumero_cni(resultset.getInt("cni_chauffeur"));
         recette.getAssistant().setNom_employe(resultset.getString("nom_chauffeur"));
         recette.getAssistant().setPrenom_employe(resultset.getString("prenom_chauffeur"));
         
         recette.getLigne().setCode_ligne(resultset.getString("code_ligne"));
         recette.getLigne().getPoint_depart().setCode_point_arret(resultset.getString("point_depart_ligne"));
         recette.getLigne().getPoint_arret().setCode_point_arret(resultset.getString("point_arret_ligne"));
        }
        catch(SQLException e){
            
        }
        
        
        
        //attente de code
       
       return recette;
    }
    
}
