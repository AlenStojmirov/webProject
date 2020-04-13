package com.example.webproject.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Year is required")
    private int year;

    @NotBlank(message = "Country is required")
    private String country;

    @NotBlank(message = "Select one options")
    private String individual;

    @ManyToOne
    @NotNull(message = "Sport id required")
    private Sport sport;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "clubs_leagues",
            joinColumns = @JoinColumn(name = "clubId"),
            inverseJoinColumns = @JoinColumn(name = "leagueId"))
    @NotNull(message = "League is required")
    private List<League> leagues;

}
