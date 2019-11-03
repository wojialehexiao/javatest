package com.song.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SentinelTest {

    public static void main(String[] args) {

        Set<String> sentilnelSet = new HashSet<>(Arrays.asList(
                "127.0.0.1:26379",
                "127.0.0.1:26380",
                "127.0.0.1:26381"
        ));

        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool("mymaster", sentilnelSet);

        Jedis resource = jedisSentinelPool.getResource();
        resource.set("aa", "bb");
        resource.close();

        jedisSentinelPool.destroy();
        jedisSentinelPool.close();
    }
}
