package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Managers.News_managers;
import Managers.Personnel_managers;
import Managers.Recette_managers;



public class Servlet_index_recette extends HttpServlet {
	private static final long serialVersionUID = 1L;
public static final String ATTR_LISTE_RECETTE_YESTERDAY="ATTR_LISTE_RECETTE_YESTERDAY" ;
public static final String ATTR_LISTE_RECETTE_NO_ARCHIVE="ATTR_LISTE_RECETTE_NO_ARCHIVE" ;
public static final String ATTR_LISTE_CHAUFFEUR="ATTR_LISTE_CHAUFFEUR" ;

	public final String vue1="/vue_backend/index_recette.jsp";
	public final String vue_form_recette_frontend="/vue_frontend/index_recette.jsp"; 
    public final String vue_form_recette_backend="/vue_backend/index_recette.jsp";     
    
    public Servlet_index_recette() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom_action="action";
		String valeur_action="index_recette";
		
		Recette_managers rm =new Recette_managers();
		Personnel_managers pm =new Personnel_managers();
		 String yesterday_date="";
	    	String step=request.getParameter("step");
	    	
	    	if(step == null){
	    		valeur_action="index_recette";
	    		request.setAttribute(ATTR_LISTE_RECETTE_YESTERDAY, rm.recette_yeterday(yesterday_date));
	    	}
	    	else if(step.equals("modifier1")){
	    		valeur_action="modifier1";
	    		request.setAttribute(ATTR_LISTE_CHAUFFEUR, pm.select_all_chauffeur());
	    	}
	    	
	    	
	    	
	    	else if(step.equals("modifier2")){
	    		valeur_action="modifier2";
	    			
	    	}
	    	
	    	
	    	request.setAttribute(nom_action, valeur_action);
	    	
	    	
	           //   request.setAttribute(ATTR_LISTE_NEWS_BACKEND, nm.get_list_news(10));
	         
		
		if(request.getAttribute("attr_destination").equals("frontend"))
		    this.getServletContext().getRequestDispatcher(vue_form_recette_frontend).forward(request,response);
		else if(request.getAttribute("attr_destination").equals("backend"))
			this.getServletContext().getRequestDispatcher(vue_form_recette_backend).forward(request,response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nom_action="action";
		String valeur_action="index_recette";
		
		Recette_managers rm =new Recette_managers();
		 String yesterday_date="";
	    	String step=request.getParameter("step");
	    	
	    	if(step == null){
	    		valeur_action="index_recette";
	    		request.setAttribute(ATTR_LISTE_RECETTE_YESTERDAY, rm.recette_yeterday(yesterday_date));
	    	}
	    	else if(step.equals("modifier2")){
	    		valeur_action="modifier2";
	    		String valeur_cni=request.getParameter("numero_cni");
		    	
		    	request.setAttribute(nom_action, valeur_action);
		    	request.setAttribute(ATTR_LISTE_RECETTE_NO_ARCHIVE, rm.recette_chauffeur_no_archive("no_archive", valeur_cni));
		    	
	    	}
	    	
	    	
	    	if(request.getAttribute("attr_destination").equals("frontend"))
			    this.getServletContext().getRequestDispatcher(vue_form_recette_frontend).forward(request,response);
			else if(request.getAttribute("attr_destination").equals("backend"))
				this.getServletContext().getRequestDispatcher(vue_form_recette_backend).forward(request,response);
			
	   
	   
	}

}
