package pl.training.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.training.bank.common.Mapper;
import pl.training.bank.entity.Customer;
import pl.training.bank.service.CustomersService;
import pl.training.bank.viewmodel.CustomerViewModel;

import javax.validation.Valid;

@RequestMapping("customer.html")
@Controller
public class CustomersController {

    private CustomersService customersService;
    private Mapper mapper;

    @Autowired
    public CustomersController(CustomersService customersService, Mapper mapper) {
        this.customersService = customersService;
        this.mapper = mapper;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showCustomerForm() {
        return new ModelAndView("customer", "customerViewModel", new CustomerViewModel());
    }

    @RequestMapping(method = RequestMethod.POST)
    public String register(@Valid  CustomerViewModel customerViewModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "customer";
        }
        Customer customer = mapper.map(customerViewModel, Customer.class);
        customersService.add(customer);
        return "login";
    }

}
