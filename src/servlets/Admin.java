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


//Gestion des requêtes provenant de la page admin.jsp et pour accéder à cette page 

@WebServlet("/admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GestionBDD bdd = GestionBDD.getInstance(); 
	public static final String vue = "/Views/admin.jsp";
	public static final String redirection = "/signin";  

	
     
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//redirection d'un non administrateur vers la page de signin
		SessionTools.allowAdmin(this, request, response, vue, redirection); 

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option = (String) request.getParameter("option");
		//action à effectuer selon l'origine de la requête post
		
		
		//rendre un jeu visible 
		if("show".equals(option)) {
			bdd.setShow(request, true);
		}
		//rendre un jeu invisible 
		if("hide".equals(option)) {
			bdd.setShow(request, false);
		}
		//supprimer un jeu 
		  if ("delete".equals(option) ) {			 
				bdd.deleteGame(request);				 
		  }
 
		  //ajouter un jeu 
		   if( "add".equals(option) ) {
				  bdd.addGame(request);	 
			  }
		   
		   //banir un joueur 
		   if( "ban".equals(option) ) {
				  bdd.ban(request);	 
			  }
		   
		   //arrêter une partie en cours 
		   if( "end".equals(option) ) {
				  bdd.end(request);	 
			  }
		   
		   //renvoyer l'admin sur sa page 
			SessionTools.allowAdmin(this, request, response, vue, redirection);	
		 
	}

}
