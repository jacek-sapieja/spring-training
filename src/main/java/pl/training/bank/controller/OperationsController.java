package pl.training.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.training.bank.common.Mapper;
import pl.training.bank.operation.Operation;
import pl.training.bank.operation.OperationResolver;
import pl.training.bank.service.AccountsService;
import pl.training.bank.viewmodel.OperationViewModel;

@RequestMapping("operation.html")
@Controller
public class OperationsController {

    private static String OPERATION_SUFFIX = "Operation";

    private OperationResolver operationResolver;
    private AccountsService accountsService;
    private Mapper mapper;

    @Autowired
    public OperationsController(OperationResolver operationResolver, AccountsService accountsService, Mapper mapper) {
        this.operationResolver = operationResolver;
        this.accountsService = accountsService;
        this.mapper = mapper;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showOperationForm() {
        return new ModelAndView("operation", "operation", new OperationViewModel());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView process(OperationViewModel operationViewModel) {
        Operation operation = operationResolver.get(operationViewModel.getType() + OPERATION_SUFFIX);
        mapper.map(operationViewModel, operation);
        accountsService.process(operation);
        ModelAndView modelAndView = new ModelAndView("operationSuccess");
        modelAndView.addObject("summary", operation.toString());
        return modelAndView;
    }

}
