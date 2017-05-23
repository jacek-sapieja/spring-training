package pl.training.bank.operation;

import pl.training.bank.service.repository.MySqlOperationHistoryRepository;

public class MySqlHistoryLogger {

    private MySqlOperationHistoryRepository historyRepository;

    public MySqlHistoryLogger(MySqlOperationHistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public void onSuccess(Operation operation) {
        historyRepository.add(operation);
    }

}
