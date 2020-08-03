package com.vtb.javacourses.lesson19.entities;

import com.vtb.javacourses.lesson19.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "lots")
public class Lot {
    private static final long serialVersionUID = -1049528515179193867L;

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

    @Version
    private Long version;

    public Lot(String name, Integer bet, User user) {
        this.name = name;
        this.bet = bet;
        this.user = user;
    }

    public Lot(String name, Integer bet) {
        this.name = name;
        this.bet = bet;
    }

    public Integer increaseBetByAmount(Integer amount, Member member){
        user = member.getUser();
        bet +=amount;
        return bet;
    }
}
