/**
 * 
 */
package com.ecom.promotionengine.service;

import com.ecom.promotionengine.service.domain.Cart;

/**
 * The interface for promotion service.
 * @author arya
 *
 */
public interface PromotionService {

	public double applyPromotion(Cart cart);

}
