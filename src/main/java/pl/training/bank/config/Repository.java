package pl.training.bank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pl.training.bank.service.repository.AccountsRepository;
import pl.training.bank.service.repository.MySqlAccountsRepository;
import pl.training.bank.service.repository.OracleAccountsRepository;

import javax.sql.DataSource;

@EnableTransactionManagement
@PropertySource("classpath:jdbc.properties")
@Configuration
public class Repository {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource mySqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername(environment.getProperty("mysql.username"));
        dataSource.setPassword(environment.getProperty("mysql.password"));
        dataSource.setUrl(environment.getProperty("mysql.url"));
        dataSource.setDriverClassName(environment.getProperty("mysql.driver"));
        return dataSource;
    }

    @Bean
    public DataSource oracleDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername(environment.getProperty("oracle.username"));
        dataSource.setPassword(environment.getProperty("oracle.password"));
        dataSource.setUrl(environment.getProperty("oracle.url"));
        dataSource.setDriverClassName(environment.getProperty("oracle.driver"));
        return dataSource;
    }

    @Bean
    public AccountsRepository accountsRepository(DataSource mySqlDataSource) {
        return new MySqlAccountsRepository(mySqlDataSource);
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource mySqlDataSource) {
        return new DataSourceTransactionManager(mySqlDataSource);
    }

    @Bean
    public PlatformTransactionManager oracleTransactionManager(@Qualifier("oracleDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public AccountsRepository oracleAccountsRepository(DataSource oracleDataSource) {
        return new OracleAccountsRepository(oracleDataSource);
    }

}
