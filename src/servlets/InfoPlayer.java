package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bdd.GestionBDD;

@WebServlet("/infoPlayer")
public class InfoPlayer extends HttpServlet {
	
	public static final String vue = "/Views/infoPlayer.jsp";
	public static final String redirection = "/signin";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			SessionTools.allowUser(this, request, response, vue, redirection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost (HttpServletRequest request, HttpServletResponse response) {
		String option = (String) request.getParameter("option");
		System.out.println("requestPseudo => "+request.getParameter("pseudo"));
		if(option.equals("password")) {
			try {
				if(GestionBDD.getInstance().isUser(request)) {
					if(request.getParameter("RepeatPassword").equals(request.getParameter("NewPassword"))) {
						System.out.println("ok !!!!!!");
						GestionBDD.getInstance().changeMDP(request);
					} else {
						//TODO: Error : wrong password 
						System.out.println("erreur dans le nouveau mdp !!!");
					}
				} else {
					//TODO: Error : wrong password 
					System.out.println("Mauvais mdp !!!");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
