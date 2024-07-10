package alexburtyn.com.trainss.station.dataInitializer;

import alexburtyn.com.trainss.station.models.Route;
import alexburtyn.com.trainss.station.models.Station;
import alexburtyn.com.trainss.station.models.Train;
import alexburtyn.com.trainss.station.repositories.RouteRepository;
import alexburtyn.com.trainss.station.services.StationService;
import alexburtyn.com.trainss.station.repositories.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Configuration
public class DataInitializer {

    private static final Logger logger = Logger.getLogger(DataInitializer.class.getName());

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private StationService stationService;

    @Autowired
    private RouteRepository routeRepository;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            // Create and save trains
            createTrains("U1", 5);
            createTrains("U2", 5);
            createTrains("U3", 5);
            createTrains("U4", 5);
            createTrains("U6", 5);

            // Create and save stations
            createStations(UbahnStationsData.STATIONS);

            // Create and save routes
            createRoutes(RoutesData.ROUTES);
        };
    }

    private void createTrains(String namePrefix, int count) {
        for (int i = 1; i <= count; i++) {
            Train train = new Train();
            train.setName(namePrefix + "-" + i);
            trainRepository.save(train);
        }
    }

    private void createStations(String[][] stationsData) {
        for (String[] stationData : stationsData) {
            Station station = new Station();
            station.setName(stationData[0]);
            station.setLocation("Lat: " + stationData[1] + ", Long: " + stationData[2] );
            station.setState(stationData[3]);
            station.setCountry(stationData[4]);
            station.setPostalCode(stationData[5]);
            stationService.saveStation(station);
        }
    }

    private void createRoutes(List<RoutesData.RouteInfo> routes) {
        for (RoutesData.RouteInfo routeInfo : routes) {
            Route route = new Route();
            route.setName(routeInfo.name);

            List<Station> stations = routeInfo.stations.stream()
                    .map(stationName -> {
                        Station station = stationService.findByName(stationName);
                        if (station == null) {
                            logger.warning("Station not found: " + stationName);
                            return null;
                        } else {
                            logger.info("Found station: " + stationName);
                            return station;
                        }
                    })
                    .filter(station -> station != null)
                    .collect(Collectors.toList());

            if (!stations.isEmpty()) {
                route.setStations(stations);
                routeRepository.save(route);
                logger.info("Created route: " + routeInfo.name);
            } else {
                logger.warning("No stations found for route: " + routeInfo.name);
            }
        }
    }
}