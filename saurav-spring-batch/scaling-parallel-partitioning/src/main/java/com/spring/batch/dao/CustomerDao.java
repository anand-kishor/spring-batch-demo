package com.spring.batch.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.spring.batch.model.Customer;
@Repository
public class CustomerDao extends JdbcDaoSupport {
	 
	  @Autowired
	  DataSource dataSource;
	 
	  @PostConstruct
	  private void initialize() {
	    setDataSource(dataSource);
	  }
	 
	  public void insert(List<? extends Customer> Customers) {
	    String sql = "INSERT INTO customer " + "(id, firstname, lastname) VALUES (?, ?, ?)";
	    getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
	      public void setValues(PreparedStatement ps, int i) throws SQLException {
	        Customer customer = Customers.get(i);
	        ps.setLong(1, customer.getId());
	        ps.setString(2, customer.getFirstName());
	        ps.setString(3, customer.getLastName());
	      }
	 
	      public int getBatchSize() {
	        return Customers.size();
	      }
	    });
	  }
}
