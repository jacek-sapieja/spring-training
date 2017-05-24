package pl.training.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.training.bank.dto.DtoMapper;
import pl.training.bank.service.repository.AccountNotFoundException;

import java.util.Locale;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class GlobalExceptionHandler {

    private DtoMapper dtoMapper;

    @Autowired
    public GlobalExceptionHandler(DtoMapper dtoMapper) {
        this.dtoMapper = dtoMapper;
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity onAccountNotFoundException(AccountNotFoundException ex, Locale locale) {
        return new ResponseEntity(dtoMapper.map(ex, locale), NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity onException(Exception ex, Locale locale) {
        ex.printStackTrace();
        System.out.println("################");
        return new ResponseEntity(dtoMapper.map(ex, locale), INTERNAL_SERVER_ERROR);
    }

}
