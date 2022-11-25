package com.rahul.controller;

import java.util.List;
import java.util.Scanner;

import com.rahul.dao.ProductDao;
import com.rahul.entity.Product;
import com.rahul.service.ProductService;

public class TestController {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ProductDao productDao = new ProductDao();
		int choice;
		char ch;
		do {
			System.out.println("Enter 1 for save Product ...");
			System.out.println("Enter 2 for get Product ...");
			System.out.println("Enter 3 for getAll Product ...");
			System.out.println("Enter 4 for Update Product ...");
			System.out.println("Enter 5 for Delete Product ...");
			System.out.println("Enter 6 for getproduct by ProductName ...");
			System.out.println("Enter 7 for getProductPrice sum ");
			System.out.println("Enter 8 for delete query ..");
			choice = sc.nextInt();

			switch (choice) {
			case 1:

				Product product = ProductService.serviceProduct();
				boolean isadded = productDao.configureProduct(product);
				if (isadded) {
					System.out.println("product save suuccefully");
				} else {
					System.out.println("product alredy exist or please try again..");
				}
				break;

			case 2:
				System.out.println("Enter the productId..");
				int id = sc.nextInt();
				Product product1 = productDao.getProductByid(id);
				if (product1 != null) {
					System.out.println(product1);
				} else {
					System.out.println("product not found");
				}

				break;

			case 3:
				List<Product> list = productDao.getAllProduct();
				if (!list.isEmpty()) {
					for (Product product2 : list) {
						System.out.println(product2.toString());
					}
				} else {
					System.out.println("ProductList Not available..");

				}
				break;
				
				
			case 4 : 
				Product prod = ProductService.serviceProduct();
				Boolean isupdated = productDao.UpdateProduct(prod);
				if(isupdated) {
					System.out.println("Product updated succssfully..");
				}else {
					System.out.println("product not found..");
				}
				break;
			case 5:
				System.out.println("Enter the Id");
				int productId = sc.nextInt();
				boolean isDeleted = productDao.deleteProduct(productId);
				if(isDeleted) {
					System.out.println("Product deleted succssefully..");
				}else {
					System.out.println("product not found for this id "+productId);
				}
				break;
			case 6 :
				
				System.out.println("Enter the Product name");
				String name = sc.next();
				Product pro = productDao.getProductByName(name);
				if(pro!=null) {
					System.out.println(pro);
				}else {
					System.out.println("product not found for this name "+name);
				}
				break;
				
			case 7:
				System.out.println("Enter for getProductPrice Sum");
				double sum = productDao.getproductSum();
				if(sum>0) {
					System.out.println(sum);	
				}else {
					System.out.println("Product not found..");
				}
				break;
				
			case 8:
				System.out.println("Enter ProductId to delete ..");
				int pId =sc.nextInt();
				int row = productDao.deleteProductQuery(pId);
				if(row>0) {
					System.out.println(row +" deleted");
				}
				
			default:
				break;
			}
			System.out.println("Do you want to continue y/n");
			ch = sc.next().charAt(0);

		} while (ch == 'y' || ch == 'Y');

	}

}
