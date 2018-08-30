package fr.formation.lamarmite;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.*;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.*;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.jpa.JpaTransactionManager;

import fr.formation.lamarmite.entities.*;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final EntityManagerFactory emf;

    private final DataSource dataSource;

    private final JpaTransactionManager transactionManager;

    private final FieldSetMapper<User> userMapper;

    private final FieldSetMapper<Civility> civilityMapper;

    @Autowired
    protected BatchConfig(EntityManagerFactory emf, DataSource dataSource,
	    JpaTransactionManager transactionManager,
	    FieldSetMapper<User> userMapper,
	    FieldSetMapper<Civility> civilityMapper) {
	this.emf = emf;
	this.dataSource = dataSource;
	this.transactionManager = transactionManager;
	this.userMapper = userMapper;
	this.civilityMapper = civilityMapper;
    }

    @Bean
    public JobBuilderFactory jobBuilderFactory() throws Exception {
	return new JobBuilderFactory(jobRepository());
    }

    @Bean
    public StepBuilderFactory stepBuilderFactory() throws Exception {
	return new StepBuilderFactory(jobRepository(), transactionManager);
    }

    @Bean
    public JpaItemWriter<String> writer() {
	JpaItemWriter<String> writer = new JpaItemWriter<>();
	writer.setEntityManagerFactory(emf);
	return writer;
    }

    @Bean
    public JobRepository jobRepository() throws Exception {
	JobRepositoryFactoryBean factoryBean = new JobRepositoryFactoryBean();
	factoryBean.setTransactionManager(transactionManager);
	factoryBean.setDataSource(dataSource);
	return factoryBean.getObject();
    }

    @Bean
    public JobLauncher jobLauncher() throws Exception {
	SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
	jobLauncher.setJobRepository(jobRepository());
	return jobLauncher;
    }

    @Bean
    public JobExecutionListener listener() {
	return new JobExecutionListener() {

	    @Override
	    public void beforeJob(JobExecution jobExecution) {
		System.out.println("Job starting with status: "
			+ jobExecution.getStatus());
	    }

	    @Override
	    public void afterJob(JobExecution jobExecution) {
		System.out.println(
			"Job ended with status: " + jobExecution.getStatus());
	    }
	};
    }

    @Bean
    public FlatFileItemReader<User> importUserReader() {
	FlatFileItemReader<User> reader = new FlatFileItemReader<>();
	reader.setResource(new ClassPathResource("users.csv"));
	reader.setLinesToSkip(1);
	DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
	tokenizer.setNames("civility_code", "lastname", "firstname", "email",
		"password", "role");
	DefaultLineMapper<User> lineMapper = new DefaultLineMapper<>();
	lineMapper.setFieldSetMapper(userMapper);
	lineMapper.setLineTokenizer(tokenizer);
	reader.setLineMapper(lineMapper);
	return reader;
    }

    @Bean
    public FlatFileItemReader<Civility> importCivilityReader() {
	FlatFileItemReader<Civility> reader = new FlatFileItemReader<>();
	reader.setResource(new ClassPathResource("civilities.csv"));
	reader.setLinesToSkip(1);
	DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
	tokenizer.setNames("code", "abbreviationEn", "abbreviationFr");
	DefaultLineMapper<Civility> lineMapper = new DefaultLineMapper<>();
	lineMapper.setFieldSetMapper(civilityMapper);
	lineMapper.setLineTokenizer(tokenizer);
	reader.setLineMapper(lineMapper);
	return reader;
    }

    @Bean
    public Job importUsersJob(JobExecutionListener listener) throws Exception {
	return jobBuilderFactory().get("importUsersJob")
		.incrementer(new RunIdIncrementer()).listener(listener)
		.flow(importUsersStep()).end().build();
    }

    @Bean
    public Step importUsersStep() throws Exception {
	return stepBuilderFactory().get("importUsersStep")
		.<User, String>chunk(10).reader(importUserReader())
		.writer(writer()).build();
    }

    @Bean
    public Job importCivilitiesJob(JobExecutionListener listener)
	    throws Exception {
	return jobBuilderFactory().get("importCivilitiesJob")
		.incrementer(new RunIdIncrementer()).listener(listener)
		.flow(imporCivilitiesStep()).end().build();
    }

    @Bean
    public Step imporCivilitiesStep() throws Exception {
	return stepBuilderFactory().get("imporCivilitiesStep")
		.<Civility, String>chunk(10).reader(importCivilityReader())
		.writer(writer()).build();
    }
}
