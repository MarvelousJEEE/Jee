package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import bdd.GestionBDD;

/**
 * Servlet implementation class ServletGames
 */
@WebServlet("/servletgames")
public class ServletGames extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGames() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pseudo = request.getParameter("variable1");
		String game = request.getParameter("variable2");		
		String type = request.getParameter("variable3");
		if(type.equals("play") &  !(pseudo.equals(""))) {
			GestionBDD bdd = GestionBDD.getInstance();
			bdd.enregisterPartie(pseudo,game);
		}
		if(type.equals("stop") & !(pseudo.equals(""))) {
			GestionBDD bdd = GestionBDD.getInstance();
			bdd.enregistrerStop(pseudo,game);
			}
		else {
			System.out.println("erreur, reconnectez-vous");

		}
	}
}
