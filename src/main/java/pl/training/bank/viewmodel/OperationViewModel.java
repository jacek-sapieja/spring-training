package pl.training.bank.viewmodel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class OperationViewModel {

    @Pattern(regexp = "\\d{26}")
    @NotNull
    private String sourceAccountNumber;
    private String destinationAccountNumber;
    private long funds;
    private String type;

    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public void setSourceAccountNumber(String sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
    }

    public String getDestinationAccountNumber() {
        return destinationAccountNumber;
    }

    public void setDestinationAccountNumber(String destinationAccountNumber) {
        this.destinationAccountNumber = destinationAccountNumber;
    }

    public long getFunds() {
        return funds;
    }

    public void setFunds(long funds) {
        this.funds = funds;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
