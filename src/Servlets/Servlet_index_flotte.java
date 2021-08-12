package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Managers.Brigade_managers;
import Managers.Bus_managers;
import Managers.Garage_managers;
import Managers.Ligne_managers;
import Managers.Point_Arret_managers;



public class Servlet_index_flotte extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public final String vue_form_flotte_frontend="/vue_backend/index_flotte.jsp";
    public final String vue_form_flotte_backend="/vue_backend/index_flotte.jsp";  
    private static final String ATTR_LISTE_LIGNE="ATTR_LISTE_LIGNE"; 
    private static final String ATTR_LISTE_BRIGADE="ATTR_LISTE_BRIGADE";
    private static final String ATTR_LISTE_GARAGE="ATTR_LISTE_GARAGE";
    private static final String ATTR_LISTE_BUS="ATTR_LISTE_BUS";
    private static final String ATTR_LISTE_POINT_ARRET="ATTR_LISTE_POINT_ARRET";
   
    public Servlet_index_flotte() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		request.setAttribute("action", "index_flotte");
		
		Bus_managers bm=new Bus_managers();
		Ligne_managers lm=new Ligne_managers();
		Brigade_managers brig =new Brigade_managers();
		Point_Arret_managers pam=new Point_Arret_managers();
		Garage_managers gm=new Garage_managers();
		
		request.setAttribute(ATTR_LISTE_LIGNE, lm.select_all_ligne());
		request.setAttribute(ATTR_LISTE_BRIGADE, brig.select_all_brigade());
		request.setAttribute(ATTR_LISTE_GARAGE, gm.select_all_garage());
		request.setAttribute(ATTR_LISTE_BUS, bm.select_all_bus());
		request.setAttribute(ATTR_LISTE_POINT_ARRET, pam.select_all_point_arret());
		
		
			if(request.getAttribute("attr_destination").equals("frontend"))
			    this.getServletContext().getRequestDispatcher(vue_form_flotte_frontend).forward(request,response);
			else if(request.getAttribute("attr_destination").equals("backend"))
				this.getServletContext().getRequestDispatcher(vue_form_flotte_backend).forward(request,response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
