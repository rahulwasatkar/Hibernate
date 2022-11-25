package com.rahul.service;

import java.util.Scanner;

import com.rahul.entity.Product;

public class ProductService {

	public static Product serviceProduct() {

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter id");
		int id = sc.nextInt();
		System.out.println("Enter name");
		String name = sc.next();
		System.out.println("Enter price");
		double price = sc.nextDouble();
		System.out.println("Enter type");
		String type = sc.next();

		Product product= new Product(id, name, price, type);
		return product;

	}

	
}
