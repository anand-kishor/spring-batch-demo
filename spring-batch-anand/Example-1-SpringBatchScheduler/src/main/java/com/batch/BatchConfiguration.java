package com.batch;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import com.batch.items.CustomItemProcessor;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	private DataSource dataSource;
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Bean
	public DataSource datasource()
	{
		final DriverManagerDataSource ds=new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3307/ramanand");
		ds.setUsername("root");
		ds.setPassword("root");
		return ds;
	}
	@Bean
	public StaxEventItemReader<User> reader()
	{
		StaxEventItemReader reader=new StaxEventItemReader();
		Resource resource=resourceLoader.getResource("file:F:/text/user.xml");
		reader.setResource(resource);
		reader.setFragmentRootElementName("user");
		Map<String,String> aliases=new HashMap<String,String>();
		aliases.put("user", "com.batch.User");
		
		XStreamMarshaller xstreamMarshaller=new XStreamMarshaller();
		xstreamMarshaller.setAliases(aliases);
		reader.setUnmarshaller(xstreamMarshaller);
		
		return reader;
	}
	@Bean
	public CustomItemProcessor process()
	{
		return new CustomItemProcessor();
	}
	public JdbcBatchItemWriter<User> writer()
	{
		JdbcBatchItemWriter writer=new JdbcBatchItemWriter();
		writer.setDataSource(dataSource);
		writer.setSql("insert into user (name,createdDate) values(?,?)");
		writer.setItemPreparedStatementSetter(new UserItemPreparedStm());
		return writer;
	}
	private class UserItemPreparedStm implements ItemPreparedStatementSetter<User>{

		@Override
		public void setValues(User user, PreparedStatement ps) throws SQLException {
			// TODO Auto-generated method stub
		ps.setString(1, user.getName());
		ps.setTimestamp(2,user.getCreatedDate());
			
		}
}
	@Bean
	public Step step1()
	{
		return stepBuilderFactory.get("step1")
				.<User,User>chunk(10)
				.reader(reader())
				.processor(process())
				.writer(writer())
				.build();
	}
	@Bean
	public Job importUserJob()
	{
		return jobBuilderFactory.get("importUserJob")
				.incrementer(new RunIdIncrementer())
				.flow(step1())
				.end()
				.build();
	}

}
