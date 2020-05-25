package com.batch.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.batch.listener.JobNotificationsListener;
import com.batch.model.Product;
import com.batch.processor.ProductItemProcessor;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private DataSource datasource;
	
	@Bean
	public FlatFileItemReader<Product> reader()
	{
		FlatFileItemReader<Product> reader=new FlatFileItemReader<Product>();
		reader.setResource(new ClassPathResource("product.csv"));
		reader.setLineMapper(new DefaultLineMapper<Product>() {{
			setLineTokenizer(new DelimitedLineTokenizer() {{
				setNames(new String[] {"id","name","description","price"});
			}});
			setFieldSetMapper(new BeanWrapperFieldSetMapper<Product>() {{
				setTargetType(Product.class);
			}});
		}});
		return reader;
	}
	@Bean
	public ProductItemProcessor processor()
	{
		return new ProductItemProcessor();
	}
	@Bean 
	public JdbcBatchItemWriter<Product> writer()
	{
		JdbcBatchItemWriter<Product> writer=new JdbcBatchItemWriter<Product>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Product>());
		writer.setSql("INSERT INTO product (product_id,name,description,price) VALUES(:id,:name,:description,:price)");
		writer.setDataSource(datasource);
		return writer;
	}
	@Bean
	public Job importJob(JobNotificationsListener listener)
	{
		return  jobBuilderFactory.get("importJob")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.flow(step1())
				.end()
				.build();
				
	}
	private Step step1() {
		// TODO Auto-generated method stub
		return stepBuilderFactory.get("step1")
				.<Product,Product>chunk(10)
				.reader(reader())
				.processor(processor())
				.writer(writer())
				.build();
	}

}
