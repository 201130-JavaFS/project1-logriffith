package com.ers.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ers.models.LoginDTO;
import com.ers.models.User;
import com.ers.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginController {
	
	private ObjectMapper objectMapper = new ObjectMapper();
	private UserService userService = new UserService();
	private static final Logger log = LogManager.getLogger(LoginController.class);
	
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException{
	
		if(request.getMethod().equals("POST")) {
			BufferedReader bufferedReader = request.getReader();//returns the body of the request
			StringBuilder stringBuilder = new StringBuilder();
			String line = bufferedReader.readLine();//returns the first line of the body of the request
			while(line != null) {
				stringBuilder.append(line);
				line = bufferedReader.readLine();//gets the next line from the body
			}
			
			String body = new String(stringBuilder);//puts the body in String object
			LoginDTO loginDTO = objectMapper.readValue(body, LoginDTO.class); //takes the JSON data as a string and stores it as a LoginDTO object
			User user = userService.login(loginDTO.username, loginDTO.password);
			
			if(user != null) {
				HttpSession httpSession = request.getSession();//returns the current session or creates one if it doesn't exist

				String json = objectMapper.writeValueAsString(user);//converts the user into a JSON String 
				response.getWriter().print(json);
				
				//these setAttributes remain in the server
				httpSession.setAttribute("role", user.getRole());
				httpSession.setAttribute("userId", user.getUserId());
				httpSession.setAttribute("user", loginDTO);
				httpSession.setAttribute("logged in", true);
				response.setStatus(200);//login was successful
			}else {
				HttpSession httpSession = request.getSession(false);//returns the session or null if one doesn't already exist
				if(httpSession != null) {
					httpSession.invalidate();//deletes the session/logs them out
				}
				response.setStatus(401);
				response.getWriter().print("Login Failed");
				log.warn("The user doesn't match those credentials, Status Code: "+ 401);

			}
		}else {
			log.error("Wrong HTTP method was used. Status Code: 405");
		}
		
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false); //if a session doesn't already exists, it returns null
		if(session != null) {
			session.invalidate(); //deletes the session/logs them out
			response.setStatus(200);
			response.getWriter().print("Logged Out");
		}else {
			log.warn("Cannot invalidate a session that doesn't exist. Status Code: 500.");
		}
		
	}
	
}
