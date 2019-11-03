package com.song.test;

import sun.reflect.Reflection;

public class ClassloaderTest {

    public static void main(String[] args) {
//        try {
//            Class<?> aClass = Class.forName("Hello", true, new MyClassLoader());
//            System.out.println(aClass.getClassLoader().getParent());
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }


        System.out.println("DDDDDDDDDDDDDDDDDDDDDD");

        new Test();
        new Test();
        new Test();
        new Test();
//        test.test();
    }


    static class Test{

        static {
            System.out.println("test init");
        }

        void  test(){
//        System.out.println(Reflection.getCallerClass(-1));
            System.out.println(Reflection.getCallerClass(0));
            System.out.println(Reflection.getCallerClass(1));
            System.out.println(Reflection.getCallerClass(2));
        }
    }

}
