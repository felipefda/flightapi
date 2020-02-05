package org.phelps.flightapi.repository.impl;

import org.phelps.flightapi.entity.Request;
import org.phelps.flightapi.repository.RequestRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@Repository
public class JdbcRequestRepository implements RequestRepository {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Request> mapper = (rs, rowNum) -> new Request(
            rs.getLong("id"),
            rs.getDate("created_on"),
            rs.getString("ip_address"),
            rs.getString("from_code"),
            rs.getString("destiny"),
            rs.getString("date_from"),
            rs.getString("date_to"),
            rs.getString("currency"),
            rs.getString("status")
    );
    private final ResultSetExtractor<Request> extractor =
            (rs) -> rs.next() ? mapper.mapRow(rs, 1) : null;

    public JdbcRequestRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public void create(Request request) {
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO request (created_on,ip_address,from,destiny,date_from,date_to,currency,status) " +
                            "VALUES (?,?,?,?,?,?,?,?,?)",
                    RETURN_GENERATED_KEYS
            );

            statement.setDate(1, request.getCreatedOn());
            statement.setString(2, request.getIpAddress());
            statement.setString(3, request.getFrom());
            statement.setString(4, request.getDestiny());
            statement.setString(5, request.getDateFrom());
            statement.setString(6, request.getDateTo());
            statement.setString(7, request.getCurrency());
            statement.setString(8, request.getStatus());

            return statement;
        }, generatedKeyHolder);

    }

    @Override
    public List<Request> list() {
        return jdbcTemplate.query("SELECT * FROM request ORDER BY created_on DESC", mapper);
    }

    @Override
    public void clearRequests() {
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(
                    "TRUNCATE request;"

            );

            return statement;
        });
    }
}
