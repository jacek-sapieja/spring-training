package pl.training.bank.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import pl.training.bank.operation.ConsoleOperationLogger;
import pl.training.bank.service.AccountNumberGenerator;
import pl.training.bank.service.AccountsService;
import pl.training.bank.service.MySqlIncrementalAccountNumberGenerator;
import pl.training.bank.service.OracleIncrementalAccountNumberGenerator;
import pl.training.bank.service.repository.AccountsRepository;

import javax.sql.DataSource;

@Import(Repository.class)
@EnableAspectJAutoProxy
@Configuration
public class Beans {

    //@Bean
    public AccountNumberGenerator accountNumberGenerator(DataSource mySqlDataSource) {
        return new MySqlIncrementalAccountNumberGenerator(mySqlDataSource);
    }

    @Bean
    public AccountNumberGenerator oracleAccountNumberGenerator(DataSource oracleDataSource) {
        return new OracleIncrementalAccountNumberGenerator(oracleDataSource);
    }

    //@Scope(BeanDefinition.SCOPE_PROTOTYPE)
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public AccountsService accountsService(@Qualifier("oracleAccountsRepository") AccountsRepository accountsRepository,
                                           @Qualifier("oracleAccountNumberGenerator") AccountNumberGenerator accountNumberGenerator) {
        return new AccountsService(accountsRepository, accountNumberGenerator);
    }

    @Bean
    public ConsoleOperationLogger operationLogger() {
        return new ConsoleOperationLogger();
    }

}
