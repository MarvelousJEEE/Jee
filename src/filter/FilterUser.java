package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlets.SessionTools;

/**
 * FilterAdmin filtre les connexions vers les servlets (sauf admin et signin) et les pages /Views/*.jsp
 */
@WebFilter(
		urlPatterns = { 
				"/FilterUser", 
				"/Views/*",
				"/games", 
				"/infoPlayer"
		})
public class FilterUser implements Filter {
	public static final String redirection = "/signin";
	public static final String redirection2 = "/PublicViews/acces.jsp";

    
    public FilterUser() {	
    }

	
	public void destroy() {

	}

	/**
	 * Méthode appelé à chaque requête vers les servlets et les pages en question
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(!SessionTools.isUser((HttpServletRequest)request)) {
			((HttpServletResponse) response).sendRedirect( ((HttpServletRequest)request).getContextPath() + redirection2);
		}else {
			chain.doFilter(request, response);

		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
