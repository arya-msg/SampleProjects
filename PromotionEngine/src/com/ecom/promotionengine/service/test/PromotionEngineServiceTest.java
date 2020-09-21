package com.ecom.promotionengine.service.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ecom.promotionengine.service.PromotionService;
import com.ecom.promotionengine.service.domain.Cart;
import com.ecom.promotionengine.service.domain.Product;
import com.ecom.promotionengine.service.domain.Promotion;
import com.ecom.promotionengine.service.impl.PromotionServiceImpl;
import com.ecom.promotionengine.utility.PromotionEngineConstant;

public class PromotionEngineServiceTest {

	private PromotionService  promotionService = new PromotionServiceImpl();;
	private Cart cart;
	private static List<Product> productList;
	private List<Character> skuList;
	private static List<Promotion> promotionsToBeApplied;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		/*
		 * Test setup Unit price for SKU IDss A 50 B 30 C 20 D 15 
		 * Active Promotions :  3 of A's for 130 2 of B's for 45 C & D for 30
		 */
		//In real time projects it will be a db fetch operation.
		productList = new ArrayList<Product>();
		Product productA = new Product(PromotionEngineConstant.SKU_A,50);
		Product productB = new Product(PromotionEngineConstant.SKU_B,30);
		Product productC = new Product(PromotionEngineConstant.SKU_C,20);
		Product productD = new Product(PromotionEngineConstant.SKU_D,15);
		productList.add(productA);
		productList.add(productB);
		productList.add(productC);
		productList.add(productD);
		promotionsToBeApplied = new ArrayList<Promotion>();
		Promotion promoA = new Promotion(PromotionEngineConstant.PROMO_A,PromotionEngineConstant.PROMO_A_DESC);
		Promotion promoB = new Promotion(PromotionEngineConstant.PROMO_B,PromotionEngineConstant.PROMO_B_DESC);
		promotionsToBeApplied.add(promoA);
		promotionsToBeApplied.add(promoB);
				
	}
	@Before
	public void setUp() throws Exception {
		cart = new Cart();
		skuList = new ArrayList<>();
	}


	@Test
	public void testPromotionEngineScenarioA() {
	// Scenario B : 1 * A 50 1 * B 30 1 * C 20 ====== Total 100
		skuList.add(PromotionEngineConstant.SKU_A);
		skuList.add(PromotionEngineConstant.SKU_B);
		skuList.add(PromotionEngineConstant.SKU_C);
		cart.setSkuList(skuList);
		assertEquals(100, promotionService.applyPromotion(cart,productList,promotionsToBeApplied),0);
	}
	
	@Test
	public void testPromotionEngineScenarioB() {
	// Scenario B : 5 * A 130 + 2*50 5 * B 45 + 45 + 30 1 * C 20 ====== Total 370
		skuList.add(PromotionEngineConstant.SKU_A);
		skuList.add(PromotionEngineConstant.SKU_B);
		skuList.add(PromotionEngineConstant.SKU_C);
		skuList.add(PromotionEngineConstant.SKU_A);
		skuList.add(PromotionEngineConstant.SKU_B);
		skuList.add(PromotionEngineConstant.SKU_A);
		skuList.add(PromotionEngineConstant.SKU_B);
		skuList.add(PromotionEngineConstant.SKU_A);
		skuList.add(PromotionEngineConstant.SKU_B);
		skuList.add(PromotionEngineConstant.SKU_A);
		skuList.add(PromotionEngineConstant.SKU_B);
		cart.setSkuList(skuList);
		assertEquals(370, promotionService.applyPromotion(cart,productList,promotionsToBeApplied),0);
	}
	
	@Test
	public void testPromotionEngineScenarioC() {
	// Scenario C : 3 * A 130 5 * B 45 + 45 + 1 * 30 1 * C - 1 * D 30 ====== Total 280
		skuList.add(PromotionEngineConstant.SKU_A);
		skuList.add(PromotionEngineConstant.SKU_B);
		skuList.add(PromotionEngineConstant.SKU_C);
		skuList.add(PromotionEngineConstant.SKU_A);
		skuList.add(PromotionEngineConstant.SKU_B);
		skuList.add(PromotionEngineConstant.SKU_A);
		skuList.add(PromotionEngineConstant.SKU_B);
		skuList.add(PromotionEngineConstant.SKU_D);
		skuList.add(PromotionEngineConstant.SKU_B);
		skuList.add(PromotionEngineConstant.SKU_B);
		cart.setSkuList(skuList);
		assertEquals(280, promotionService.applyPromotion(cart,productList,promotionsToBeApplied),0);
	}

}
