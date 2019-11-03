package com.song.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public class AssistTest {

    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();

        CtClass ctClass = pool.makeClass("com.song.test.Test");

        CtMethod ctMethod = CtMethod.make("public void test(){}",ctClass);
        ctClass.addMethod(ctMethod);

        ctClass.writeFile("d:/test");
    }
}
