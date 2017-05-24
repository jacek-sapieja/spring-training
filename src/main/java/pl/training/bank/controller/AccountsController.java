package pl.training.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.training.bank.common.UriBuilder;
import pl.training.bank.dto.AccountDto;
import pl.training.bank.dto.DtoMapper;
import pl.training.bank.dto.ExceptionDto;
import pl.training.bank.entity.Account;
import pl.training.bank.service.AccountsService;
import pl.training.bank.service.repository.AccountNotFoundException;

import java.net.URI;
import java.util.Locale;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.created;

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

    /*@ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity onAccountNotFound(AccountNotFoundException ex, Locale locale) {
        return new ResponseEntity(dtoMapper.map(ex, locale), NOT_FOUND);
    }*/

}
