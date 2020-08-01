package com.vtb.javacourses.lesson19;

import com.vtb.javacourses.lesson19.entities.Lot;
import com.vtb.javacourses.lesson19.entities.User;
import com.vtb.javacourses.lesson19.repos.LotRepo;
import com.vtb.javacourses.lesson19.repos.UserRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Auction {
    private List<User> users;

    public Auction() {
        UserRepo userRepo = new UserRepo();
        this.users = userRepo.getAll();
    }

    public void start() {
        CountDownLatch startCdl = new CountDownLatch(users.size());
        CountDownLatch endCdl = new CountDownLatch(users.size());

        List<Member> members = new ArrayList<>();

        for (User user : users) {
            members.add(new Member(user, this, startCdl, endCdl));
        }

        System.out.println("Auction starts!");
//        System.out.println(members);
//        System.out.println(lots);
//        System.out.println();

        for (Member member : members) {
            new Thread(member).start();
        }

        long time = System.currentTimeMillis();
        try {
            startCdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            endCdl.await();
            System.out.println("Auction ended!");
            System.out.println("Elapsed time: " + (System.currentTimeMillis() - time));
            System.out.println(checkSum());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean checkSum() {
        int expectedTotalSum = 800_000;
        int providedTotalSum = 0;
        LotRepo lotRepo = new LotRepo();

        for (Lot curLot : lotRepo.getAll()) {
            providedTotalSum += curLot.getBet();
        }
        System.out.println("provided sum: " + providedTotalSum);

        return expectedTotalSum == providedTotalSum;
    }
}
