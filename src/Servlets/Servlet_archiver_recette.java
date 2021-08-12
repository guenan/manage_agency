package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Managers.Recette_managers;


public class Servlet_archiver_recette extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public final String vue_archiver_recette="/vue_backend/index_recette.jsp";
	public final String ATTR_ARCHIVE_RECETTE="ATTR_ARCHIVAGE_RECETTE";
    
    public Servlet_archiver_recette() {
        super();
    
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	int id_recette=Integer.parseInt(request.getParameter("id_recette"));
	Recette_managers rm =new Recette_managers();
	String message_succes="";
	if (rm.archiver_recette(id_recette)){
		message_succes="recette archivée avec succès";
		request.setAttribute(ATTR_ARCHIVE_RECETTE, message_succes);
	}
	else{
		message_succes="echec";
		request.setAttribute(ATTR_ARCHIVE_RECETTE, message_succes);
	}
	
	
	this.getServletContext().getRequestDispatcher(vue_archiver_recette).forward(request,response);

	}

}
