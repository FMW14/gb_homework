package com.vtb.javacourses.lesson19;

import com.vtb.javacourses.lesson19.utils.PrepareData;

public class Main {
    public static void main(String[] args) {
        PrepareData.forcePrepareData();

        Auction auction = new Auction();
        auction.start();

    }
}
