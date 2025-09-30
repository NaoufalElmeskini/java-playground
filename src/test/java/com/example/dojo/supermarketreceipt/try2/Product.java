package com.example.dojo.supermarketreceipt.try2;

import lombok.Getter;

@Getter
public enum Product {
	TOOTHBRUSH(0.99, 3	),
	CHERRY_TOMATOES(0.99, 0),
	TOOTHPASTE(1.79, 5);
	private final Double price;
	private final int minimumDiscountUnit;

	Product(double price, int minimumDiscountUnit) {
		this.price = price;
		this.minimumDiscountUnit = minimumDiscountUnit;
	}
}
