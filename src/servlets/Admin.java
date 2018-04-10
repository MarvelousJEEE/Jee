package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bdd.GestionBDD;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GestionBDD bdd = GestionBDD.getInstance(); 
	public static final String vue = "/Views/admin.jsp";
	public static final String redirection = "/signin";

    /**
     * @see HttpServlet#HttpServlet()
     */
	

    
    
    
    
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	SessionTools.allowAdmin(this, request, response, vue, redirection);
	//	 this.getServletContext().getRequestDispatcher( vue ).forward( request, response ); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option = (String) request.getParameter("option");
		if("show".equals(option)) {
			bdd.setShow(request, true);
		}
		if("hide".equals(option)) {
			bdd.setShow(request, false);
		}
		  if ("delete".equals(option) ) {			 
				bdd.deleteGame(request);				 
		  }
 
		   if( "add".equals(option) ) {
				  bdd.addGame(request);	 
			  }
		   
		   if( "ban".equals(option) ) {
				  bdd.ban(request);	 
			  }
		   
		   if( "end".equals(option) ) {
				  bdd.end(request);	 
			  }
			SessionTools.allowAdmin(this, request, response, vue, redirection);	
		  // this.getServletContext().getRequestDispatcher( vue ).forward( request, response ); 
	}

}
