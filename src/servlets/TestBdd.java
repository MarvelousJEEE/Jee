package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bdd.GestionBDD;
@WebServlet("/TestBdd")
public class TestBdd extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ATT_MESSAGES = "messages";
    public static final String VUE          = "/WEB-INF/test_jdbc.jsp";
    
    
    
    
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        GestionBDD test = new GestionBDD();
        List<String> messages = test.executerTests( request );

        request.setAttribute( ATT_MESSAGES, messages );

        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
}