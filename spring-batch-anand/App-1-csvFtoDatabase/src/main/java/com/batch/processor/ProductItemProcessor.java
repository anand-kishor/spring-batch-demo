package com.batch.processor;

import org.springframework.batch.item.ItemProcessor;

import com.batch.model.Product;

public class ProductItemProcessor implements ItemProcessor<Product,Product> {

	@Override
	public Product process(Product item) throws Exception {
		// TODO Auto-generated method stub
		String name=item.getName().toUpperCase();
		String description=item.getDescription().toUpperCase();
		Product product=new Product();
		System.out.println("product id ="+product.getId()+"name of product="+name+"description="+description+"price of itom="+product.getPrice());
		return product;
	}

}
