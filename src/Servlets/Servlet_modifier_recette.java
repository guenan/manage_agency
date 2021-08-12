package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Employe;
import Beans.Recette;
import Managers.Recette_managers;
import Objet_Metiers.OM_recette;


public class Servlet_modifier_recette extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATTR_RECETTE="ATTR_RECETTE";
	

	public final String vue1="/vue_backend/index_recette.jsp";
	public final String vue_modifier_recette_frontend="/vue_frontend/index_recette.jsp"; 
	public final String vue_modifier_recette_backend="/vue_backend/index_recette.jsp";     
	Boolean result1=true; 
    String message=""; 
   
    public Servlet_modifier_recette() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nom_action="action";
		String valeur_action="modifier_recette";
		String id_recette=request.getParameter("id_recette");
		Recette_managers rm =new Recette_managers();
		
		if(id_recette != null){
		
		request.setAttribute(ATTR_RECETTE, rm.unique_recette(id_recette));
	
		
   
		}else{
			message="une erreur innatendue s'est produite";
			
		}
		
		if(request.getAttribute("attr_destination").equals("frontend"))
		    this.getServletContext().getRequestDispatcher(vue_modifier_recette_frontend).forward(request,response);
		else if(request.getAttribute("attr_destination").equals("backend"))
			this.getServletContext().getRequestDispatcher(vue_modifier_recette_backend).forward(request,response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nom_action="action";
		String valeur_action="modifier2";
		int id_recette= Integer.parseInt( request.getParameter("id_recette"));
		
		Recette_managers rm =new Recette_managers();
		OM_recette omr =new OM_recette(request);
		
		Recette recette=omr.make_recette_to_update();
		recette.setId_recette(id_recette);
		
		
		int result=rm.update_recette(recette);
		
		if(result == 1){
			message="mise à  jour effectuée avec succes";
			result1=true;
			
		}
		else{
			message="echec de la mise à jour";
			result1=false;
		}
   
		response.setContentType("text/xml");
	      response.setHeader("Cache-Control", "no-cache");
	      response.getWriter().write("<response>" +
	      								"<message>"+message+"</message>" +
	      								"<statut>"+result1+"</statut>" +	
	      							 "</response>");
		
	}

}
