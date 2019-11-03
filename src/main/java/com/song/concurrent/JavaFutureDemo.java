package com.song.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class JavaFutureDemo {

    public static final int SLEEP_GAP = 5000;
    public static String getCurrenThreadtName(){
        return Thread.currentThread().getName();
    }

    static class HotWaterJob implements Callable<Boolean>{

        @Override
        public Boolean call() throws Exception {
            try {
                System.out.println("洗好水壶");
                System.out.println("灌上凉水");
                System.out.println("放在火上");
                Thread.sleep(SLEEP_GAP);
                System.out.println("水烧开了");
            }catch (Exception e){
                System.out.println("烧水发生异常中断");
                return false;
            }

            System.out.println("运行结束");
            return true;
        }
    }

    static class WashJob implements Callable<Boolean>{

        @Override
        public Boolean call() throws Exception {

            try {
                System.out.println("洗茶壶");
                System.out.println("洗茶杯");
                System.out.println("拿茶叶");
                Thread.sleep(SLEEP_GAP);
                System.out.println("洗完了");
            }catch (Exception e){
                System.out.println("清洗发生异常中断");
                return false;
            }

            System.out.println("运行结束");
            return true;
        }
    }

    public static void drinkTea(boolean waterOk, boolean cupOk){
        if(waterOk && cupOk){
            System.out.println("泡茶喝");
        }

        if(!waterOk){
            System.out.println("烧水失败，没有茶喝");
        }

        if(!cupOk) {
            System.out.println("洗杯子失败， 没有茶喝");
        }
    }

    public static void main(String[] args) {
        Callable<Boolean> hotWaterJob = new HotWaterJob();
        FutureTask<Boolean> hTask = new FutureTask<>(hotWaterJob);
        Thread hThread = new Thread(hTask, "** 烧水-Thread");

        Callable<Boolean> washJob = new WashJob();
        FutureTask<Boolean> wTask = new FutureTask<>(washJob);
        Thread wThread = new Thread(wTask, "** 清洗-Thread");

        hThread.start();
        wThread.start();

        Thread.currentThread().setName("主线程");


        try {
            Boolean waterOk = hTask.get();
            Boolean cupOk = wTask.get();

            drinkTea(waterOk, cupOk);


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
