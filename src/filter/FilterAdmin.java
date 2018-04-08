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

import servlets.Admin;
import servlets.SessionTools;

/**
 * Servlet Filter implementation class FilterAdmin
 */
@WebFilter({ "/admin", "/Views/admin.jsp" })
public class FilterAdmin implements Filter {
	public static final String redirection = "/signin";
	public static final String redirection2 = "/PublicViews/acces.jsp";
    public FilterAdmin() {
    }

	
	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("filtre admin!!!");
		if(!SessionTools.isAdmin((HttpServletRequest)request)) {
			((HttpServletResponse) response).sendRedirect( ((HttpServletRequest)request).getContextPath() + redirection2);
		}else {
			chain.doFilter(request, response);

		}
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
