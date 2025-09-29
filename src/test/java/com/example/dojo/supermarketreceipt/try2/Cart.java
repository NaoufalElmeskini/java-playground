package com.example.dojo.supermarketreceipt.try2;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	@Getter
	private Map<Product, Integer> productQuantities = new HashMap<Product, Integer>();

	Cart(Map<Product, Integer> productQuantities) {
		this.productQuantities = productQuantities;
	}

	public static CartBuilder builder() {
		return new CartBuilder();
	}

	public Cart add(Product product, int quantity) {
		productQuantities.put(product, quantity);
		return this;
	}

	public static class CartBuilder {
		private Map<Product, Integer> products = new HashMap<>();

		CartBuilder() {
		}

		public CartBuilder products(Map<Product, Integer> products) {
			this.products = products;
			return this;
		}

		public Cart build() {
			return new Cart(this.products);
		}

		public String toString() {
			return "Cart.CartBuilder(products=" + this.products + ")";
		}
	}
}
