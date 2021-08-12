package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import Beans.Bus;
import Managers.Bus_managers;


public class Servlet_index_feuille_route extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public final String vue_form_recette_frontend="/vue_backend/index_feuille_route.jsp";
    public final String vue_form_recette_backend="/vue_backend/index_feuille_route.jsp";
    public final String ATTR_LISTE_MATRICULE="ATTR_LISTE_MATRICULE";
    public final String ATTR_LISTE_BUS="ATTR_LISTE_BUS";
    
       
   
    public Servlet_index_feuille_route() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("action", "index_feuille_route");
		Bus_managers bm=new Bus_managers();
		Bus bus =new Bus();
		ArrayList<Bus> liste_bus=new ArrayList<Bus>();
		ArrayList<String> liste_immatriculation=new ArrayList<String>();
		liste_bus=bm.select_all_bus();
		for (int i=0;i<liste_bus.size();i++){
			liste_immatriculation.add(liste_bus.get(i).getImmatriculation());
			System.out.println(liste_immatriculation.get(i));
		}
		
		request.setAttribute(ATTR_LISTE_MATRICULE, liste_immatriculation);
		request.setAttribute(ATTR_LISTE_BUS, bm.select_all_bus());
		
		if(request.getAttribute("attr_destination").equals("frontend"))
		    this.getServletContext().getRequestDispatcher(vue_form_recette_frontend).forward(request,response);
		else if(request.getAttribute("attr_destination").equals("backend"))
			this.getServletContext().getRequestDispatcher(vue_form_recette_backend).forward(request,response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
