package service;

import entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleService {
    List<Schedule> getAll(int limit, int offset);
}
