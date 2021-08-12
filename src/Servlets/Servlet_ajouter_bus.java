package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Bus;
import Managers.Brigade_managers;
import Managers.Bus_managers;
import Managers.Garage_managers;
import Managers.Ligne_managers;
import Managers.Point_Arret_managers;
import Objet_Metiers.OM_bus;



public class Servlet_ajouter_bus extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public final String vue_form_bus_frontend="/vue_backend/index_flotte.jsp";
    public final String vue_form_bus_backend="/vue_backend/index_flotte.jsp";
    public final String ATTR_RESULTAT_BUS="attr_resultat_bus";
   private String resultat_bus="";
   private static final String ATTR_LISTE_LIGNE="ATTR_LISTE_LIGNE"; 
   private static final String ATTR_LISTE_BRIGADE="ATTR_LISTE_BRIGADE";
   private static final String ATTR_LISTE_GARAGE="ATTR_LISTE_GARAGE";
   private static final String ATTR_LISTE_BUS="ATTR_LISTE_BUS";
   private static final String ATTR_LISTE_POINT_ARRET="ATTR_LISTE_POINT_ARRET";
   
   
    public String getResultat_bus() {
	return resultat_bus;
}


public void setResultat_bus(String resultat_bus) {
	this.resultat_bus = resultat_bus;
}


	public Servlet_ajouter_bus() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("action", "index_flotte");
		
		if(request.getAttribute("attr_destination").equals("frontend"))
		    this.getServletContext().getRequestDispatcher(vue_form_bus_frontend).forward(request,response);
		else if(request.getAttribute("attr_destination").equals("backend"))
			this.getServletContext().getRequestDispatcher(vue_form_bus_backend).forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		OM_bus om_bus=new OM_bus(request);
		Bus_managers bus_managers=new Bus_managers();
		Bus bus=om_bus.make_bus();
		
		
		if(bus==null){
			
			resultat_bus="remplissez tous vos champs";
		     request.setAttribute("attr_om_bus", om_bus);
		     
		    
		}else{
			try{
				
			if(bus_managers.add_bus(bus)){		
				resultat_bus="succes d'ajout du bus";
				
				request.setAttribute("bus", bus);
			
			}else{
				resultat_bus="echec d'ajout du bus";
				
				request.setAttribute("bus", bus);
			
				
			}
			}catch(Exception e){
				request.setAttribute("error_bd", e.getMessage());
			}
		}
		request.setAttribute("action", "index_flotte");
		request.setAttribute("rel", resultat_bus);
		
		Bus_managers bm=new Bus_managers();
		Ligne_managers lm=new Ligne_managers();
		Brigade_managers brig =new Brigade_managers();
		Point_Arret_managers pam=new Point_Arret_managers();
		Garage_managers gm=new Garage_managers();
		
		request.getSession().setAttribute(ATTR_LISTE_LIGNE, lm.select_all_ligne());
		request.setAttribute(ATTR_LISTE_BRIGADE, brig.select_all_brigade());
		request.setAttribute(ATTR_LISTE_GARAGE, gm.select_all_garage());
		request.setAttribute(ATTR_LISTE_BUS, bm.select_all_bus());
		request.setAttribute(ATTR_LISTE_POINT_ARRET, pam.select_all_point_arret());
		request.setAttribute(ATTR_RESULTAT_BUS, resultat_bus);
		if(request.getAttribute("attr_destination").equals("frontend"))
		    this.getServletContext().getRequestDispatcher(vue_form_bus_frontend).forward(request,response);
		else if(request.getAttribute("attr_destination").equals("backend"))
			this.getServletContext().getRequestDispatcher(vue_form_bus_backend).forward(request,response);
		
	}

}
