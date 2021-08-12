package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Recette;
import Beans.Suggestion;
import Managers.News_managers;
import Managers.Recette_managers;
import Objet_Metiers.OM_news;
import Objet_Metiers.OM_recette;


public class Servlet_ajouter_recette extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 public final String vue_form_recette_frontend="/vue_backend/index_recette.jsp";
	    public final String vue_form_recette_backend="/vue_backend/index_recette.jsp";
	    public final String ATTR_RESULTAT_RECETTE="attr_resultat_recette";
	   private  String resultat_recette="";
	   
    
    public Servlet_ajouter_recette() {
        super();
        
    }

    
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom_action="action";
		String valeur_action="ajouter_recette";
		request.setAttribute(nom_action, valeur_action);
		
		if(request.getAttribute("attr_destination").equals("frontend"))
		    this.getServletContext().getRequestDispatcher(vue_form_recette_frontend).forward(request,response);
		else if(request.getAttribute("attr_destination").equals("backend"))
			this.getServletContext().getRequestDispatcher(vue_form_recette_backend).forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom_action="action";
		String valeur_action="ajouter_recette";
		
		
		OM_recette om_recette=new OM_recette(request);
		Recette_managers recette_managers=new Recette_managers();
		Recette recette=om_recette.make_recette_to_add();
		
		
		if(recette==null){
			
			resultat_recette="remplissez tous vos champs";
		     request.setAttribute("attr_om_suggestion", om_recette);
		     System.out.println(resultat_recette);
		     
		    
		}else{
			
				
			if(recette_managers.add_recette(recette)){		
				resultat_recette="succes d'ajout de la recette";
				
				request.setAttribute("recette", recette);
				System.out.println(resultat_recette);
			
			}else{
				resultat_recette="echec d'ajout de la recette";
				
				request.setAttribute("recette", recette);
				System.out.println(resultat_recette);
				
			}
			
			}
		
		
		request.setAttribute(nom_action, valeur_action);
		request.setAttribute(ATTR_RESULTAT_RECETTE, resultat_recette);
		if(request.getAttribute("attr_destination").equals("frontend"))
		    this.getServletContext().getRequestDispatcher(vue_form_recette_frontend).forward(request,response);
		else if(request.getAttribute("attr_destination").equals("backend"))
			this.getServletContext().getRequestDispatcher("/vue_backend/index_personnel.jsp").forward(request,response);
		
	}

}
