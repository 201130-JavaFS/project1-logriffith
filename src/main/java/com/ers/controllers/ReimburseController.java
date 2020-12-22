package com.ers.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.models.NewReimbDTO;
import com.ers.models.Reimbursement;
import com.ers.services.ReimbService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReimburseController {

	private ReimbService reimbService = new ReimbService();
	private ObjectMapper objectMapper = new ObjectMapper();

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
			Reimbursement reimbursement = new Reimbursement(newReimbDTO.amount, newReimbDTO.description,
					newReimbDTO.userId, newReimbDTO.type);
			
			if(reimbService.newReimbursement(reimbursement)) {
				response.setStatus(200);
				response.getWriter().print("New Reimbursement Request");
			}else {
				response.setStatus(400);
				response.getWriter().print("Request couldn't be inserted into DB");
			}
		}

	}

}
//public static final String NEW_REIMB_STATUS = "INSERT INTO reimbursement_status (status) VALUES (NULL)";
//
//public static final String NEW_REIMB_TYPE = "INSERT INTO reimbursement_type (reimb_type) VALUES (?)";
//
//public static final String NEW_REIMBURSEMENT = "INSERT INTO reimbursements (amount, submitted, resolved, description, user_id, status_id, type_id) "
//		+ "VALUES (?, (SELECT now()), NULL, ?, ?, (SELECT max(status_id) FROM reimbursement_status) , (SELECT max(type_id) FROM reimbursement_type))";
