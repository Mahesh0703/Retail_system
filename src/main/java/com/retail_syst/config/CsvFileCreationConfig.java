package com.retail_syst.config;



import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import com.retail_syst.rowmapper.RetailItemProcessor;
import com.retail_syst.rowmapper.RetailRowMapper;
import com.retail_syst.vo.RetailItems;

@Configuration
@EnableBatchProcessing
public class CsvFileCreationConfig {


	@Autowired
	private JobBuilderFactory jobbuilderfactory;
	
	@Autowired
	private StepBuilderFactory stepbuilderfactory;
	
	@Autowired
	private DataSource datasource;
	
	@Bean
	public JdbcCursorItemReader<RetailItems> reader(){
		System.out.println("Enter into CsvFileCreationConfig.reader() :: ");
		JdbcCursorItemReader<RetailItems> cursorItemReader= new JdbcCursorItemReader<>();
		cursorItemReader.setDataSource(datasource);
		cursorItemReader.setSql("SELECT item_id, item_name, item_category, item_unit_rate, item_unit_qty, item_total_price FROM doc_retail_item");
		cursorItemReader.setRowMapper(new RetailRowMapper ());
		
		System.out.println("Exit into CsvFileCreationConfig.reader() :: ");
		return cursorItemReader;
		
		
	}
	
	@Bean
	public RetailItemProcessor processer() {
		return new RetailItemProcessor (); 
	}
	
	@Bean
	public FlatFileItemWriter<RetailItems> writer(){
		
		System.out.println("Enter into CsvFileCreationConfig.reader() :: ");
		
		FlatFileItemWriter<RetailItems>writer= new FlatFileItemWriter<>();
		writer.setResource(new ClassPathResource("retail_system.csv"));
		
		DelimitedLineAggregator<RetailItems> lineAggregator=new DelimitedLineAggregator<>();
		lineAggregator.setDelimiter(",");
		
		BeanWrapperFieldExtractor<RetailItems> fieldExtractor =new BeanWrapperFieldExtractor<RetailItems>();
		fieldExtractor.setNames(new String [] { "Id","name","category","unitRate","qty","unitTotalPrice"});
		lineAggregator.setFieldExtractor(fieldExtractor);
		
		writer.setLineAggregator(lineAggregator);
		
		System.out.println("Exit into CsvFileCreationConfig.reader() :: ");
		
		return writer;
	}
	
	@Bean
	public Step step1() {
		 return stepbuilderfactory.get("step1").<RetailItems , RetailItems > chunk(100).reader(reader()).processor(processer()).writer(writer()).build();
		
	}
	
	@Bean
	public Job exportRetailJob() {
		return jobbuilderfactory.get("exportRetailJob").incrementer(new RunIdIncrementer()).flow(step1()).end().build();
	}
	
	
	
}
