
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Beans.Employe;
import Managers.Brigade_managers;
import Managers.Bus_managers;
import Managers.Garage_managers;
import Managers.Ligne_managers;
import Managers.Personnel_managers;


public class Servlet_index_personnel extends HttpServlet {
  
  
  private final String USER_SESSION="user_session";  
  private static final String param_rechercher="param_rechercher"; 
  
  
  private static final String ATTR_LISTE_LIGNE="ATTR_LISTE_LIGNE"; 
  private static final String ATTR_LISTE_BRIGADE="ATTR_LISTE_BRIGADE";
  private static final String ATTR_LISTE_GARAGE="ATTR_LISTE_GARAGE";
  private static final String ATTR_LISTE_BUS="ATTR_LISTE_BUS";
  private static final String ATTR_LISTE_POINT_ARRET="ATTR_LISTE_POINT_ARRET";
  
  public static final String ATTR_LISTE_CHAUFFEUR="ATTR_LISTE_CHAUFFEUR" ; 
	public static final String ATTR_LISTE_GARAGISTE="ATTR_LISTE_GARAGISTE" ; 
	public static final String ATTR_LISTE_CONTROLLEUR="ATTR_LISTE_CONTROLLEUR" ; 
	public static final String ATTR_LISTE_ASSISTANT="ATTR_LISTE_ASSISTANT" ; 
	public static final String ATTR_LISTE_CHEF_AGENCE="ATTR_LISTE_CHEF_AGENCE" ; 
	public static final String ATTR_ERREUR_AFFICHAGE="ATTR_LISTE_CHEF_AGENCE" ;
	
	public static final String VUE_ERROR_AFFICHER_PERSONNEL_BACKEND        = "/Ressources/errors/erro.jsp";
	
	private static final String vue_authentification_employe_backend="/vue_backend/index_personnel.jsp";

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	HttpSession session=request.getSession();
    	
    	Ligne_managers ligne_managers=new Ligne_managers();
    	Brigade_managers brigade_managers=new Brigade_managers();
    	Garage_managers garage_managers=new Garage_managers();
    	Bus_managers bus_managers=new Bus_managers();
    	
    	session.setAttribute(ATTR_LISTE_LIGNE, ligne_managers.select_all_ligne());
    	session.setAttribute(ATTR_LISTE_BRIGADE, brigade_managers.select_all_brigade());
    	session.setAttribute(ATTR_LISTE_GARAGE, garage_managers.select_all_garage());
    	session.setAttribute(ATTR_LISTE_BUS, bus_managers.select_all_bus());
      
    	
      String ATTR_action=(String) request.getAttribute("ATTR_action");
      String PARAM_rechercher=(String)request.getParameter(param_rechercher);
    	
        Employe employe=(Employe)session.getAttribute(USER_SESSION);
        Personnel_managers pm=new Personnel_managers();
        
        if(PARAM_rechercher!= null && PARAM_rechercher.equals(param_rechercher)){
       
        	request.setAttribute("action","rechercher");
        
        }else{
        	request.setAttribute("action",ATTR_action );
        }
        
        
        
        if (employe.isChef_agence()){
        
        
        	request.setAttribute(ATTR_LISTE_CHAUFFEUR, pm.select_all_chauffeur());
        	request.setAttribute(ATTR_LISTE_GARAGISTE, pm.select_all_GARAGISTE());
        	request.setAttribute(ATTR_LISTE_CONTROLLEUR, pm.select_all_controlleur());
        	request.setAttribute(ATTR_LISTE_ASSISTANT, pm.select_all_assistant());
        	request.setAttribute(ATTR_LISTE_CHEF_AGENCE, pm.select_all_chef_agence());
        	
        	
        	
        	this.getServletContext().getRequestDispatcher( vue_authentification_employe_backend).forward( request, response );
      }else{
      	request.setAttribute(ATTR_ERREUR_AFFICHAGE, "vous n'avez pas les droits requis pour effectuer cette operation");
       	this.getServletContext().getRequestDispatcher( VUE_ERROR_AFFICHER_PERSONNEL_BACKEND).forward( request, response );
  	      
       }
    	
      
      
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

 
}
