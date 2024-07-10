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

import java.util.List;

@RestController
@RequestMapping("/stations")
public class StationController {
    @Autowired
    private StationService stationService;

    @Autowired
    private RouteService routeService;

    @JsonView(Views.Full.class)
    @GetMapping
    public List<Station> getAllStations() {
        return stationService.getAllStations();
    }

    @JsonView(Views.Full.class)
    @GetMapping("/{id}")
    public Station getStationById(@PathVariable Integer id) {
        return stationService.getStationById(id);
    }

    @PostMapping
    public ResponseEntity<?> createStation(@RequestBody Station station) {
        Station existingStation = stationService.findByName(station.getName());
        if (existingStation != null) {
            return new ResponseEntity<>("Station with name: " + station.getName() + " already exists", HttpStatus.CONFLICT);
        }
        Station createdStation = stationService.saveStation(station);
        return new ResponseEntity<>(createdStation, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStation(@PathVariable Integer id, @RequestBody Station station) {
        Station existingStation = stationService.getStationById(id);
        if (existingStation == null) {
            return new ResponseEntity<>("Station with id " + id + " does not exist", HttpStatus.NOT_FOUND);
        }
        station.setId(id);
        Station updatedStation = stationService.saveStation(station);
        return new ResponseEntity<>("Station with name " + updatedStation.getName() + " and id " + id + " was updated.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStation(@PathVariable Integer id) {
        Station station = stationService.getStationById(id);
        if (station == null) {
            return new ResponseEntity<>("Station not found", HttpStatus.NOT_FOUND);
        }

        // Remove the station from all routes that reference it
        List<Route> routes = station.getRoutes();
        for (Route route : routes) {
            route.getStations().remove(station);
            routeService.saveRoute(route); // Assuming you have a routeService. If not, you need to create one.
        }

        stationService.deleteStation(id);
        return new ResponseEntity<>("Station with name " + station.getName() + " and id " + id + " was deleted.", HttpStatus.OK);
    }
}