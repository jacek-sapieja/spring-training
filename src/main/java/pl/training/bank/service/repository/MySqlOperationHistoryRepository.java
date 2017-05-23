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
        operation.getSubOperations()
                .forEach(entry -> jdbcTemplate.update(INSERT_OPERATION, new MapSqlParameterSource()
                .addValue("number", entry.getSourceAccountNumber())
                .addValue("funds", entry.getFunds())
                .addValue("type", entry.getClass().getSimpleName())));
    }

}
