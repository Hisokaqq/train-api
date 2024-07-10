package alexburtyn.com.trainss.station.repositories;

import alexburtyn.com.trainss.station.models.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {
}
