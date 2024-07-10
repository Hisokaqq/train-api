package alexburtyn.com.trainss.station.controllers;

import alexburtyn.com.trainss.station.models.*;
import alexburtyn.com.trainss.station.services.RouteService;
import alexburtyn.com.trainss.station.services.ScheduleService;
import alexburtyn.com.trainss.station.services.StationService;
import alexburtyn.com.trainss.station.services.TrainService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private TrainService trainService;
    @Autowired
    private StationService stationService;
    @Autowired
    private RouteService routeService;

    private ResponseEntity<?> checkExistingSchedule(Integer id) {
        Schedule existingSchedule = scheduleService.getScheduleById(id);
        if (existingSchedule == null) {
            return new ResponseEntity<>("Schedule with id: " + id + " not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(existingSchedule, HttpStatus.OK);
    }

    @JsonView(Views.Schedule.class)
    @GetMapping
    public ResponseEntity<List<Schedule>> getAllSchedules() {
        return new ResponseEntity<List<Schedule>>(scheduleService.getAllSchedules(), HttpStatus.OK);
    }

    @JsonView(Views.Schedule.class)
    @GetMapping("/{id}")
    public ResponseEntity<?> getScheduleById(@PathVariable Integer id){
        return checkExistingSchedule(id);
    }

    @JsonView(Views.Schedule.class)
    @PostMapping
    public ResponseEntity<?> createSchedule(@RequestBody Schedule schedule){
        Integer trainId = schedule.getTrain().getId();
        Integer routeId = schedule.getRoute().getId();
        Integer stationId = schedule.getCurrent_station().getId();

        Train train = trainService.getTrainById(trainId);
        if (train == null) {
            return new ResponseEntity<>("Train with id: " + trainId + " not found", HttpStatus.NOT_FOUND);
        }

        Route route = routeService.getRouteById(routeId);
        if (route == null) {
            return new ResponseEntity<>("Route with id: " + routeId + " not found", HttpStatus.NOT_FOUND);
        }

        Station station = stationService.getStationById(stationId);
        if (station == null) {
            return new ResponseEntity<>("Station with id: " + stationId + " not found", HttpStatus.NOT_FOUND);
        }

        Schedule existingSchedule = scheduleService.getScheduleByTrain(train);
        if (existingSchedule != null) {
            return new ResponseEntity<>("A schedule already exists for train with id: " + trainId, HttpStatus.CONFLICT);
        }

        schedule.setTrain(train);
        schedule.setRoute(route);
        schedule.setCurrent_station(station);

        Schedule createdSchedule = scheduleService.saveSchedule(schedule);

        return new ResponseEntity<>(createdSchedule, HttpStatus.CREATED);
    }

    @JsonView(Views.Schedule.class)
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSchedule(@PathVariable Integer id, @RequestBody Schedule scheduleDetails){
        Integer trainId = scheduleDetails.getTrain().getId();
        Integer routeId = scheduleDetails.getRoute().getId();
        Integer stationId = scheduleDetails.getCurrent_station().getId();

        Train train = trainService.getTrainById(trainId);
        if (train == null) {
            return new ResponseEntity<>("Train with id: " + trainId + " not found", HttpStatus.NOT_FOUND);
        }

        Route route = routeService.getRouteById(routeId);
        if (route == null) {
            return new ResponseEntity<>("Route with id: " + routeId + " not found", HttpStatus.NOT_FOUND);
        }

        Station station = stationService.getStationById(stationId);
        if (station == null) {
            return new ResponseEntity<>("Station with id: " + stationId + " not found", HttpStatus.NOT_FOUND);
        }

        Schedule existingSchedule = scheduleService.getScheduleById(id);
        if (existingSchedule == null) {
            return new ResponseEntity<>("Schedule with id: " + id + " not found", HttpStatus.NOT_FOUND);
        }

        existingSchedule.setTrain(train);
        existingSchedule.setRoute(route);
        existingSchedule.setCurrent_station(station);

        Schedule updatedSchedule = scheduleService.saveSchedule(existingSchedule);

        return new ResponseEntity<>(updatedSchedule, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchedule(@PathVariable Integer id) {
        Schedule existingSchedule = scheduleService.getScheduleById(id);
        if (existingSchedule == null) {
            return new ResponseEntity<>("Schedule with id: " + id + " not found", HttpStatus.NOT_FOUND);
        }
        scheduleService.deleteSchedule(id);
        return new ResponseEntity<>("Schedule with id: " + id + " was deleted.", HttpStatus.OK);
    }
}
