package com.example.dojo.supermarketreceipt.try2;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;

public class SuperMarket2 {

	private static Map<Product, BigDecimal> discountMap = Map.of(
			Product.TOOTHBRUSH, BigDecimal.valueOf(0.99),
			Product.TOOTHPASTE,	BigDecimal.valueOf(1.46));


	public static Reciept processReciept(Cart shoppingCart) {
		BigDecimal price = BigDecimal.ZERO;
		BigDecimal discount = BigDecimal.ZERO;

		BigDecimal accumulatedPrice = Arrays.stream(Product.values())
				.filter(shoppingCart.getProductQuantities()::containsKey).map(value -> {
					BigDecimal priceOf1Product = BigDecimal.valueOf(shoppingCart.getProductQuantities().get(value))
							.multiply(BigDecimal.valueOf(value.getPrice()));
					return priceOf1Product;
				})
				.reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

		discount = Arrays.stream(Product.values())
				.filter(key -> shoppingCart.getProductQuantities().containsKey(key)
						&& key.getMinimumDiscountUnit() > 0)
				.map(key -> {
					Integer quantite = shoppingCart.getProductQuantities().get(key);
					return discountMap.get(key).multiply(BigDecimal.valueOf(quantite / key.getMinimumDiscountUnit()));
				}).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
		price = accumulatedPrice.subtract(discount);

		return Reciept.builder().price(price.doubleValue()).discount(discount.doubleValue()).build();
	}
}
