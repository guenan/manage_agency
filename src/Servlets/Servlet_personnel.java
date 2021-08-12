
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet_personnel extends HttpServlet {

   

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String ATTR_action=(String) request.getAttribute("attr_action");
        String ATTR_destination=(String) request.getAttribute("attr_destination");
       request.setAttribute("ATTR_destination", ATTR_destination);
       request.setAttribute("ATTR_action", ATTR_action);
       this.getServletContext().getRequestDispatcher("/Servlet_"+ATTR_action).forward(request,response);
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ATTR_action=(String) request.getAttribute("attr_action");
        String ATTR_destination=(String) request.getAttribute("attr_destination");
       request.setAttribute("ATTR_destination", ATTR_destination);
       this.getServletContext().getRequestDispatcher("/Servlet_"+ATTR_action).forward(request,response);
        
    }

}
