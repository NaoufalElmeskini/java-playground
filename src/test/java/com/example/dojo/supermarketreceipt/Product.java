package com.example.dojo.supermarketreceipt;

import lombok.Getter;

@Getter
public class Product {
	private String name;
	private int quatity;

	private Product() {
	}
	private Product(String name, int quatity) {
		this.name = name;
		this.quatity = quatity;
	}

	public static Product getRice(int quatity) {
		return new Product("rice", quatity);
	}

	public static Object getApples(int quatity) {
		return new Product("apple", quatity);
	}
}
