package com.ecom.promotionengine.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ecom.promotionengine.service.PromotionService;
import com.ecom.promotionengine.service.domain.Cart;
import com.ecom.promotionengine.service.domain.Product;
import com.ecom.promotionengine.service.domain.Promotion;

public class PromotionServiceImpl implements PromotionService {


	private static final int INT_30 = 30;
	private static final int INT_45 = 45;
	private static final int INT_2 = 2;
	private static final int INT_130 = 130;
	private static final int INT_3 = 3;
	private static final char SKU_D = 'D';
	private static final char SKU_C = 'C';
	private static final char SKU_B = 'B';
	private static final char SKU_A = 'A';

	@Override
	public double applyPromotion(Cart cart, List<Product> productList, List<Promotion> promotionsToBeApplied) {
		double totalAmount = 0;
		if (null != cart && null != cart.getSkuList() && !cart.getSkuList().isEmpty()) {
			Map<Character, Integer> itemList = new HashMap<Character, Integer>();
			cart.getSkuList().stream().distinct().forEach(item -> {
				int count = (int) cart.getSkuList().stream().filter(sku -> sku.equals(item)).count();
				itemList.put(item, count);
			}

			);
			
			if (null != productList && !productList.isEmpty()) {
				Map<Character, Double> itemPrice = new HashMap<Character, Double>();
				productList.stream().distinct().forEach(product -> {
					itemPrice.put(product.getSkuId(), product.getPrice());
				}

				);
				
			
			
			// apply promotion
			// 3 of A's for 130
			for (Promotion promotion : promotionsToBeApplied) {
				if (null != promotion.getType() && promotion.getType().equals("PromoA")) {
					if (itemList.containsKey(SKU_A)) {
						// (counterofA / 3) * 130 + (counterofA % 3 * priceofA);
						int countOfA = itemList.get(SKU_A);
						if (countOfA != 0)
							totalAmount = totalAmount + ((countOfA / INT_3) * INT_130 + ((countOfA % INT_3) * itemPrice.get(SKU_A)));
					}
					// 2 of B's for 45
					if (itemList.containsKey(SKU_B)) {
						int countOfB = itemList.get(SKU_B);
						if (countOfB != 0)
							totalAmount = totalAmount + ((countOfB / INT_2) * INT_45 + (countOfB % INT_2 * itemPrice.get(SKU_B)));

					}
				}
				if (null != promotion.getType() && promotion.getType().equals("PromoB")) {
					// C & D for 30
					int countOfC = 0;
					int countOfD = 0;
					if (itemList.containsKey(SKU_C)) {
						countOfC = itemList.get(SKU_C);
					}
					if (itemList.containsKey(SKU_D)) {

						countOfD = itemList.get(SKU_D);
					}
					if (countOfC != 0 && countOfD != 0) {
						if (countOfC == countOfD) {
							totalAmount = totalAmount + countOfC * INT_30;
						} else if (countOfC > countOfD) {
							totalAmount = totalAmount + countOfD * INT_30 + (countOfC - countOfD) * itemPrice.get(SKU_C);
						} else {
							totalAmount = totalAmount + countOfC * INT_30 + (countOfD - countOfC) * itemPrice.get(SKU_D);
						}
					} else {
						totalAmount = totalAmount + countOfC * itemPrice.get(SKU_C) + countOfD * itemPrice.get(SKU_D);
					}
				}
			}
		
			}
			
		}
		
		return totalAmount;
	}

	

}
