package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import beans.User;
import bdd.GestionBDD;

/**
 * Servlet implementation class ServletGames
 */
@WebServlet("/servletgames")
public class ServletGames extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static final String VUE = "/Views/games.jsp";
    public static final String redirection = "/Views/signIn.jsp";
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
		if(SessionTools.isUser(request)) {
			request.setAttribute("pseudo", SessionTools.getCookie(request, "user").getValue());
			this.getServletContext().getRequestDispatcher(VUE).forward( request, response );
		}else {
			this.getServletContext().getRequestDispatcher(redirection).forward( request, response );

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
		if(type.equals("stop") & !(pseudo.equals(""))) {
			GestionBDD bdd = GestionBDD.getInstance();
			bdd.enregistrerStop(pseudo,game);
			}
		else {
			System.out.println("erreur, reconnectez-vous");

		}
	}
}
