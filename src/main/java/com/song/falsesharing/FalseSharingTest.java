package com.song.falsesharing;

public class FalseSharingTest {

    public static void main(String[] args) throws InterruptedException {
        testPointer(new Pointer());
    }

    private static void testPointer(Pointer pointer) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100000000; i++) {
                pointer.x.value++;
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100000000; i++) {
                pointer.y.value++;
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(System.currentTimeMillis() - start);
        System.out.println(pointer);
    }
}
//
//class Pointer {
//    volatile long x;
//
//    long p1, p2, p3, p4, p5, p6, p7;
//
//    volatile long y;
//}


class Pointer {
    MyLong x = new MyLong();
    MyLong y = new MyLong();
}
@sun.misc.Contended
class MyLong {
    volatile long value;
}
