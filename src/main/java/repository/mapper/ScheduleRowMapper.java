package repository.mapper;

import entity.Schedule;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ScheduleRowMapper implements RowMapper<Schedule> {

    @Override
    public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Schedule.builder()
                .id(rs.getLong("id"))
                .aud_num(rs.getLong("aud_num"))
                .time(rs.getString("time"))
                .day_week(rs.getLong("day_week"))
                .teacher(rs.getString("teacher"))
                .name_group(rs.getString("name_group"))
                .build();
    }
}
