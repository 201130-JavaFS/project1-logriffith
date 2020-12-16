package com.ers.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.controllers.LoginController;

public class PrimaryServlet extends HttpServlet {
	
	private LoginController loginController = new LoginController();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("application/json");
		response.setStatus(404);//change status code later if something is found
		final String URI = request.getRequestURI().replace("/project1/", "");//removes all the base info
		switch(URI) {
			case "login":
				loginController.login(request,response);
				break;
		}
	}
	
}
