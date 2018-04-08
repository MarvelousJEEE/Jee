package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import bdd.GestionBDD;
import beans.User;
/**
 * Servlet implementation class ServletGames
 */
@WebServlet("/games")
public class Games extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static final String VUE = "/Views/games.jsp";
    public static final String redirection = "/signin";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Games() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			SessionTools.allowUser(this, request, response, VUE, redirection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		else if(type.equals("stop") & !(pseudo.equals(""))) {
			GestionBDD bdd = GestionBDD.getInstance();
			bdd.enregistrerStop(pseudo,game);
			}
		else {
			System.out.println("erreur, reconnectez-vous");

		}
	}
	
}
