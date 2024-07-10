package alexburtyn.com.trainss.station.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "trains")
public class Train {
    @Id
    @GeneratedValue
    @JsonView(Views.Schedule.class)
    private Integer id;

    @JsonView(Views.Schedule.class)
    private String name;

    @JsonIgnore
    @OneToOne(mappedBy = "train", cascade = CascadeType.REMOVE)
    private Schedule schedule;
}