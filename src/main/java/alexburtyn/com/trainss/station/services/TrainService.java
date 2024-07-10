package alexburtyn.com.trainss.station.services;


import alexburtyn.com.trainss.station.models.Train;
import alexburtyn.com.trainss.station.repositories.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService {
    @Autowired
    private TrainRepository trainRepository;

    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }

    public Train getTrainById(Integer id) {
        return trainRepository.findById(id).orElse(null);
    }

    public Train saveTrain(Train train) {
        return trainRepository.save(train);
    }

    public void deleteTrain(Integer id) {
        trainRepository.deleteById(id);
    }
}