package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import com.mysql.jdbc.ResultSetMetaData;

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
    public static final String redirection = "/Views/games.jsp";
    public static final String redirection2 = "/admin";
    public static final String ATT_SESSION_USER = "users";
    private static GestionBDD bdd;
    private static ResultSet games;
    
    public SignIn() {
        super();
        this.bdd = GestionBDD.getInstance();
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response )throws ServletException, IOException {
    	SessionTools.logOut(request, response);
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
			//SessionTools.logIn(request, response, isAdmin);
			User u = new User();
			u.setPseudo(request.getParameter("pseudo"));
			u.setAdmin(false);
			System.out.println("Games : "+request.getAttribute("Games"));
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
		SessionTools.logOut(request, response);
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
