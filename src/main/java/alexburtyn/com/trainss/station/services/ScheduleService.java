package alexburtyn.com.trainss.station.services;


import alexburtyn.com.trainss.station.models.Schedule;
import alexburtyn.com.trainss.station.models.Train;
import alexburtyn.com.trainss.station.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public Schedule getScheduleById(Integer id) {
        return scheduleRepository.findById(id).orElse(null);
    }

    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public void deleteSchedule(Integer id) {
        scheduleRepository.deleteById(id);
    }

    public Schedule getScheduleByTrain(Train train) {
        return scheduleRepository.findByTrain(train);
    }
}
