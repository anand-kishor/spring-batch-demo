package com.batch.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.batch.model.Product;


public class ProductItemProcessor implements ItemProcessor<Product,Product> {
	private static final Logger log=LoggerFactory.getLogger(ProductItemProcessor.class);
	@Override
	public Product process(Product product) throws Exception {
		final String nameOfProduct=product.getName().toUpperCase();
		final String emailAddress=product.getEmail().toUpperCase();
		final Product productItem=new Product(product.getId(),nameOfProduct,emailAddress,product.getSalary());
		log.info("converting ("+product+") into ("+productItem+")");
		return productItem;

	}

}
