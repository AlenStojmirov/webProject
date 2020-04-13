package com.example.webproject.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class SportMatch  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Match name is required")
    private String name;

    private String status;

    @NotBlank(message = "Match time is required")
    private String time;

    @ManyToOne
    private Club clubHome;

    @ManyToOne
    private Club clubAway;

}
