package servlets;

import java.io.IOException;
import java.sql.SQLException;
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
    public static final String redirection = "/Views/games.jsp";
    
    
    public SignIn() {
        super();
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response )throws ServletException, IOException {
    	GestionBDD bdd = GestionBDD.getInstance();
    	boolean isUser=false;
	try {
		isUser = bdd.isUser(request);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	if(isUser) {
	       this.getServletContext().getRequestDispatcher("/Views/games.jsp").forward( request, response );

	}else {
	       this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}
    }
    
    
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		GestionBDD bdd = GestionBDD.getInstance();
	    boolean isUser=false;
		try {
			isUser = bdd.isUser(request);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(isUser) {
			
		} else {
		       this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
		}
    }
}
