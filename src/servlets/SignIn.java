package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bdd.ConfigBDD;
import bdd.GestionBDD;
import beans.User;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/signin")
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static final String VUE = "/Views/signIn.jsp";
    public static final String redirection = "/games";
    public static final String redirection2 = "/admin";
    public static final String ATT_SESSION_USER = "users";
    
    
    public SignIn() {
        super();
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response )throws ServletException, IOException {
    	GestionBDD bdd = GestionBDD.getInstance();
    	boolean isUser=false;
    	boolean isAdmin = false;
    	boolean[] status;
		try {
			status = bdd.getStatus(request);
			isUser =status[0];
			isAdmin = status[1];
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(isUser) {
			User u = new User();
			u.setPseudo(request.getParameter("pseudo"));
			u.setAdmin(false);
			if(isAdmin) {
				u.setAdmin(true);	
			} 
			
			//Creation de la session
			HttpSession session = request.getSession();
			session.setAttribute( u.getPseudo(), u);
			Cookie cookie = new Cookie( "user", u.getPseudo() );
			cookie.setMaxAge(60 * 60 * 24 * 365);
			response.addCookie( cookie );
			
			
			request.setAttribute("pseudo", u.getPseudo());

			if(isAdmin) {
				//Pour rediriger vers une autre servlet
				response.sendRedirect( request.getContextPath() + redirection2);
			}else {
				response.sendRedirect( request.getContextPath() + redirection);
			}
		}else {
			//TODO: message d'erreur => demander de se reconnecter
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
