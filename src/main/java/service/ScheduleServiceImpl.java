package service;

import entity.Schedule;
import lombok.AllArgsConstructor;
import repository.ScheduleRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private ScheduleRepository userRepository;

    @Override
    public List<Schedule> getAll(int limit, int offset) {
        return userRepository.getAll(limit, offset);
    }
}
