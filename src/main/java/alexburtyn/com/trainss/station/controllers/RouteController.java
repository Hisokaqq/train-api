package alexburtyn.com.trainss.station.controllers;

import alexburtyn.com.trainss.station.models.Route;
import alexburtyn.com.trainss.station.models.Station;
import alexburtyn.com.trainss.station.models.Views;
import alexburtyn.com.trainss.station.services.RouteService;
import alexburtyn.com.trainss.station.services.StationService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/routes")
public class RouteController {
    @Autowired
    private RouteService routeService;
    @Autowired
    private StationService stationService;

    @JsonView(Views.Basic.class)
    @GetMapping
    public List<Route> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    @JsonView(Views.Basic.class)
    @GetMapping("/{id}")
    public Route getRouteById(@PathVariable Integer id) {
        return routeService.getRouteById(id);
    }

    @JsonView(Views.Basic.class)
    @PostMapping
    public Route createRoute(@RequestBody Route route) {
        
        List<Station> stations = route.getStations();
        List<Station> actualStations = new ArrayList<>();
        for (Station station : stations) {
            Station actualStation = stationService.getStationById(station.getId());
            if (actualStation != null) {
                actualStations.add(actualStation);
            }
        }
        route.setStations(actualStations);

        return routeService.saveRoute(route);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRoute(@PathVariable Integer id, @RequestBody Route route) {
        Route existingRoute = routeService.getRouteById(id);
        if (existingRoute == null) {
            return new ResponseEntity<>("Route with id " + id + " does not exist", HttpStatus.NOT_FOUND);
        }

        List<Station> stations = route.getStations();
        List<Station> actualStations = new ArrayList<>();
        for (Station station : stations) {
            Station actualStation = stationService.getStationById(station.getId());
            if (actualStation != null) {
                actualStations.add(actualStation);
            }
        }
        route.setStations(actualStations);
        route.setId(id);
        Route updatedRoute = routeService.saveRoute(route);

        return new ResponseEntity<>("Route with name " + updatedRoute.getName() + " and id " + id + " was updated.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoute(@PathVariable Integer id) {
        Route route = routeService.getRouteById(id);
        if (route == null) {
            return new ResponseEntity<>("Route not found", HttpStatus.NOT_FOUND);
        }
        routeService.deleteRoute(id);
        return new ResponseEntity<>("Route with id " + id + " was deleted.", HttpStatus.OK);
    }
}

