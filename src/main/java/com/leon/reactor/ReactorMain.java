package com.leon.reactor;

import reactor.core.publisher.*;

import java.util.ArrayList;
import java.util.List;
import org.reactivestreams.*;

public class ReactorMain
{
    public void main()
    {
        // Mono and Flux are both implementations of the Reactive Streams Publisher interface.
        // Flux is a stream that can emit 0..n elements.
        Flux<Integer> ints = Flux.range(1, 4)
                .map((Integer i) ->
                {
                    if (i <= 3)
                        return i;

                    throw new RuntimeException("Got to 4");
                });

        // The subscribe method collects elements in the stream.
        ints.subscribe((Integer i) -> System.out.println(i),
                error -> System.err.println("Flux Error: " + error));

        // Mono is a stream that can emit 0..1 elements.
        Publisher<Integer> mono = Mono.just(1);

        List<Integer> list = new ArrayList<>();
        Flux.just(100, 101, 102, 103).subscribe(list::add);
        list.forEach(System.out::println);
    }
}
