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
	private static final String vue = "/Views/signUp.jsp";
	private static final String redirection = "/Views/games.jsp";
       
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
				this.getServletContext().getRequestDispatcher(redirection).forward(request, response);
			} else {
				this.getServletContext().getRequestDispatcher(vue).forward(request, response);
			}
		} else {
			System.out.println("erreur dans la verification du Password");
			this.getServletContext().getRequestDispatcher( vue ).forward( request, response);
		}
	}

}
