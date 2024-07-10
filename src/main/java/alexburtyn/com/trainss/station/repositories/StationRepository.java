package alexburtyn.com.trainss.station.repositories;

import alexburtyn.com.trainss.station.models.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station, Integer> {
    Station findByName(String name);
}