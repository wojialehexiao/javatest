package com.song.concurrent;

public class ThreadLocalModel {

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    private static int i = 0;

    public static void set() {
        threadLocal.set(i++);
    }

    public static int get() {
        return threadLocal.get();
    }
}
