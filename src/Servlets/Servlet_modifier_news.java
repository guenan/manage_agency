package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import Beans.News;
import Managers.News_managers;
import Objet_Metiers.OM_news;

/**
 * Servlet implementation class Servlet_modifier_news
 */
@WebServlet("/Servlet_modifier_news")
public class Servlet_modifier_news extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	public final String vue1="/vue_backend/index_news.jsp";
	public final String vue_modifier_news_frontend="/vue_frontend/index.jsp"; 
	public final String vue_modifier_news_backend="/vue_backend/index_news.jsp";  
	
	
	String message="";
	
	
    public Servlet_modifier_news() {
        super();
       
    }

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			boolean succes_recuperation=true;
			int id_news=Integer.parseInt(request.getParameter("id_news"));
			News_managers nm =new News_managers();
			OM_news omn=new OM_news(request);
			News news=new News();
			news=nm.get_unique_news(id_news);
			
			if (news==null){
				succes_recuperation=false;
				response.setContentType("text/xml");
			      response.setHeader("Cache-Control", "no-cache");
			      response.getWriter().write("<response>" +
			      								"<statut>"+succes_recuperation+"</statut>" +	      								
			      							 "</response>");
				
			}
			
			else{
				
				response.setContentType("text/xml");
			      response.setHeader("Cache-Control", "no-cache");
			      response.getWriter().write("<response>" +
			      							"<id_news>"+news.getId_news()+"</id_news>" +
			      							"<titre_news>"+news.getTitre_news()+"</titre_news>" +
			      							"<contenu_news>"+news.getContenu_news()+"</contenu_news>" +
			      							 "</response>");
				
			}
						
			
			
			
			
			
	}

	
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String nom_action="action";
			String valeur_action="modifier_news";
			
			
			int id_news=Integer.parseInt(request.getParameter("id_news"));
			String titre_news=request.getParameter("titre_news");
			String contenu_news=request.getParameter("contenu_news");
			
			News_managers nm =new News_managers();
			OM_news omn=new OM_news(request);
			
			News news=omn.make_news_to_update();
			news.setId_news(id_news);
			news.setTitre_news(titre_news);
			news.setContenu_news(contenu_news);
			
			boolean result=nm.update_news(news);
			
			if(result){
			message="Mise à jour réussie";	
				}
			else{
				message="echec de la mise à jour";
				}
	}

}
