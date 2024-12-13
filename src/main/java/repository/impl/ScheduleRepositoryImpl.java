package repository.impl;

import entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import repository.ScheduleRepository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@RequiredArgsConstructor
public class ScheduleRepositoryImpl implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Schedule> rowMapper;
    private final Properties properties;

    public ScheduleRepositoryImpl(Properties properties, DataSource dataSource, RowMapper<Schedule> rowMapper) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.rowMapper = rowMapper;
        this.properties = properties;
    }

    private Optional<Schedule> optionalSingleResult(List<Schedule> students) {
        if (students.size() > 1) {
            throw new IncorrectResultSizeDataAccessException(1);
        } else {
            return students.stream().findAny();
        }
    }

    @Override
    public List<Schedule> getAll(int limit, int offset) {
        return jdbcTemplate.query("select * from schedule offset ? limit ?", rowMapper, offset, limit);
    }
}