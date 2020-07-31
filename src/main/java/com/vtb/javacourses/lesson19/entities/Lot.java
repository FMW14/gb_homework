package com.vtb.javacourses.lesson19.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "lots")
public class Lot {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "bet")
    private Integer bet;

//    @Column(name = "user_id")
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Lot(String name, Integer bet, User user) {
        this.name = name;
        this.bet = bet;
        this.user = user;
    }

    public Lot(String name, Integer bet) {
        this.name = name;
        this.bet = bet;
    }
}
