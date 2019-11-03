package com.song.circle;

import java.util.*;

public class CircleTest {

    public static void main(String[] args) {

        List<String> path = Arrays.asList("A","B" , "C", "D", "C", "B","E");
        List<String> path2 = Arrays.asList("A","B" , "C", "D", "B","E");
        List<String> path3 = Arrays.asList("A","B" , "C", "D", "B","E","F","G","E","H");




        List<List<String>> allCircle = new ArrayList<>();

        Stack<String> stack = new Stack<>();
        for (String node : path3) {
            if(!stack.empty() && stack.search(node) > -1){
                List<String> circle = new ArrayList<>();
                circle.add(node);

                String pop = stack.pop();
                while (!node.equals(pop)) {
                    circle.add(pop);
                    pop = stack.pop();
                }
                circle.add(pop);
                stack.push(pop);
                Collections.reverse(circle);
                allCircle.add(circle);
            }else {
                stack.push(node);
            }
        }
        stack.clear();

        System.out.println(allCircle);
    }
}
