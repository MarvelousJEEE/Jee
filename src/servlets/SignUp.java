package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bdd.GestionBDD;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/signup")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String vue = "/PublicViews/signUp.jsp";
	private static final String redirection = "/games";
	private static final String redirection2 = "/signin";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pseudo = request.getParameter("pseudo");
		String password = request.getParameter("password");
		String password2 = request.getParameter("Verif");
		String dateOfBirth = request.getParameter("dateOfBirth");
		String email = request.getParameter("email");
		
		
		GestionBDD bdd = GestionBDD.getInstance();
		if(password.equals(password2)) {
			boolean a = bdd.enregisterJoueur(pseudo, password, dateOfBirth, email);
			if (a) {
				SessionTools.logIn(request, response, false);
				response.sendRedirect( request.getContextPath() + redirection);
			} else {
				response.sendRedirect( request.getContextPath() + redirection2);
			}
		} else {
			System.out.println("erreur dans la verification du Password");
			response.sendRedirect( request.getContextPath() + redirection2);
		}
	}

}
