package Servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Beans.Employe;
import Managers.Personnel_managers;


public class Servlet_deconnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String URL_REDIRECTION ="/Servlet_authentification_personnel";
	public final String USER_SESSION="user_session";
			

       
    
    public Servlet_deconnexion() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	     HttpSession session=request.getSession();
	     Employe employe=(Employe)session.getAttribute(USER_SESSION);
	     
	     try {
			Personnel_managers.update_statut("off_line", employe.getNumero_cni());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     session.invalidate();
	     
	     this.getServletContext().getRequestDispatcher(URL_REDIRECTION).forward( request, response );


	}


}
