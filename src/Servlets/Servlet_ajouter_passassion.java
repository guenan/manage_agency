package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Garage;
import Beans.Passassion;
import Managers.Garage_managers;
import Managers.Passassion_managers;
import Objet_Metiers.OM_garage;
import Objet_Metiers.OM_passassion;


@WebServlet("/Servlet_ajouter_passassion")
public class Servlet_ajouter_passassion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public final String vue_form_passassion_frontend="/vue_backend/ajouter_passassion.jsp";
    public final String vue_form_passassion_backend="/vue_backend/ajouter_passassion.jsp";
    public final String ATTR_RESULTAT_PASSASSION="attr_resultat_passassion";
   private String resultat_passassion="";     
    
    public Servlet_ajouter_passassion() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getAttribute("attr_destination").equals("frontend"))
		    this.getServletContext().getRequestDispatcher(vue_form_passassion_frontend).forward(request,response);
		else if(request.getAttribute("attr_destination").equals("backend"))
			this.getServletContext().getRequestDispatcher(vue_form_passassion_backend).forward(request,response);

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		OM_passassion om_passassion=new OM_passassion(request);
		Passassion_managers passassion_managers=new Passassion_managers();
		Passassion passassion=om_passassion.make_passassion_to_add();
		
		
		if(passassion==null){
			
			resultat_passassion="remplissez tous vos champs";
		     request.setAttribute("attr_om_passassion", om_passassion);
		     
		    
		}else{
			try{
				
			if(passassion_managers.add_passassion(passassion)){		
				resultat_passassion="succes d'ajout de la passassion";
				
				request.setAttribute("passassion", passassion);
			
			}else{
				resultat_passassion="echec d'ajout de la passassion";
				
				request.setAttribute("passassion", passassion);
			
				
			}
			}catch(Exception e){
				request.setAttribute("error_bd", e.getMessage());
				e.printStackTrace();
			}
	}
	
	
	request.setAttribute(ATTR_RESULTAT_PASSASSION, resultat_passassion);
	if(request.getAttribute("attr_destination").equals("frontend"))
	    this.getServletContext().getRequestDispatcher(vue_form_passassion_frontend).forward(request,response);
	else if(request.getAttribute("attr_destination").equals("backend"))
		this.getServletContext().getRequestDispatcher(vue_form_passassion_backend).forward(request,response);
	
	}

}
