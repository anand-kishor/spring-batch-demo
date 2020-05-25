package com.batch.listener;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import com.batch.model.Product;

public class ProductRowMapper implements RowMapper<Product> {

	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Product product=new Product();
		//product.setId(rs.getInt(1));
		product.setId(rs.getInt(1));
		product.setName(rs.getString(2));
		product.setDescription(rs.getString(3));
		product.setPrice(rs.getInt(4));
		
		return product;
	}


}
