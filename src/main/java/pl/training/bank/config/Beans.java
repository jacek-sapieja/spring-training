package pl.training.bank.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ResourceBundleMessageSource;
import pl.training.bank.dto.DtoMapper;
import pl.training.bank.operation.*;
import pl.training.bank.service.AccountNumberGenerator;
import pl.training.bank.service.AccountsService;
import pl.training.bank.service.CustomersService;
import pl.training.bank.service.JpaIncrementalAccountNumberGenerator;
import pl.training.bank.service.repository.AccountsRepository;
import pl.training.bank.service.repository.CustomersRepository;

import javax.persistence.EntityManagerFactory;

@EnableAspectJAutoProxy
@Configuration
public class Beans {

    @Bean
    public AccountNumberGenerator accountNumberGenerator(EntityManagerFactory entityManagerFactory) {
        return new JpaIncrementalAccountNumberGenerator(entityManagerFactory);
    }

    //@Scope(BeanDefinition.SCOPE_PROTOTYPE)
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public AccountsService accountsService(AccountsRepository accountsRepository, AccountNumberGenerator accountNumberGenerator) {
        return new AccountsService(accountsRepository, accountNumberGenerator);
    }

    @Bean
    public CustomersService customersService(CustomersRepository customersRepository) {
        return new CustomersService(customersRepository);
    }

    @Bean
    public ConsoleOperationLogger operationLogger() {
        return new ConsoleOperationLogger();
    }

    @Bean
    public OperationResolver operationResolver() {
        return new OperationResolver();
    }

    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    @Bean
    public DepositOperation depositOperation() {
        return new DepositOperation();
    }

    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    @Bean
    public WithdrawOperation withdrawOperation() {
        return new WithdrawOperation();
    }

    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    @Bean
    public TransferOperation transferOperation() {
        return new TransferOperation();
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasename("errors");
        return messageSource;
    }

    @Bean
    public DtoMapper dtoMapper(MessageSource messageSource) {
        return new DtoMapper(messageSource);
    }

}
