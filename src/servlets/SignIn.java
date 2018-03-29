package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bdd.ConfigBDD;
import bdd.GestionBDD;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/signin")
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static final String VUE = "/Views/signIn.jsp";
    
    
    public SignIn() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response )throws ServletException, IOException {
    	GestionBDD bdd = GestionBDD.getInstance();
    	
    	System.out.println(request.toString());
    	boolean b = bdd.isUser(request);
    	System.out.println("is user : "+b);
        //this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
    
    
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    	
    	GestionBDD bdd = GestionBDD.getInstance();
    	System.out.println(request.toString());
    	boolean b = bdd.isUser(request);
    	System.out.println(b);
        //this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
}
