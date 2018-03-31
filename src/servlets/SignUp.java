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
		
		/*
		System.out.println("pseudo : "+pseudo);
		System.out.println("password : "+password);
		System.out.println("password2 : "+password2);
		System.out.println("dateOfBirth:  "+dateOfBirth);
		System.out.println("email : "+email);
		*/
		GestionBDD bdd = GestionBDD.getInstance();
		bdd.enregisterJoueur(pseudo, password, dateOfBirth, email);
	}

}
