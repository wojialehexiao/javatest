package com.song.cglib;

import net.sf.cglib.proxy.Enhancer;

public class CglibTest {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(User.class);
        enhancer.setCallback(new UserInterceptor());

        User user = (User)enhancer.create();

        user.getUserName();

    }
}
