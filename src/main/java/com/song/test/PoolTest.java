package com.song.test;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PoolTest {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 0, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>());

        for (int i = 0; i < 3; i++) {
            threadPoolExecutor.submit(()-> {
                try {

                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        for (int i = 0; i < 3; i++) {
            threadPoolExecutor.submit(()-> {
                try {

                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        for (int i = 0; i < 10; i++) {


            System.out.println("-----------------------------------------------------------------");
            System.out.println(threadPoolExecutor.getActiveCount());
            System.out.println(threadPoolExecutor.getCompletedTaskCount());
            System.out.println(threadPoolExecutor.getCorePoolSize());
            System.out.println(threadPoolExecutor.getCompletedTaskCount());
            System.out.println(threadPoolExecutor.getTaskCount());
            System.out.println(threadPoolExecutor.getLargestPoolSize());
            System.out.println(threadPoolExecutor.getMaximumPoolSize());
            System.out.println(threadPoolExecutor.getPoolSize());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



    }
}
