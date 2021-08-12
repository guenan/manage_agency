package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Bus;
import Beans.Suggestion;
import Managers.Bus_managers;
import Managers.News_managers;
import Objet_Metiers.OM_bus;
import Objet_Metiers.OM_news;

public class Servlet_ajouter_suggestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public final String vue_form_suggestion_frontend="/index.jsp";
    public final String vue_form_suggestion_backend="/index.jsp";
    public final String ATTR_RESULTAT_SUGGESTION="attr_resultat_suggestion";
   private String resultat_suggestion="";
    
    public Servlet_ajouter_suggestion() {
        super();
       
    }

    
	
	public String getResultat_suggestion() {
		return resultat_suggestion;
	}



	public void setResultat_suggestion(String resultat_suggestion) {
		this.resultat_suggestion = resultat_suggestion;
	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getAttribute("attr_destination").equals("frontend"))
		    this.getServletContext().getRequestDispatcher(vue_form_suggestion_frontend).forward(request,response);
		else if(request.getAttribute("attr_destination").equals("backend"))
			this.getServletContext().getRequestDispatcher(vue_form_suggestion_backend).forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OM_news om_suggestion=new OM_news(request);
		News_managers suggestion_managers=new News_managers();
		
		Suggestion suggestion=om_suggestion.make_suggestion_to_add();
		
		
		if(suggestion==null){
			
			resultat_suggestion="remplissez tous vos champs";
		     request.setAttribute("attr_om_suggestion", om_suggestion);
		     
		    
		}else{
			try{
				
			if(suggestion_managers.add_suggestion(suggestion)){		
				resultat_suggestion="succes d'ajout de la suggestion";
				
				
			
			}else{
				resultat_suggestion="echec d'ajout de la suggestion";
				
				
			
				
			}
			}catch(Exception e){
				request.setAttribute("error_bd", e.getMessage());
				e.printStackTrace();
			}
		}
		
		request.setAttribute("suggestion", suggestion);
		
		request.setAttribute(ATTR_RESULTAT_SUGGESTION, resultat_suggestion);
		if(request.getAttribute("attr_destination").equals("frontend"))
		    this.getServletContext().getRequestDispatcher(vue_form_suggestion_frontend).forward(request,response);
		else if(request.getAttribute("attr_destination").equals("backend"))
			this.getServletContext().getRequestDispatcher(vue_form_suggestion_backend).forward(request,response);
		
	}

}
