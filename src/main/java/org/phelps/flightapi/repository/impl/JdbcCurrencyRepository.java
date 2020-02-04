package org.phelps.flightapi.repository.impl;

import org.phelps.flightapi.entity.Currency;
import org.phelps.flightapi.repository.CurrencyRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class JdbcCurrencyRepository implements CurrencyRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Currency> mapper = (rs, rowNum) -> new Currency(
            rs.getString("name")
    );
    private final ResultSetExtractor<Currency> extractor =
            (rs) -> rs.next() ? mapper.mapRow(rs, 1) : null;

    public JdbcCurrencyRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Currency create(Currency currency) {
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO currency (name) " +
                            "VALUES (?)",
                    RETURN_GENERATED_KEYS
            );

            statement.setString(1, currency.getName());

            return statement;
        }, generatedKeyHolder);

        return find(currency.getName());
    }

    @Override
    public Currency find(String name) {
        return jdbcTemplate.query(
                "SELECT name FROM currency WHERE name = ?",
                new Object[]{name},
                extractor);
    }


    @Override
    public List<Currency> list() {
        return jdbcTemplate.query("SELECT id, name FROM currency", mapper);
    }

    @Override
    public void delete(String name) {
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM currency WHERE name  = ? "

            );
            statement.setString(1, name);

            return statement;
        });
    }
}
