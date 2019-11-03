package com.song.test;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import static org.mockito.Mockito.*;

public class MockTest {


    @Test
    public void myTest(){
        List list = mock(List.class);
        when(list.get(0)).thenReturn("111");
        Assert.assertEquals("asd", 1,1);
        System.out.println(list.get(0));
        System.out.println(list.get(1));

        list.add("aaa");
        list.add("bbb");

        System.out.println(list.get(0));

        System.out.println(list.size());

        verify(list, times(1)).get(0);

        String ret = (String) list.get(0);

        Assert.assertEquals(ret, "111");
    }
}

