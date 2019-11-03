package com.song.concurrent;

public class SingletonTest {

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                Singleton singleton = Singleton.getSingleton();
                System.out.println(singleton);
            }).start();
        }
    }
}
