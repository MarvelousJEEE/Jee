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
    public static final String redirection2 = "/Views/admin.jsp";
    public static final String ATT_SESSION_USER = "users";
    private GestionBDD bdd;
    private ResultSet games;
    
    public SignIn() {
        super();
        this.bdd = GestionBDD.getInstance();
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
			String[] tableGames = new String[0];
			tableGames = this.getTableGames();
			request.setAttribute("Games", tableGames);
			User u = new User();
			u.setPseudo(request.getParameter("pseudo"));
			u.setAdmin(false);
			if(isAdmin) {
				u.setAdmin(true);	
			} 
			HttpSession session = request.getSession();
			session.setAttribute( u.getPseudo(), u);
			Cookie cookie = new Cookie( "user", u.getPseudo() );
			cookie.setMaxAge(60 * 60 * 24 * 365);
			response.addCookie( cookie );
			
			request.setAttribute("user", u.getPseudo());
			System.out.println(isAdmin);
			if(isAdmin) {
				this.getServletContext().getRequestDispatcher( redirection2 ).forward( request, response );
			}else {
				this.getServletContext().getRequestDispatcher( redirection ).forward( request, response );
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
    
    public String[] getTableGames() {
  	    this.games = bdd.getGames();
  	    try {
  	    		int i;
  	    		this.games.last();
			 //on récupère le numéro de la ligne 
	  	    int nombreLignes = this.games.getRow(); 
	  	    //on replace le curseur avant la première ligne 
	  	    this.games.beforeFirst();
	  	    this.games.next();
			String[] tableGames = new String[nombreLignes];
			for(i=0;i<nombreLignes;i++) {
				tableGames[i]=this.games.getNString("infos");
				this.games.next();
			}
	  	    return tableGames;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
  	    
    }
 
}
