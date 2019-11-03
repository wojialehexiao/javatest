package com.song.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ClusterTest {

    public static void main(String[] args) {

        Set<HostAndPort> hostAndPortSet = new HashSet<>(Arrays.asList(
                new HostAndPort("127.0.0.1", 6379),
                new HostAndPort("127.0.0.1", 6380),
                new HostAndPort("127.0.0.1", 6381),
                new HostAndPort("127.0.0.1", 6382),
                new HostAndPort("127.0.0.1", 6383),
                new HostAndPort("127.0.0.1", 6384)
        ));
        JedisCluster jedisCluster = new JedisCluster(hostAndPortSet);

        long l = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            jedisCluster.set("" + i, "" +i);
        }

        jedisCluster.close();
        System.out.println(System.currentTimeMillis() - l);
    }
}
