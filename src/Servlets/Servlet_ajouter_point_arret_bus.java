package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Managers.Bus_Point_Arret_managers;
import Beans.Bus_Point_Arret;
import Objet_Metiers.OM_bus_point_arret;

/**
 * Servlet implementation class Servlet_ajouter_point_arret_bus
 */
@WebServlet("/Servlet_ajouter_point_arret_bus")
public class Servlet_ajouter_point_arret_bus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public final String vue_form_flotte_frontend="/vue_backend/index_flotte.jsp";
    public final String vue_form_flotte_backend="/vue_backend/index_flotte.jsp";     
    


    public Servlet_ajouter_point_arret_bus() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom_action="action";
		String valeur_action="index_flotte";
		
		request.setAttribute(nom_action, valeur_action);
		this.getServletContext().getRequestDispatcher(vue_form_flotte_backend).forward(request,response);

		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		String message_succes="";
		Bus_Point_Arret_managers bpam=new Bus_Point_Arret_managers();
		OM_bus_point_arret ombpa=new OM_bus_point_arret(request);
		
		
		Bus_Point_Arret bus_point_arret = new Bus_Point_Arret();
		
		
		bus_point_arret=ombpa.make_bus_point_arret();
		 if (bus_point_arret==null){
			 message_succes="veuillez remplir tous vos champs";
		 }
		
	else{
		try{
			
		if(bpam.add_bus_point_arret(bus_point_arret)){		
			message_succes="succes d'ajout du point d'arret";
			
		
		}
		else{
			message_succes="echec d'ajout du point d'arret";
			
		}
		}catch(Exception e){
			request.setAttribute("error_bd", e.getMessage());
		}
	}
		 request.setAttribute("message_succes", message_succes);
		 this.getServletContext().getRequestDispatcher(vue_form_flotte_backend).forward(request,response);

	}
	
		


}
		
	


