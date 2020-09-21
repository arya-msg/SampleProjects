package com.ecom.promotionengine.service.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.ecom.promotionengine.service.PromotionService;
import com.ecom.promotionengine.service.domain.Cart;
import com.ecom.promotionengine.service.impl.PromotionServiceImpl;

public class PromotionEngineServiceTest {

	private PromotionService promotionService;
	private Cart cart;
	@Before
	public void setUp() throws Exception {
		/*
		 * Test setup Unit price for SKU IDss A 50 B 30 C 20 D 15 
		 * Active Promotions :  3 of A's for 130 2 of B's for 45 C & D for 30
		 */
		promotionService = new PromotionServiceImpl();
	}


	@Test
	public void testPromotionEngineScenarioA() {
	// Scenario B : 1 * A 50 1 * B 30 1 * C 20 ====== Total 100
		assertEquals(100, promotionService.applyPromotion(cart),0);
	}
	
	@Test
	public void testPromotionEngineScenarioB() {
	// Scenario B : 5 * A 130 + 2*50 5 * B 45 + 45 + 30 1 * C 20 ====== Total 370
		//assertEquals(370, promotionService.applyPromotion(cart),0);
	}
	
	@Test
	public void testPromotionEngineScenarioC() {
	// Scenario C : 3 * A 130 5 * B 45 + 45 + 1 * 30 1 * C - 1 * D 30 ====== Total 280
		//assertEquals(280, promotionService.applyPromotion(cart),0);
	}

}
