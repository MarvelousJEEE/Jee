package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bdd.GestionBDD;
import beans.User;

public class SessionTools {
	private  SessionTools	 verif = new SessionTools();
	
	private SessionTools() {}
	
	public SessionTools getInstance() {
		return verif;
	}
	
	/**
	 * Teste si l'individu est un utilisateur connecté.
	 * @param request
	 * @return true si l'individu est un utilisateur connecté, false sinon
	 */
	public static boolean isUser(HttpServletRequest request) {
		Cookie c = getCookie(request, "user");
		if(c==null) {
			return false;
		}
		String pseudo = c.getValue();
		HttpSession session = request.getSession(); 
		User u = (User)session.getAttribute(pseudo);
		if(u==null) {
			return false;
			
		}else {
			return true;
		}
	}
	
	/**
	 * Teste si l'individu est un admin connecté.
	 * @param request
	 * @return true si l'individu est un admin connecté, false sinon
	 */
	public static boolean isAdmin(HttpServletRequest request){
		Cookie c = getCookie(request, "user");
		if(c==null) {
			return false;
		}
		String pseudo = c.getValue();
		HttpSession session = request.getSession(); 
		User u = (User)session.getAttribute(pseudo);
		if(u==null) {
			return false;
			
		}else {
			if(u.isAdmin()) {
				return true;
			}else {
				return false;
			}
		}
	}

	/**
	 * Connecte un utilisateur
	 * @param request
	 * @param response
	 * @param isAdmin
	 */
	public static void logIn(HttpServletRequest request, HttpServletResponse response, boolean isAdmin) {
		//Création du bean utilisateur
		User u = new User();
		u.setPseudo(request.getParameter("pseudo"));
		u.setAdmin(isAdmin);
		
		//Creation de la session
		HttpSession session = request.getSession();
		session.setAttribute( u.getPseudo(), u);
		
		//Création du cookie
		Cookie cookie = new Cookie( "user", u.getPseudo() );
		cookie.setMaxAge(60 * 60 * 24 * 365);
		response.addCookie( cookie );
		
		request.setAttribute("pseudo", u.getPseudo());

	}
	
	/**
	 * Déconnecte un utilisateur
	 * @param request
	 * @param response
	 */
	public static void logOut(HttpServletRequest request, HttpServletResponse response) {
		
		Cookie c = getCookie(request, "user");
		if(c!=null) {
		String pseudo = c.getValue();
		HttpSession session = request.getSession();
		session.removeAttribute(pseudo);
		}
		
	}
	
	/**
	 * Fonction de filtre. Si l'individu est un utilisateur connecté, alors la vue passée en paramètre est affichée.
	 * Sinon l'individu est redirigé vers la servlet passé en paramètre.
	 * @param servlet
	 * @param request
	 * @param response
	 * @param vue
	 * @param redirection
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void allowUser(HttpServlet servlet, HttpServletRequest request,HttpServletResponse response, String vue, String redirection) throws ServletException, IOException, SQLException {
		boolean [] status = GestionBDD.getInstance().getStatus(request);
		if(SessionTools.isUser(request)) {
			request.setAttribute("pseudo", SessionTools.getCookie(request, "user").getValue());
			request.setAttribute("email", GestionBDD.getInstance().getEmail(SessionTools.getCookie(request, "user").getValue()));
			servlet.getServletContext().getRequestDispatcher(vue).forward( request, response );
		}else {
			response.sendRedirect( request.getContextPath() + redirection);
		}
	}
	
	/**
	 * Fonction de filtre. Si l'individu est un admin connecté, alors la vue passée en paramètre est affichée.
	 * Sinon l'individu est redirigé vers la servlet passé en paramètre.
	 * @param servlet
	 * @param request
	 * @param response
	 * @param vue
	 * @param redirection
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void allowAdmin(HttpServlet servlet, HttpServletRequest request,HttpServletResponse response, String vue, String redirection) throws ServletException, IOException {
		if(SessionTools.isAdmin(request)) {
			request.setAttribute("pseudo", SessionTools.getCookie(request, "user").getValue());
			servlet.getServletContext().getRequestDispatcher(vue).forward( request, response );
		}else {
			response.sendRedirect( request.getContextPath() + redirection);
		}
	}
	
	/**
	 * Retourne le cookie nommé.
	 * @param request
	 * @param nameCookie
	 * @return Cookie
	 */
	public static Cookie getCookie(HttpServletRequest request, String nameCookie) {
		if ( request.getCookies() != null ) {
			Cookie [] cookies = request.getCookies();
			for(Cookie c : cookies) {
				
				if(c.getName().equals(nameCookie)) {
					return c;
				}
			}
		}
		
		return null;
	}

}
