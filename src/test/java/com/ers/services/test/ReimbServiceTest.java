package com.ers.services.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.ers.services.ReimbService;

class ReimbServiceTest {

	public static ReimbService reimbService;

	@BeforeAll
	public static void setUpReimbService() {
		reimbService = new ReimbService();
	}

	@Test
	void testNewType() {
		assertFalse(reimbService.newType(" VACATION "));
	}

	@Test
	void testAllReimbursementsByIdFail() {
		assertTrue(reimbService.allReimbursementsById(2000).size() == 0);
	}

	@Test
	void testAllReimbursementsById() {
		assertNotNull(reimbService.allReimbursementsById(1));
	}

	@Test
	void testAllPendingByIdFail() {
		assertNull(reimbService.allPendingById(-22));
	}

	@Test
	void testAllPendingById() {
		assertNotNull(reimbService.allPendingById(5));
	}

	@Test
	void testAllPending() {
		assertNotNull(reimbService.allPending(5));
	}

	@Test
	void testAllReimbursements() {
		assertNotNull(reimbService.allReimbursements());
	}

	@Test
	void testAllReimbursementsByIdInvalidEntry() {
		assertNull(reimbService.allReimbursementsById(-70));
	}
}
