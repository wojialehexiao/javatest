package com.song.reactor;

import java.io.IOException;

public class TestReactor {

    public static void main(String[] args) throws IOException {
        new Thread(new Reactor(8000)).start();
    }
}
