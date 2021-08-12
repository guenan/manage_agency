package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.News;
import Beans.Suggestion;
import Managers.News_managers;
import Objet_Metiers.OM_news;



public class Servlet_ajouter_news extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public final String vue_form_news_frontend="/vue_backend/index_news.jsp";
    public final String vue_form_news_backend="/vue_backend/index_news.jsp";
    public final String ATTR_RESULTAT_NEWS="attr_resultat_news";
   private String resultat_news="";
    
    public Servlet_ajouter_news() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom_action="action";
		String valeur_action="ajouter_news";
		
		request.setAttribute(nom_action, valeur_action);
		
		
		if(request.getAttribute("attr_destination").equals("frontend"))
		    this.getServletContext().getRequestDispatcher(vue_form_news_frontend).forward(request,response);
		else if(request.getAttribute("attr_destination").equals("backend"))
			this.getServletContext().getRequestDispatcher(vue_form_news_backend).forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom_action="action";
		String valeur_action="ajouter_news";
		
		OM_news om_news=new OM_news(request);
		News_managers news_managers=new News_managers();
		News news=om_news.make_news_to_add();
		
		
		if(news==null){
			
			resultat_news="remplissez tous vos champs";
		     request.setAttribute("attr_om_news", om_news);
		     
		    
		}else{
			try{
				
			if(news_managers.add_news(news)){		
				resultat_news="succes d'ajout de la news";
				
				request.setAttribute("news", news);
			
			}else{
				resultat_news="echec d'ajout de la news";
				
				request.setAttribute("news", news);
			
				
			}
			}catch(Exception e){
				request.setAttribute("error_bd", e.getMessage());
				e.printStackTrace();
			}
	}
	
	
	request.setAttribute(nom_action, valeur_action);	
	request.setAttribute(ATTR_RESULTAT_NEWS, resultat_news);
	if(request.getAttribute("attr_destination").equals("frontend"))
	    this.getServletContext().getRequestDispatcher(vue_form_news_frontend).forward(request,response);
	else if(request.getAttribute("attr_destination").equals("backend"))
		this.getServletContext().getRequestDispatcher(vue_form_news_backend).forward(request,response);
	
}
}
