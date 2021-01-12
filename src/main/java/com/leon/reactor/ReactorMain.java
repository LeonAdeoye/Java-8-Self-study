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

        // Let's go through the sequence that we have logged one by one:
        // onSubscribe() – This is called when we subscribe to our stream
        // request(unbounded) – When we call subscribe, behind the scenes we are creating a Subscription. This subscription requests elements from the stream.
        // In this case, it defaults to unbounded, meaning it requests every single element available
        // onNext() – This is called on every single element
        // onComplete() – This is called last, after receiving the last element.
        List<Integer> list = new ArrayList<>();
        Flux.just(100, 101, 102, 103)
                .log()
                .subscribe(list::add);
        list.forEach(System.out::println);

        subSCriberInterface();
    }

    private void subSCriberInterface()
    {
        List<Integer> elements = new ArrayList<>();

        Flux.just(11, 12, 13, 14)
                .log()
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        elements.add(integer);
                    }

                    @Override
                    public void onError(Throwable t) {}

                    @Override
                    public void onComplete() {}
                });
    }
}
