package com.song.nio;

import java.nio.CharBuffer;

public class BufferTest {

    public static void main(String[] args) {
        CharBuffer charBuffer = CharBuffer.allocate(100);

        System.out.println("position: " + charBuffer.position());
        System.out.println("limit: " + charBuffer.limit());
        System.out.println("capacity: " + charBuffer.capacity());


        for (int i = 0; i < 10; i++) {
            charBuffer.put('a');
        }

        System.out.println("---------------after put------------------");
        System.out.println("position: " + charBuffer.position());
        System.out.println("limit: " + charBuffer.limit());
        System.out.println("capacity: " + charBuffer.capacity());


        charBuffer.flip();

        System.out.println("---------------after flip------------------");
        System.out.println("position: " + charBuffer.position());
        System.out.println("limit: " + charBuffer.limit());
        System.out.println("capacity: " + charBuffer.capacity());

        for (int i = 0; i < charBuffer.limit(); i++) {
            System.out.print(charBuffer.get());
            System.out.println(charBuffer.position());
        }

        System.out.println("---------------after rewind--------------------");

        charBuffer.rewind();

        for (int i = 0; i < charBuffer.limit(); i++) {
            System.out.print(charBuffer.get());
            if(i == 2){
                charBuffer.mark();
            }
            System.out.println(charBuffer.position());
        }


        System.out.println("---------------after reset--------------------");

        charBuffer.reset();

        System.out.println("position: " + charBuffer.position());
        System.out.println("limit: " + charBuffer.limit());
        System.out.println("capacity: " + charBuffer.capacity());



        charBuffer.clear();

        System.out.println("---------------after clear------------------");
        System.out.println("position: " + charBuffer.position());
        System.out.println("limit: " + charBuffer.limit());
        System.out.println("capacity: " + charBuffer.capacity());




    }
}
