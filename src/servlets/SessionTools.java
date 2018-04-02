package servlets;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
		if(u==null) {
			return false;
			
		}else {
			if(u.isAdmin()) {
				return true;
			}else {
				return true;
			}
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
