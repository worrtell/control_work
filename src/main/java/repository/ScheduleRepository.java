package repository;
import entity.Schedule;

import java.util.List;
import java.util.Optional;


public interface ScheduleRepository {
    List<Schedule> getAll(int limit, int offset);
}
