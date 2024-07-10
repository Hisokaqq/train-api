package alexburtyn.com.trainss.station.services;

import alexburtyn.com.trainss.station.models.Route;
import alexburtyn.com.trainss.station.repositories.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {
    @Autowired
    private RouteRepository routeRepository;

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public Route getRouteById(Integer id) {
        return routeRepository.findById(id).orElse(null);
    }

    public Route saveRoute(Route route) {
        return routeRepository.save(route);
    }

    public void deleteRoute(Integer id) {
        routeRepository.deleteById(id);
    }
}