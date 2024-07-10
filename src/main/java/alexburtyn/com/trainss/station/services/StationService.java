package alexburtyn.com.trainss.station.services;

import alexburtyn.com.trainss.station.models.Station;
import alexburtyn.com.trainss.station.repositories.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {
    @Autowired
    private StationRepository stationRepository;

    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }

    public Station getStationById(Integer id) {
        return stationRepository.findById(id).orElse(null);
    }

    public Station saveStation(Station station) {
        return stationRepository.save(station);
    }

    public void deleteStation(Integer id) {
        stationRepository.deleteById(id);
    }

    public Station findByName(String name) {
        return stationRepository.findByName(name);
    }
}