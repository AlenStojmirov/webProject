package com.example.webproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class PrivateUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int numOfSubscribers;

    private int costSubscribe;

    private int money;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User userId;

    @JsonIgnore
    @ManyToMany(mappedBy = "subscribers")
    private List<User> userSubscribers;

}
