package com.ecom.promotionengine.service.impl;

import java.util.List;

import com.ecom.promotionengine.service.PromotionService;
import com.ecom.promotionengine.service.domain.Cart;
import com.ecom.promotionengine.service.domain.Product;
import com.ecom.promotionengine.service.domain.Promotion;

public class PromotionServiceImpl implements PromotionService {

	@Override
	public double applyPromotion(Cart cart, List<Product> productList, List<Promotion> promotionsToBeApplied) {
		// TODO Auto-generated method stub
		return 100;
	}

}
