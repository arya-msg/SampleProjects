package com.ecom.promotionengine.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ecom.promotionengine.service.PromotionService;
import com.ecom.promotionengine.service.domain.Cart;
import com.ecom.promotionengine.service.domain.Product;
import com.ecom.promotionengine.service.domain.Promotion;
import com.ecom.promotionengine.utility.PromotionEngineConstant;

public class PromotionServiceImpl implements PromotionService {

	@Override
	public double applyPromotion(Cart cart, List<Product> productList, List<Promotion> promotionsToBeApplied) {
		double totalAmount = 0;
		if (null != cart && null != cart.getSkuList() && !cart.getSkuList().isEmpty()) {
			Map<Character, Integer> itemList = getCountOfDistinctItemsInCart(cart);
			
			if (null != productList && !productList.isEmpty()) {
				Map<Character, Double> itemPrice = getPriceOfDistinctItemsInCart(productList);
				
			// apply promotion
			// 3 of A's for 130
			for (Promotion promotion : promotionsToBeApplied) {
				if (null != promotion.getType() && promotion.getType().equals(PromotionEngineConstant.PROMO_A)) {
					totalAmount = applyPromotionA(totalAmount, itemList, itemPrice);
				}
				if (null != promotion.getType() && promotion.getType().equals(PromotionEngineConstant.PROMO_B)) {
					totalAmount = applyPromotionB(totalAmount, itemList, itemPrice);
				}
			}
		
			}
			
		}
		
		return totalAmount;
	}

	private double applyPromotionB(double totalAmount, Map<Character, Integer> itemList,
			Map<Character, Double> itemPrice) {
		// C & D for 30
		int countOfC = 0;
		int countOfD = 0;
		if (itemList.containsKey(PromotionEngineConstant.SKU_C)) {
			countOfC = itemList.get(PromotionEngineConstant.SKU_C);
		}
		if (itemList.containsKey(PromotionEngineConstant.SKU_D)) {

			countOfD = itemList.get(PromotionEngineConstant.SKU_D);
		}
		if (countOfC != 0 && countOfD != 0) {
			if (countOfC == countOfD) {
				totalAmount = totalAmount + countOfC * PromotionEngineConstant.INT_30;
			} else if (countOfC > countOfD) {
				totalAmount = totalAmount + countOfD * PromotionEngineConstant.INT_30 
						+ (countOfC - countOfD) * itemPrice.get(PromotionEngineConstant.SKU_C);
			} else {
				totalAmount = totalAmount + countOfC * PromotionEngineConstant.INT_30 
						+ (countOfD - countOfC) * itemPrice.get(PromotionEngineConstant.SKU_D);
			}
		} else {
			totalAmount = totalAmount + countOfC * itemPrice.get(PromotionEngineConstant.SKU_C) 
			+ countOfD * itemPrice.get(PromotionEngineConstant.SKU_D);
		}
		return totalAmount;
	}

	private double applyPromotionA(double totalAmount, Map<Character, Integer> itemList,
			Map<Character, Double> itemPrice) {
		if (itemList.containsKey(PromotionEngineConstant.SKU_A)) {
			// (counterofA / 3) * 130 + (counterofA % 3 * priceofA);
			int countOfA = itemList.get(PromotionEngineConstant.SKU_A);
			if (countOfA != 0)
				totalAmount = totalAmount + ((countOfA / PromotionEngineConstant.INT_3) * PromotionEngineConstant.INT_130
						+ ((countOfA % PromotionEngineConstant.INT_3) * itemPrice.get(PromotionEngineConstant.SKU_A)));
		}
		// 2 of B's for 45
		if (itemList.containsKey(PromotionEngineConstant.SKU_B)) {
			int countOfB = itemList.get(PromotionEngineConstant.SKU_B);
			if (countOfB != 0)
				totalAmount = totalAmount + ((countOfB / PromotionEngineConstant.INT_2) * PromotionEngineConstant.INT_45 
						+ (countOfB % PromotionEngineConstant.INT_2 * itemPrice.get(PromotionEngineConstant.SKU_B)));

		}
		return totalAmount;
	}

	private Map<Character, Double> getPriceOfDistinctItemsInCart(List<Product> productList) {
		Map<Character, Double> itemPrice = new HashMap<Character, Double>();
		productList.stream().distinct().forEach(product -> {
			itemPrice.put(product.getSkuId(), product.getPrice());
		}

		);
		return itemPrice;
	}

	private Map<Character, Integer> getCountOfDistinctItemsInCart(Cart cart) {
		Map<Character, Integer> itemList = new HashMap<Character, Integer>();
		cart.getSkuList().stream().distinct().forEach(item -> {
			int count = (int) cart.getSkuList().stream().filter(sku -> sku.equals(item)).count();
			itemList.put(item, count);
		}

		);
		return itemList;
	}

	

}
