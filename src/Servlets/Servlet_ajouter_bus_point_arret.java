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
import Objet_Metiers.OM_bus_point_arret;
import Managers.Bus_Point_Arret_managers;
import Beans.Bus_Point_Arret;



public class Servlet_ajouter_bus_point_arret extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public final String vue_form_bus_frontend="/vue_backend/index_flotte.jsp";
    public final String vue_form_bus_backend="/vue_backend/index_flotte.jsp";
    public final String ATTR_RESULTAT_BUS_POINT_ARRET="attr_resultat_bus_point_arret";
    public final String ATTR_RESULTAT_BUS="attr_resultat_bus";
    private String resultat_bus_point_arret="";
    private String resultat_bus_point_arret1="";
    private static final String ATTR_LISTE_LIGNE="ATTR_LISTE_LIGNE"; 
    private static final String ATTR_LISTE_BRIGADE="ATTR_LISTE_BRIGADE";
    private static final String ATTR_LISTE_GARAGE="ATTR_LISTE_GARAGE";
    private static final String ATTR_LISTE_BUS="ATTR_LISTE_BUS";
    private static final String ATTR_LISTE_POINT_ARRET="ATTR_LISTE_POINT_ARRET";
   
   
    public String getResultat_bus_bus_point_arret() {
	return resultat_bus_point_arret;
}


public void setResultat_bus(String resultat_bus_point_arret) {
	this.resultat_bus_point_arret = resultat_bus_point_arret;
}


	public Servlet_ajouter_bus_point_arret() {
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
		
		OM_bus_point_arret om_bus_pa=new OM_bus_point_arret(request);
		Bus_Point_Arret_managers bus_pa_manager=new Bus_Point_Arret_managers();
		Bus_Point_Arret bus_pa=om_bus_pa.make_bus_point_arret();
		
		
		if(bus_pa!=null){
			
			try{
				
				if(bus_pa_manager.add_bus_point_arret(bus_pa)){		
					resultat_bus_point_arret="succes d'ajout du point d'arret";
					
					request.setAttribute("bus_point_arret", bus_pa);
				
				}else{
					resultat_bus_point_arret1="echec d'ajout du point d'arret";
					
					request.setAttribute("bus", bus_pa);
				
					
				}
				}catch(Exception e){
					request.setAttribute("error_bd", e.getMessage());
				}
			
		     
		    
		}else{
			resultat_bus_point_arret="remplissez tous vos champs";
		     request.setAttribute("attr_om_bus_point_arret", om_bus_pa);
		}
			
		request.setAttribute("action", "index_flotte");
		request.setAttribute("rel", resultat_bus_point_arret);
		request.setAttribute("rel", resultat_bus_point_arret1);
		
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
		if(request.getAttribute("attr_destination").equals("frontend"))
		    this.getServletContext().getRequestDispatcher(vue_form_bus_frontend).forward(request,response);
		else if(request.getAttribute("attr_destination").equals("backend"))
			this.getServletContext().getRequestDispatcher(vue_form_bus_backend).forward(request,response);
		
	}

}
