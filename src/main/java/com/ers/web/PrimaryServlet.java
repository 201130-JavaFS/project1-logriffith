package com.ers.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ers.controllers.LoginController;
import com.ers.controllers.ReimbController;

public class PrimaryServlet extends HttpServlet {
	
	/**
	 * Default serial ID for this servlet.
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = LogManager.getLogger(PrimaryServlet.class); 

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
				}else {
					response.setStatus(403);
				}
				break;
			case "pending/manager":
				if(request.getSession(false) != null) {
					reimbController.allPending(request,response);
				}else {
					response.setStatus(403);
				}
				break;
			case "pending/manager/resolve":
				if(request.getSession(false) != null) {
					reimbController.resolveRequest(request, response);
				}else {
					response.setStatus(403);
				}
				break;
			case "pending/employee":
				if(request.getSession(false) != null) {
					reimbController.allPendingForUser(request, response);
				}else {
					response.setStatus(403);
				}
				break;
			case "all":
				if(request.getSession(false) != null) {
					reimbController.allReimburements(response);
				}else {
					response.setStatus(403);
				}
				break;
			case "all/employee":
				if(request.getSession(false) != null) {
					reimbController.allReimbursementsForUser(request, response);
				}else {
					response.setStatus(403);
				}
				break;
			case "logout":
				loginController.logout(request,response);
				break;
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("application/json");
		doGet(request,response);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("application/json");
		doGet(request,response);
	}
	
}
