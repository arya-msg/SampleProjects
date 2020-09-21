package com.ecom.promotionengine.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ecom.promotionengine.service.PromotionService;
import com.ecom.promotionengine.service.domain.Cart;
import com.ecom.promotionengine.service.domain.Product;
import com.ecom.promotionengine.service.domain.Promotion;

public class PromotionServiceImpl implements PromotionService {

	@Override
	public double applyPromotion(Cart cart, List<Product> productList, List<Promotion> promotionsToBeApplied) {
		
		
		double totalAmount = 0;
		if (null != cart && null != cart.getSkuList() && !cart.getSkuList().isEmpty()) {
			
			// to get the count of each item.
			
			Map<Character, Integer> itemList = new HashMap<Character, Integer>();
			cart.getSkuList().stream().distinct().forEach(item -> {
				int count = (int) cart.getSkuList().stream().filter(sku -> sku.equals(item)).count();
				itemList.put(item, count);
			}

			);
			
		
		}
		return totalAmount;
	}

}
