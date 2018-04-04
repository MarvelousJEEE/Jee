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
	private ResultSet games; 
	private ResultSet players; 
	private ResultSet matchs; 
	private GestionBDD bdd;
	public static final String vue = "/Views/admin.jsp";
	public static final String redirection = "/signin";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
        super();
        this.bdd = GestionBDD.getInstance();
  	    this.games = bdd.getGames();
  	    this.players = bdd.getPlayers();
  	    this.matchs = bdd.getMatchs();
    }
    
    public String test() {
    	return "admin"; 
    }
    
    public ResultSet getGames() {
      
    	return this.games ;
    }
    
    
    public ResultSet getPlayers() {
        
    	return this.players ;
    }
    
   public ResultSet getMatchs() {
        
    	return this.matchs ;
    }
    
    
    
    
    
    
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionTools.allowAdmin(this, request, response, vue, redirection);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionTools.allowAdmin(this, request, response, vue, redirection);	
		String option = (String) request.getParameter("option");
		
		  if ("delete".equals(option) ) {			 
				bdd.deleteGame(request);				 
		  }
 
		   if( "add".equals(option) ) {
				  bdd.addGame(request);	 
			  }
			SessionTools.allowAdmin(this, request, response, vue, redirection);
	}

}
