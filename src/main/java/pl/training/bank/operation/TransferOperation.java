package pl.training.bank.operation;

import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;

import java.util.ArrayList;
import java.util.List;

public class TransferOperation extends Operation {

    private List<Operation> operations = new ArrayList<>();

    public TransferOperation(String sourceAccountNumber, String destinationAccountNumber, long funds) {
        super(sourceAccountNumber, funds);
        this.destinationAccountNumber = destinationAccountNumber;
    }

    @Override
    public void execute() {
        WithdrawOperation withdrawOperation = new WithdrawOperation(sourceAccountNumber, funds);
        withdrawOperation.setAccountsRepository(accountsRepository);
        withdrawOperation.execute();
        operations.add(withdrawOperation);

        DepositOperation depositOperation = new DepositOperation(destinationAccountNumber, funds);
        depositOperation.setAccountsRepository(accountsRepository);
        depositOperation.execute();
        operations.add(depositOperation);
    }

    @Override
    public List<Operation> getSubOperations() {
        return operations;
    }

    @Override
    public String toString() {
        return String.format("%s ==> %s ==> %s", sourceAccountNumber, formatCurrency(funds), destinationAccountNumber);
    }

}
