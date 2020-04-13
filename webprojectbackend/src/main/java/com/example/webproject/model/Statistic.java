package com.example.webproject.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int numPicks;

    private int win;

    private int lose;

    private int _void;

    private float winRatio;

    private double profit;

}
