package alexburtyn.com.trainss.station.controllers;

import alexburtyn.com.trainss.station.models.Train;
import alexburtyn.com.trainss.station.services.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/trains")
public class TrainController {
    @Autowired
    private TrainService trainService;

    @GetMapping
    public List<Train> getAllTrains() {
        return trainService.getAllTrains();
    }

    @GetMapping("/{id}")
    public Train getTrainById(@PathVariable Integer id) {
        return trainService.getTrainById(id);
    }

    @PostMapping
    public Train createTrain(@RequestBody Train train) {
        return trainService.saveTrain(train);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTrain(@PathVariable Integer id, @RequestBody Train train) {
        Train existingTrain = trainService.getTrainById(id);
        if (existingTrain == null) {
            return new ResponseEntity<>("Train with id " + id + " does not exist", HttpStatus.NOT_FOUND);
        }
        train.setId(id);
        Train updatedTrain = trainService.saveTrain(train);
        return new ResponseEntity<>("Train with name " + updatedTrain.getName() + " and id " + id + " was updated.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTrain(@PathVariable Integer id) {
        Train train = trainService.getTrainById(id);
        if (train == null) {
            return new ResponseEntity<>("Train not found", HttpStatus.NOT_FOUND);
        }
        trainService.deleteTrain(id);
        return new ResponseEntity<>("Train with name " + train.getName() + " and id " + id + " was deleted.", HttpStatus.OK);
    }
}

