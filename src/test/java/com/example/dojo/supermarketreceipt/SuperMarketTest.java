package com.example.dojo.supermarketreceipt;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Supermarket Receipt Kata
 * link : https://sammancoaching.org/kata_descriptions/supermarket_receipt.html
 *
 * Write some code that could be used in a supermarket to calculate the total cost of items in a shopping cart and provide a receipt to the customer.
 *
 * The supermarket has a catalog with different types of products (rice, apples, milk, toothbrushes,…). Each product has a price, and the total price of the shopping cart is the total of all the prices of the items. You get a receipt that details the items you’ve bought, the total price, and any discounts that were applied.
 *
 * The supermarket runs special deals, e.g.
 *
 *     Buy two toothbrushes, get one free. Normal toothbrush price is €0.99
 *     20% discount on apples, normal price €1.99 per kilo.
 *     10% discount on rice, normal price €2.49 per bag
 *     Five tubes of toothpaste for €7.49, normal price €1.79
 *     Two boxes of cherry tomatoes for €0.99, normal price €0.69 per box.
 *
 * These are just examples: the actual special deals change each week.
 *
 * input -> output
 * "rice" x1 -> 2.49
 * "apples" x1 -> 1.99
 *
 * "apples" x2 -> 3.98
 * "apples" x2, "rice" x1 -> 6.47
 *
 * langage : cart, recu, prixTotal, catalogue
 *
 */
public class SuperMarketTest {

	@Test
	public void testEmptyCart() {
		List cart = List.of();
		assertThat(SuperMarket.calculateReceipt(cart))
				.isEqualTo(0.0);
	}

	@Test
	public void testCartWithRice() {
		List cart = List.of(Product.getRice(1));
		assertThat(SuperMarket.calculateReceipt(cart))
				.isEqualTo(2.49);
	}

	@Test
	public void testCartWithApples() {
		List cart = List.of(Product.getApples(1));
		assertThat(SuperMarket.calculateReceipt(cart))
				.isEqualTo(1.99);
	}






}
