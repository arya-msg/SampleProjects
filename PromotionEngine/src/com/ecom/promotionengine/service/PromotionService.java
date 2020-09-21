/**
 * 
 */
package com.ecom.promotionengine.service;

import java.util.List;

import com.ecom.promotionengine.service.domain.Cart;
import com.ecom.promotionengine.service.domain.Product;

/**
 * The interface for promotion service.
 * @author arya
 *
 */
public interface PromotionService {

	public double applyPromotion(Cart cart, List<Product> productList);

}
