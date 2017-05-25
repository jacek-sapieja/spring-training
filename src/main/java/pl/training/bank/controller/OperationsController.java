package pl.training.bank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.training.bank.viewmodel.Operation;

@RequestMapping("operation.html")
@Controller
public class OperationsController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showOperationForm() {
        return new ModelAndView("operation", "operation", new Operation());
    }

}
