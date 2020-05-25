package com.batch.items;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.batch.item.ItemProcessor;

import com.batch.User;

public class CustomItemProcessor implements ItemProcessor<User, User> {

	@Override
	public User process(User user) throws Exception {
		// TODO Auto-generated method stub
		Timestamp createdDate=new Timestamp(new Date().getTime());
		user.setCreatedDate(createdDate);
		return user;
	}

}
