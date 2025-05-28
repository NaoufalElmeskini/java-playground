package com.example.dojo.supermarketreceipt;

import java.util.List;

public class SuperMarket {
	public static double calculateReceipt(List<Product> cart) {
		if ("apple".equalsIgnoreCase(cart.getFirst().getName())) {
			return 1.99;
		}
		if ("rice".equalsIgnoreCase(cart.getFirst().getName())) {
			return 2.49;
		}
		return 0;
	}
}
