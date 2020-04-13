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
public class Pick {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Date is require")
    private String data;

    @NotBlank(message = "Your prediction is require")
    private String pick;

    @NotNull(message = "Put odd for your prediction")
    private float odd;

    @NotNull(message = "Stake is require")
    private int stake;

    private String status;

    private String info;

    private String status_private_public;

    @ManyToOne
    @NotNull(message = "Set user who create this event")
    private User user;

    @ManyToOne
    @NotNull(message = "Chose your bookmark")
    private Bookmark bookmark;

    @ManyToOne
    @NotNull(message = "Chose your match")
    private SportMatch sportMatch;

}
