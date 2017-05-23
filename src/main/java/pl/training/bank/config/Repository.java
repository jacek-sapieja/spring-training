package pl.training.bank.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertySourceFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pl.training.bank.service.repository.AccountsRepository;
import pl.training.bank.service.repository.HibernateAccountsRepository;
import pl.training.bank.service.repository.MySqlAccountsRepository;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@EnableTransactionManagement
@PropertySource("classpath:jdbc.properties")
@Configuration
public class Repository {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(environment.getProperty("database.username"));
        dataSource.setPassword(environment.getProperty("database.password"));
        dataSource.setJdbcUrl(environment.getProperty("database.url"));
        dataSource.setDriverClass(environment.getProperty("database.driver"));
        return dataSource;
    }

    @Bean
    public PropertiesFactoryBean hibernateProperties() {
        PropertiesFactoryBean factoryBean =  new PropertiesFactoryBean();
        factoryBean.setLocation(new ClassPathResource("hibernate.properties"));
        return factoryBean;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource, Properties hibernateProperties) {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setPackagesToScan("pl.training.bank.entity");
        localSessionFactoryBean.setDataSource(dataSource);
        localSessionFactoryBean.setHibernateProperties(hibernateProperties);
        return localSessionFactoryBean;
    }

    @Bean
    public AccountsRepository accountsRepository(SessionFactory sessionFactory) {
        return new HibernateAccountsRepository(sessionFactory);
    }

    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

}
