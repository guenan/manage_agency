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
import Beans.Point_Arret;
import java.lang.Exception;

public class Servlet_rechercher_bus_immatriculation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String vue_index_feuille_route="/vue_backend/index_feuille_route.jsp";
	public static final String ATTR_LISTE_BUS="ATTR_LISTE_BUS";
	public static final String ATTR_LISTE_POINT_ARRET="ATTR_LISTE_POINT_ARRET";
	
   public Servlet_rechercher_bus_immatriculation() {
        super();
            }

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			


		}
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		//	System.out.println("post de Servlet_rechercher_bus_immatriculation ");
			
			String immatriculation=request.getParameter("immatriculation");
			ArrayList<Point_Arret> liste_point_arret=new ArrayList<Point_Arret>();
			Bus_managers bm=new Bus_managers();
			
			try{
				Bus bus=new Bus();
				bus=bm.select_unique_bus(immatriculation);
				request.setAttribute(ATTR_LISTE_BUS, bus);
			}
			catch (Exception e){
				e.getMessage();
			}
			
			liste_point_arret=bm.get_bus_point_arret(immatriculation);
			request.setAttribute("action", "index_info_bus");
			request.setAttribute(ATTR_LISTE_POINT_ARRET,liste_point_arret);
			
			this.getServletContext().getRequestDispatcher(vue_index_feuille_route).forward(request,response);
			}
		
		}
		
