package com.djelroy.reactorbasics;

import java.util.Arrays;
import java.util.List;

import reactor.core.publisher.Flux;

public class FluxExample 
{
    public static void main( String[] args )
    {
        Flux<String> firstNames = Flux.just("Steve", "Gary");
        List<String> iterableLastNames = Arrays.asList("Vee", "Jobs", "Sambora");
        Flux<String> lastNames = Flux.fromIterable(iterableLastNames);
        Flux<Integer> oneToThree = Flux.range(1, 3);
        
        firstNames.subscribe(n -> System.out.println("My first name is " + n));
        lastNames.subscribe(n -> System.out.println("My last name is " + n));
        oneToThree.subscribe(System.out::println);
        
    }
}
