package pl.training.bank.service.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import pl.training.bank.operation.Operation;
import pl.training.bank.operation.TransferOperation;

import javax.sql.DataSource;
import java.util.Arrays;

public class MySqlOperationHistoryRepository {

    private static final String INSERT_OPERATION = "insert into history values(null,:number,:funds,:type,now())";

    private NamedParameterJdbcTemplate jdbcTemplate;

    public MySqlOperationHistoryRepository(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public void add(Operation operation) {
        save(operation.getSubOperations().toArray(new Operation[0]));
    }

    private void save(Operation ... operations) {
        Arrays.stream(operations).forEach(operation -> {
            MapSqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("number", operation.getSourceAccountNumber())
                    .addValue("funds", operation.getFunds())
                    .addValue("type", operation.getClass().getSimpleName());
            jdbcTemplate.update(INSERT_OPERATION, parameters);
        });
    }

}
