package pl.training.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.training.bank.common.UriBuilder;
import pl.training.bank.dto.AccountDto;
import pl.training.bank.dto.AccountsPageDto;
import pl.training.bank.dto.DtoMapper;
import pl.training.bank.entity.Account;
import pl.training.bank.service.AccountsService;
import pl.training.bank.service.repository.ResultPage;

import java.net.URI;
import java.util.List;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;

@CrossOrigin
@RequestMapping(value = "api-v1/accounts")
@RestController
public class AccountsController {

    private AccountsService accountsService;
    private DtoMapper dtoMapper;
    private UriBuilder uriBuilder = new UriBuilder();

    @Autowired
    public AccountsController(AccountsService accountsService, DtoMapper dtoMapper) {
        this.accountsService = accountsService;
        this.dtoMapper = dtoMapper;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createAccount() {
        Account account = accountsService.createAccount();
        URI uri = uriBuilder.requestUriWithId(account.getId());
        return created(uri).build();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public AccountDto getAccount(@PathVariable("id") Long id) {
        Account account = accountsService.getAccount(id);
        return dtoMapper.map(account, AccountDto.class);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteAccount(@PathVariable("id") Long id) {
        accountsService.deleteAccount(id);
        return noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public AccountsPageDto getAccounts(
            @RequestParam(required = false, defaultValue = "0", name = "pageNumber") int pageNumber,
            @RequestParam(required = false, defaultValue = "10", name = "pageSize") int pageSize) {
        ResultPage<Account> resultPage = accountsService.getAccounts(pageNumber, pageSize);
        List<AccountDto> accountDtos = dtoMapper.map(resultPage.getContent(), AccountDto.class);
        return new AccountsPageDto(accountDtos, resultPage.getPageNumber(), resultPage.getTotalPages());
    }

    /*@ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity onAccountNotFound(AccountNotFoundException ex, Locale locale) {
        return new ResponseEntity(dtoMapper.map(ex, locale), NOT_FOUND);
    }*/

}
