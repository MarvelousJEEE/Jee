package servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;

public class SessionTools {
	private  SessionTools	 verif = new SessionTools();
	
	private SessionTools() {}
	
	public SessionTools getInstance() {
		return verif;
	}
	
	public static boolean isUser(HttpServletRequest request) {
		Cookie c = getCookie(request, "user");
		if(c==null) {
			return false;
		}
		String pseudo = c.getValue();
		System.out.println(pseudo);
		HttpSession session = request.getSession(); 
		User u = (User)session.getAttribute(pseudo);
		if(u==null) {
			return false;
			
		}else {
			return true;
		}
	}
	
	public static boolean isAdmin(HttpServletRequest request){
		Cookie c = getCookie(request, "user");
		if(c==null) {
			return false;
		}
		String pseudo = c.getValue();
		HttpSession session = request.getSession(); 
		User u = (User)session.getAttribute(pseudo);
		System.out.println("***"+u.toString());
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
	
	public static void allowUser(HttpServlet servlet, HttpServletRequest request,HttpServletResponse response, String vue, String redirection) throws ServletException, IOException {
		if(SessionTools.isUser(request)) {
			request.setAttribute("pseudo", SessionTools.getCookie(request, "user").getValue());
			servlet.getServletContext().getRequestDispatcher(vue).forward( request, response );
		}else {
			response.sendRedirect( request.getContextPath() + redirection);
		}
	}
	
	public static void allowAdmin(HttpServlet servlet, HttpServletRequest request,HttpServletResponse response, String vue, String redirection) throws ServletException, IOException {
		System.out.println(SessionTools.isAdmin(request));
		if(SessionTools.isAdmin(request)) {
			request.setAttribute("pseudo", SessionTools.getCookie(request, "user").getValue());
			servlet.getServletContext().getRequestDispatcher(vue).forward( request, response );
		}else {
			response.sendRedirect( request.getContextPath() + redirection);
		}
	}
	
	
	public static Cookie getCookie(HttpServletRequest request, String nameCookie) {
		Cookie [] cookies = request.getCookies();
		for(Cookie c : cookies) {
			if(c.getName().equals(nameCookie)) {
				return c;
			}
		}
		return null;
	}
}
