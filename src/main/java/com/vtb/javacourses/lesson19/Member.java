package com.vtb.javacourses.lesson19;

import com.vtb.javacourses.lesson19.entities.Lot;
import com.vtb.javacourses.lesson19.entities.User;
import com.vtb.javacourses.lesson19.utils.HibernateUtil;
import lombok.Getter;
import org.hibernate.Session;

import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;


public class Member implements Runnable {

    private CountDownLatch startCdl;
    private CountDownLatch endCdl;

    @Getter
    private Auction auction;

    @Getter
//    @Setter
    private User user;

    public Member(User user, Auction auction, CountDownLatch startCdl, CountDownLatch endCdl) {
        this.user = user;
        this.startCdl = startCdl;
        this.auction = auction;
        this.endCdl = endCdl;
    }

    @Override
    public void run() {
        try {
            startCdl.countDown();

            long maxLots = 5L;

            for (int i = 0; i < 1000; i++) {
                long randomNum = ThreadLocalRandom.current().nextLong(1L, maxLots);
//                increaseOptimistic(randomNum);
                increasePessimistic(randomNum);

                Thread.sleep(1);
            }
            endCdl.countDown();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void increaseOptimistic(Long randomNum) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Lot lotFromDb;
        lotFromDb = (Lot) session.createQuery("from Lot where id=:id").setParameter("id", randomNum).
                setLockMode(LockModeType.OPTIMISTIC).
                uniqueResult();
        lotFromDb.increaseBetByAmount(100, this);

        try {
            session.getTransaction().commit();
            System.out.println(this.toString() + " committed");
        } catch (OptimisticLockException e) {
            session.getTransaction().rollback();
            System.out.println(this.toString() + " rolled back");
            increaseOptimistic(randomNum);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    private void increasePessimistic(Long randomNum) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();

            Lot lotFromDb;
            lotFromDb = (Lot) session.createQuery("from Lot where id=:id").setParameter("id", randomNum).
                    setLockMode(LockModeType.PESSIMISTIC_WRITE).
                    uniqueResult();
            lotFromDb.increaseBetByAmount(100, this);
            session.getTransaction().commit();


            System.out.println(this.toString() + " committed");
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
