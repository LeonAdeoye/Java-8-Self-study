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
        // The core difference is that Reactive is a push model, whereas the Java 8 Streams are a pull model.
        // In a reactive approach, events are pushed to the subscribers as they come in.
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

        backPressureSubscriber();
        mapStream();
    }

    private void mapStream()
    {
        Flux<Integer> ints = Flux.range(1, 10)
                .map((Integer i) -> i * 10);

        ints.subscribe((Integer i) -> System.out.println(i));
    }

    private void backPressureSubscriber()
    {
        // In the above example, the subscriber is telling the producer to push every single element at once.
        // This could end up becoming overwhelming for the subscriber, consuming all of its resources.
        // Backpressure is when a downstream can tell an upstream to send it fewer data in order to prevent it from being overwhelmed.
        // We can modify our Subscriber implementation to apply backpressure.
        // Let's tell the upstream to only send two elements at a time by using request():
        List<Integer> elements = new ArrayList<>();

        Flux.just(11, 12, 13, 14, 15, 16, 17, 18)
                .log()
                .subscribe(new Subscriber<Integer>()
                {
                    private Subscription subscription;
                    private int next = 0;

                    @Override
                    public void onSubscribe(Subscription subscription)
                    {
                        this.subscription = subscription;
                        subscription.request(3);
                    }

                    @Override
                    public void onNext(Integer integer)
                    {
                        // In logs, we'll see the request(3) is called, followed by three onNext() calls, then request(3) again, etc.
                        elements.add(integer);
                        next++;
                        if(next % 3 == 0)
                            this.subscription.request(3);
                    }

                    @Override
                    public void onError(Throwable t) {}

                    @Override
                    public void onComplete() {}
                });
    }
}