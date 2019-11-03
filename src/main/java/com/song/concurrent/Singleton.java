package com.song.concurrent;

public class Singleton {

    private static Singleton singleton;

    private static final Object lock = new Object();

    private Singleton(){

    }

    public static Singleton getSingleton() {

        if(singleton == null){
            synchronized(lock) {
                if(singleton == null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
