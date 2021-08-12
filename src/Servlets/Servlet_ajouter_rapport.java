package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Beans.Bus;
import Beans.Controlleur;
import Beans.Rapport;
import Managers.Bus_managers;
import Managers.Rapport_managers;
import Objet_Metiers.OM_bus;
import Objet_Metiers.OM_rapport;



public class Servlet_ajouter_rapport extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public final String vue_form_rapport_frontend="/vue_backend/ajouter_rapport.jsp";
    public final String vue_form_rapport_backend="/vue_backend/ajouter_brapport.jsp";
    public final String vue_form_connexion_backend="/vue_backend/connexion.jsp";
    public final String ATTR_RESULTAT_RAPPORT="attr_resultat_rapport";
   private String resultat_rapport="";   
    
    public Servlet_ajouter_rapport() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
	     Controlleur controlleur=(Controlleur) session.getAttribute("user_session");
	     
	 if(controlleur != null && controlleur.isControlleur()){
		
		if(request.getAttribute("attr_destination").equals("frontend"))
		    this.getServletContext().getRequestDispatcher(vue_form_rapport_frontend).forward(request,response);
		else if(request.getAttribute("attr_destination").equals("backend"))
			this.getServletContext().getRequestDispatcher(vue_form_rapport_backend).forward(request,response);
		
	 }else{
		 request.setAttribute("message", "connectez vous en tant que controlleur");
		 this.getServletContext().getRequestDispatcher(vue_form_connexion_backend).forward(request,response);
			
	 }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OM_rapport om_rapport=new OM_rapport(request);
		Rapport_managers rapport_managers=new Rapport_managers();
		Rapport rapport=om_rapport.make_Rapport();
		HttpSession session=request.getSession();
	     Controlleur controlleur=(Controlleur) session.getAttribute("user_session");
	     

	if(controlleur != null && controlleur.isControlleur()){
		
		if(rapport==null){
			
			resultat_rapport="remplissez tous vos champs";
		     request.setAttribute("attr_om_bus", om_rapport);
		     
		    
		}else{
			try{
				
			if(rapport_managers.add_rapport(rapport)){		
				resultat_rapport="succes d'ajout du rapport";
				
				request.setAttribute("rapport", rapport);
			
			}else{
				resultat_rapport="echec d'ajout du rapport";
				
				request.setAttribute("rapport", rapport);
			
				
			}
			}catch(Exception e){
				request.setAttribute("error_bd", e.getMessage());
			}
		}
		
		request.setAttribute(ATTR_RESULTAT_RAPPORT, resultat_rapport);
		if(request.getAttribute("attr_destination").equals("frontend"))
		    this.getServletContext().getRequestDispatcher(vue_form_rapport_frontend).forward(request,response);
		else if(request.getAttribute("attr_destination").equals("backend"))
			this.getServletContext().getRequestDispatcher(vue_form_rapport_backend).forward(request,response);
	}
	else{
		 request.setAttribute("message", "connectez vous en tant que controlleur");
		 this.getServletContext().getRequestDispatcher(vue_form_connexion_backend).forward(request,response);
			
	 }
	}

	}


