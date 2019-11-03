package com.song.concurrent;

import com.google.common.util.concurrent.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GuavaFutureDemo {

    public static final int SLEEP_GAP = 5000;

    public static String getCurrenThreadtName() {
        return Thread.currentThread().getName();
    }

    static class HotWaterJob implements Callable<Boolean> {

        @Override
        public Boolean call() throws Exception {
            try {
                System.out.println("洗好水壶");
                System.out.println("灌上凉水");
                System.out.println("放在火上");
                Thread.sleep(SLEEP_GAP);
                System.out.println("水烧开了");
            } catch (Exception e) {
                System.out.println("烧水发生异常中断");
                return false;
            }

            System.out.println("运行结束");
            return true;
        }
    }

    static class WashJob implements Callable<Boolean> {

        @Override
        public Boolean call() throws Exception {

            try {
                System.out.println("洗茶壶");
                System.out.println("洗茶杯");
                System.out.println("拿茶叶");
                Thread.sleep(SLEEP_GAP);
                System.out.println("洗完了");
            } catch (Exception e) {
                System.out.println("清洗发生异常中断");
                return false;
            }

            System.out.println("运行结束");
            return true;
        }
    }

    static class MainJob implements Runnable {
        boolean waterOk = false;
        boolean cupOk = false;

        int gap = SLEEP_GAP;


        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (Exception e) {

                }

                if (waterOk && cupOk) {
                    drinkTea(waterOk, cupOk);
                    break;
                }
            }
        }
    }

    public static void drinkTea(boolean waterOk, boolean cupOk) {
        if (waterOk && cupOk) {
            System.out.println("泡茶喝");
        }

        if (!waterOk) {
            System.out.println("烧水失败，没有茶喝");
        }

        if (!cupOk) {
            System.out.println("洗杯子失败， 没有茶喝");
        }
    }

    public static void main(String[] args) {

        MainJob mainJob = new MainJob();
        Thread mainThread = new Thread(mainJob);
        mainThread.setName("主线程");
        mainThread.start();


        Callable<Boolean> hotWaterJob = new HotWaterJob();

        Callable<Boolean> washJob = new WashJob();

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        ListeningExecutorService gPool = MoreExecutors.listeningDecorator(executorService);

        ListenableFuture<Boolean> hotFuture = gPool.submit(hotWaterJob);

        Futures.addCallback(hotFuture, new FutureCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {
                if (aBoolean) {
                    mainJob.waterOk = true;
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });


        ListenableFuture<Boolean> washFuture = gPool.submit(washJob);

        Futures.addCallback(washFuture, new FutureCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {
                if (aBoolean) {
                    mainJob.cupOk = true;
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });

    }
}
