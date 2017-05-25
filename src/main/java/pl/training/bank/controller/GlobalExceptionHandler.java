package pl.training.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

@ControllerAdvice
public class GlobalExceptionHandler {

    private MessageSource messageSource;

    @Autowired
    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView onException(Exception ex, Locale locale) {
        ex.printStackTrace();
        return new ModelAndView("error", "description", getDescription(ex, locale));
    }

    private String getDescription(Exception ex, Locale locale) {
        return messageSource.getMessage(ex.getClass().getSimpleName(), null, locale);
    }

}
