package com.example.dojo.supermarketreceipt.try2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SuperMarketReceiptTest {

	/*
	{} -> {price: 0, discount: 0},
	{tootbrush x1, } -> {price: 0.99, discount: 0},
	{tootbrush x3, } -> {price: 1.98, discount: 0},
	{tootbrush x2, } -> {price: 1.98, discount: 0},
	 */
	//	input -> output
	@Test
	@DisplayName("should XXX when I YYY")
	public void whenITryToXXX() {
		//    when
		Cart shoppingCart = Cart.builder().build();
		Reciept result = SuperMarket2.processReciept(shoppingCart);
		//    then
		assertThat(result.getPrice()).isEqualTo(0);
		assertThat(result.getDiscount()).isEqualTo(0);
	}

	@Test
	@DisplayName("should XXX when I YYY")
	public void whenITryToXXX2() {
		//    when
		Cart cartWith1Product = Cart.builder().build();
		Reciept result = SuperMarket2.processReciept(cartWith1Product);
		//    then
		assertThat(result.getPrice()).isEqualTo(0);
		assertThat(result.getDiscount()).isEqualTo(0);
	}
}
