package com.ers.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.controllers.LoginController;
import com.ers.controllers.ReimbController;

public class PrimaryServlet extends HttpServlet {
	
	private LoginController loginController = new LoginController();
	private ReimbController reimbController = new ReimbController();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("application/json");
		response.setStatus(404);//change status code later if something is found
		final String URI = request.getRequestURI().replace("/project-1/", "");//removes all the base info
		switch(URI) {
			case "login":
				loginController.login(request,response);
				break;
			case "new":
				if(request.getSession(false) != null) {
					reimbController.newReimbursement(request, response);
				}
			case "logout":
				loginController.logout(request,response);
				break;
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("application/json");
		doGet(request,response);
	}
	
}
