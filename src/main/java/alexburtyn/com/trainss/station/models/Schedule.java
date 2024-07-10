package alexburtyn.com.trainss.station.models;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "schedules")
public class Schedule {
    @JsonView(Views.Schedule.class)
    @Id
    @GeneratedValue
    private Integer id;

    @JsonView(Views.Schedule.class)
    @OneToOne
    @JoinColumn(name = "train_id")
    private Train train;

    @JsonView(Views.Schedule.class)
    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @JsonView(Views.Schedule.class)
    @ManyToOne
    @JoinColumn(name = "current_station_id")
    private Station current_station;
}