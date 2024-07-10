package alexburtyn.com.trainss.station.repositories;


import alexburtyn.com.trainss.station.models.Schedule;
import alexburtyn.com.trainss.station.models.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    Schedule findByTrain(Train train);
}
