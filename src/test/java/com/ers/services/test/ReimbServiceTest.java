package com.ers.services.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.ers.services.ReimbService;

class ReimbServiceTest {

	public static ReimbService reimbService;
	
	@BeforeAll
	public static void setUpReimbService(){
		reimbService = new ReimbService();
	}
	
	@Test
	void testNewStatus() {
		assertTrue(reimbService.newStatus());
	}
	
	@Test
	void testNewType() {
		assertTrue(reimbService.newType("OTHER"));
	}
	
//	@Test 
//	void testNewTypeFail() {
//		assertFalse(reimbService.newType("unauthorized type"));
//	}
	
}
