package com.ecom.promotionengine.service.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PromotionEngineServiceTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		/*
		 * Test setup Unit price for SKU IDss A 50 B 30 C 20 D 15 
		 * Active Promotions :  3 of A's for 130 2 of B's for 45 C & D for 30
		 */
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPromotionEngineScenarioA() {
	// Scenario B : 1 * A 50 1 * B 30 1 * C 20 ====== Total 100
	}
	
	@Test
	public void testPromotionEngineScenarioB() {
	// Scenario B : 5 * A 130 + 2*50 5 * B 45 + 45 + 30 1 * C 20 ====== Total 370
	}
	
	@Test
	public void testPromotionEngineScenarioC() {
	// Scenario C : 3 * A 130 5 * B 45 + 45 + 1 * 30 1 * C - 1 * D 30 ====== Total 280
	}

}
