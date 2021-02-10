package com.ers.services.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.ers.models.User;
import com.ers.services.UserService;

class UserServiceTest {

	public static UserService userService;

	@BeforeAll
	public static void setUpUserService() {
		userService = new UserService();
	}

	@Test
	void testLogin() {
		User user = userService.login("testmanager", "manager");
		User manager = new User(4, "Tom", "Smith", null, "Manager");
		assertEquals(manager, user);
	}

	@Test
	void testWrongLogin() {
		User user = new User(4, "Tom", "Smith", null, "Manager");
		User falseUser = userService.login("testmanager", "managerkjdkfk");
		assertFalse(user.equals(falseUser));
	}
	
	@Test
	void testGetType() {
		String type = "travel";
		assertEquals(type,userService.getType(4));
	}
	
	@Test
	void testGetStatus() {
		assertEquals("denied", userService.getStatus(4));
	}
	
	@Test
	void testResolve() {
		assertTrue(userService.resolve(4,"denied"));
	}
	
	
	@Test void testResolveFail(){
		assertFalse(userService.resolve(5, "unauthorized status"));
	}

}
