package com.song.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

public class MyClassLoader extends ClassLoader {



    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            FileInputStream fis = new FileInputStream("D:/test/Hello.class");
            byte[] buffer = new byte[1];

            while (fis.read(buffer) != -1){
                baos.write(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] bytes = baos.toByteArray();
        return defineClass(name, bytes,0,bytes.length);
    }
}
