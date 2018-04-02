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
		String prenom = request.getParameter("variable1");
		PrintWriter out = response.getWriter();
		out.println(prenom);
		JOptionPane.showMessageDialog(null,prenom);

	}

}
