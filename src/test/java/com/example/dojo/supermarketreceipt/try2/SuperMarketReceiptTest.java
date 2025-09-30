package com.example.dojo.supermarketreceipt.try2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/*
	source : https://sammancoaching.org/kata_descriptions/supermarket_receipt.html
 */
public class SuperMarketReceiptTest {

	/*
	{} -> {price: 0, discount: 0},
	{toothpaste x1, } -> {price: 1.79, discount: 0},
	{tootbrush x1, } -> {price: 0.99, discount: 0},
	{ cherryTomatoes x1, } -> {price: 1.79, discount: 0},

	objectif : design test suite
		panier final : {toothbrush x2, toothpaste x5, rice x12, produitN x qteN}
		reciept final : { price : 20.20, discount : 29.10 }
	constat : 3 parametre input = nombre de produits differents, nom de produits differents, quantite d'un produit
	plan :
		- varier D1 = type de produit
		- varier D2 = quantite de produit
		- varier D3 = nombre de produit differents dans le panier


		D1 :
			{toothpaste x1, } -> {price: 1.79, discount: 0},
			{tootbrush x1, } -> {price: 0.99, discount: 0},
			{ cherryTomatoes x1, } -> {price: 0.99, discount: 0},

		D2 :
			{toothpaste x3} -> {}
			{toothBrush x3} -> {}
			{cherryTomatoes x3} -> {}

		D3 :
			{toothpaste x1, toothBrush x1} -> {}
			{toothpaste x1, cherryTomatoes x3} -> {}





	{toothpaste x2, } -> {price: 3.58, discount: 0},



	{tootbrush x3, } -> {price: 1.98, discount: 0.99},
	{tootbrush x2, } -> {price: 1.98, discount: 0.99},
	 */
	//	input -> output
	@Test
	@DisplayName("should rocess reciept when i have empty cart")
	public void whenITryToXXX() {
		//    when
		Cart shoppingCart = Cart.builder().build();
		Reciept result = SuperMarket2.processReciept(shoppingCart);
		//    then
		assertThat(result.getPrice()).isEqualTo(0);
		assertThat(result.getDiscount()).isEqualTo(0);
	}

	@ParameterizedTest
	@DisplayName("should process reciept when i have 1 product")
	@CsvSource({ "toothpaste, 1, 1.79, 0",
			"toothbrush, 1, 0.99, 0",
			"cherry_Tomatoes, 1, 0.99, 0",
	})
	public void whenITryToProcessRecieptForShoppingCartWith1ProductTypeWith1Quantite(String inputProduct,
			int inputQuantity, double expectedPrice, double expectedDiscount) {
		//    when
		Cart cartWith1Product = Cart.builder().build().add(getProductFromName(inputProduct), inputQuantity);

		Reciept result = SuperMarket2.processReciept(cartWith1Product);
		//    then
		assertThat(result.getPrice()).isEqualTo(expectedPrice);
		assertThat(result.getDiscount()).isEqualTo(expectedDiscount);
	}

	private Product getProductFromName(String inputProduct) {
		return Arrays.stream(Product.values()).filter(product -> product.name().equalsIgnoreCase(inputProduct))
				.findFirst().orElseThrow(() -> new IllegalArgumentException("Product not found : " + inputProduct));
	}

	@ParameterizedTest
	@DisplayName("should process reciept when I have multiple products of same type")
	@CsvSource({
			"toothbrush, 1, 0.99, 0",
			"toothbrush, 2, 1.98, 0",
			"toothbrush, 3, 1.98, 0.99",
			"toothbrush, 4, 2.97, 0.99",
						"toothbrush, 5, 3.96, 0.99",
						"toothbrush, 6, 3.96, 1.98",
						"toothbrush, 9, 5.94, 2.97",
						"toothpaste, 5, 7.49, 1.46",
						"toothpaste, 6, 9.28, 1.46",
						"toothpaste, 11, 16.77, 2.92",

	})
	public void whenITryToProcessRecieptForShoppingCartWithIHaveMultipleProductsofSameType(String inputProduct,
			int inputQuantity, double expectedPrice, double expectedDiscount) {
		//    when
		Cart cartWith1Product = Cart.builder().build()
				.add(getProductFromName(inputProduct), inputQuantity);

		Reciept result = SuperMarket2.processReciept(cartWith1Product);
		//    then
		assertThat(result.getDiscount()).isEqualTo(expectedDiscount);
		assertThat(result.getPrice()).isEqualTo(expectedPrice);
	}
}
