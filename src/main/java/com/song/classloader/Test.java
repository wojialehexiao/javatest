package com.song.classloader;

import java.io.InputStream;
import java.util.Iterator;
import java.util.ServiceLoader;

public class Test {

    public static void main(String[] args) {
        ServiceLoader<InputStream> load = ServiceLoader.load(InputStream.class);
        Iterator<InputStream> iterator = load.iterator();
        iterator.forEachRemaining(inputStream -> System.out.println(inputStream.getClass().getName()));
    }
}
