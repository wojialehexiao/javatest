package com.song.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GcTest {

    public static void main(String[] args) {
        List<UUID> uuidList = new ArrayList<>();
        while (true) {

            UUID uuid = UUID.randomUUID();
            uuidList.add(uuid);

            if(uuidList.size() == 1000000){
                uuidList.clear();
            }
            String s = uuid.toString();
        }
    }
}
