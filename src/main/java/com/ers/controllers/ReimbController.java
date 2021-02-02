package com.ers.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ers.models.NewReimbDTO;
import com.ers.models.Reimbursement;
import com.ers.services.ReimbService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReimbController {

	private ReimbService reimbService = new ReimbService();
	private ObjectMapper objectMapper = new ObjectMapper();
	private static final Logger log = LogManager.getLogger(ReimbController.class); 

	public void newReimbursement(HttpServletRequest request, HttpServletResponse response) throws IOException {

		if (request.getMethod().equals("POST")) {
			BufferedReader bufferedReader = request.getReader();
			StringBuilder stringBuilder = new StringBuilder();
			String line = bufferedReader.readLine();
			while (line != null) {
				stringBuilder.append(line);
				line = bufferedReader.readLine();
			}

			String body = stringBuilder.toString();
			NewReimbDTO newReimbDTO = objectMapper.readValue(body, NewReimbDTO.class);
			HttpSession httpSession = request.getSession(false);
			int userId = (int) httpSession.getAttribute("userId");
			Reimbursement reimbursement = new Reimbursement(newReimbDTO.amount, newReimbDTO.description,
					userId, newReimbDTO.type);
			
			if(reimbService.newReimbursement(reimbursement)) {
				//log.info(response);
				response.getWriter().print("New Reimbursement Request");
				response.setStatus(201);
			}else {
				//log.warn("Request: "+ request + "Response: "+response);
				response.getWriter().print("Request couldn't be inserted into DB");
				response.setStatus(409);
				log.warn("Request couldn't be inserted into DB. Status Code: "+409);
			}
		}

	}
	
	public void allPendingForUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession httpSession = request.getSession(false);
		int userId = (int) httpSession.getAttribute("userId");
		List<Reimbursement> userPendingList = reimbService.allPendingById(userId);
		String json = objectMapper.writeValueAsString(userPendingList);
		response.getWriter().print(json);
		response.setStatus(200);
		
	}
	
	public void allPending(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession httpSession = request.getSession(false);
		int userId = (int) httpSession.getAttribute("userId");
		List<Reimbursement> pendingList = reimbService.allPending(userId);
		String json = objectMapper.writeValueAsString(pendingList);
		response.getWriter().print(json);
		response.setStatus(200);
	}
	
	public void allReimburements(HttpServletResponse response) throws IOException{
		List<Reimbursement> allReimb = reimbService.allReimbursements();
		String json = objectMapper.writeValueAsString(allReimb);
		response.getWriter().print(json);
		response.setStatus(200);
	}
	
	public void allReimbursementsForUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession httpSession = request.getSession(false);
		int userId = (int) httpSession.getAttribute("userId");
		List<Reimbursement> userReimb = reimbService.allReimbursementsById(userId); 
		String json = objectMapper.writeValueAsString(userReimb);
		response.getWriter().print(json);
		response.setStatus(200);
	}

}
