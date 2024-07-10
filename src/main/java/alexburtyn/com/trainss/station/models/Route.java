package alexburtyn.com.trainss.station.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "routes")
public class Route {
    @JsonView({Views.Basic.class, Views.Schedule.class})
    @Id
    @GeneratedValue
    private Integer id;
    @JsonView({Views.Basic.class, Views.Schedule.class})
    private String name;

    @JsonView({Views.Basic.class})
    @ManyToMany
    @JoinTable(
            name = "route_stations",
            joinColumns = @JoinColumn(name = "route_id"),
            inverseJoinColumns = @JoinColumn(name = "station_id")
    )
    private List<Station> stations;

    /*    @JsonView({Views.Basic.class, Views.Schdule.class})*/
    @JsonIgnore
    @OneToMany(mappedBy = "route", cascade = CascadeType.REMOVE)
    private List<Schedule> schedules;
}