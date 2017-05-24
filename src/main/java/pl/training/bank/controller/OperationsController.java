package pl.training.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.training.bank.dto.DtoMapper;
import pl.training.bank.dto.OperationDto;
import pl.training.bank.operation.Operation;
import pl.training.bank.operation.OperationResolver;
import pl.training.bank.service.AccountsService;

import static org.springframework.http.ResponseEntity.noContent;

@RequestMapping("api-v1/operations")
@CrossOrigin
@RestController
public class OperationsController {

    private AccountsService accountsService;
    private OperationResolver operationResolver;
    private DtoMapper dtoMapper;

    @Autowired
    public OperationsController(AccountsService accountsService, OperationResolver operationResolver, DtoMapper dtoMapper) {
        this.accountsService = accountsService;
        this.operationResolver = operationResolver;
        this.dtoMapper = dtoMapper;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity process(@RequestBody OperationDto operationDto) {
        Operation operation = operationResolver.get(operationDto.getType());
        dtoMapper.map(operationDto, operation);
        accountsService.process(operation);
        return noContent().build();
    }

}
