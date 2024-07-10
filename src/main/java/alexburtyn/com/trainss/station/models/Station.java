package alexburtyn.com.trainss.station.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.common.aliasing.qual.Unique;

import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stations")
public class Station {
    @JsonView({Views.Basic.class, Views.Schedule.class})
    @Id
    @GeneratedValue
    private Integer id;

    @JsonView({Views.Basic.class,  Views.Schedule.class})
    @Column(unique = true)
    private String name;

    @JsonView(Views.Full.class)
    private String location;
    @JsonView(Views.Full.class)
    private String state;
    @JsonView(Views.Full.class)
    private String country;
    @JsonView(Views.Full.class)
    private String postalCode;

    @JsonIgnore
    @ManyToMany(mappedBy = "stations")
    private List<Route> routes;

    @OneToMany(mappedBy = "current_station")
    private List<Schedule> schedules;
}