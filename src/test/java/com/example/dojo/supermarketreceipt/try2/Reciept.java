package com.example.dojo.supermarketreceipt.try2;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Reciept {
	private final double price;
	private final double discount;

}
