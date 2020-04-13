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
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "League name is require")
    private String name;

    @ManyToOne
    @NotNull(message = "Sport category is required")
    private Sport sport;

    @JsonIgnore
    @ManyToMany(mappedBy = "leagues")
    private List<Club> clubs;

}
