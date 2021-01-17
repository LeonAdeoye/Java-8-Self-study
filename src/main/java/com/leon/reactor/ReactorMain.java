package com.leon.reactor;

import reactor.core.publisher.*;
import reactor.core.scheduler.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.reactivestreams.*;
import java.util.Random;

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
        zipWith();
        baseSubscriber();
        generate();
        handle();
        thread();
        switchThread();
        creatingFluxes();
        creatingMono();

        // You can cold streams and hot stream.
        // Cold stream are static fixed length streams
        // while hot streams are always running and can be subscribed to at any point missing the start of the data.
        hotTimeStream(3);
    }

    private void mapStream()
    {
        Flux<Integer> ints = Flux.range(1, 10)
                .map((Integer i) -> i * 10);

        ints.subscribe((Integer i) -> System.out.println(i));
    }

    private void zipWith()
    {
        Flux.range(1,5)
            .map((Integer i) -> i * 2)
            .zipWith(Flux.range(0, Integer.MAX_VALUE), (Integer left, Integer right) -> String.format("left: %d and right: %d", left, right))
            .subscribe(System.out::println);
    }

    private void baseSubscriber()
    {
        Flux<Integer> nums = Flux.range(10,3);
        nums.subscribe(new SampleSubscriber<Integer>());
    }

    private void generate()
    {
        Flux<String> flux = Flux.generate(
                () -> 0,
                (state, sink) ->
                {
                    sink.next("3 x " + state + " = " + (3 * state) );
                    if(state == 10) sink.complete();
                    return state + 1;
                }
        );
        flux.subscribe(System.out::println);
    }

    private String alphabet(int number)
    {
        if(number < 1 || number > 26)
            return null;
        else
            return String.format("valid string: %s", number);
    }

    private void handle()
    {
        Flux.just(-1, 0, 10, 20, 30)
            .handle((number, sink) -> {
                String result = alphabet(number);
                // Only call next if the result is not null
                if(result != null)
                    sink.next(result);
            })
            .subscribe(System.out::println);
    }

    private void thread()
    {
        try
        {
            Mono<String> mono = Mono.just("hello");
            System.out.println("Mono creation thread: " + Thread.currentThread().getName());

            Thread th = new Thread(() -> {
                mono.map((String msg) -> msg + " map thread ")
                    .subscribe(v -> System.out.println(v + Thread.currentThread().getName()));
            });

            th.start();
            th.join();
        }
        catch(InterruptedException ie) {}
    }

    private void switchThread()
    {
        System.out.println("\nswitching threads...");
        Scheduler scheduler = Schedulers.newParallel("parallel threads", 4);

        final Flux<String> flux = Flux.range(1,2)
            .log()
            .map(i -> i + " + " + Thread.currentThread().getName())
            .publishOn(scheduler)
            .map(i -> i + " + " + Thread.currentThread().getName());

        flux.subscribe(System.out::println);
    }

    private void hotTimeStream(int maxCount)
    {
        ConnectableFlux<Object> publish = Flux.create(fluxSink ->
        {
            int currentCount = 1;
            while(maxCount == 0 || currentCount <= maxCount )
            {
                fluxSink.next(System.currentTimeMillis());
                try
                {
                    int sleepTime = new Random().nextInt(4) + 1;
                    System.out.println("sleeping for " + sleepTime + " second(s).");
                    Thread.sleep(sleepTime * 1000);
                }
                catch(InterruptedException ie) {}
                currentCount++;
            }
            System.out.println("\nFinished Publishing " + (currentCount - 1) + " times.");
        })
        .publish(); // By calling publish we are returning a ConnectableFlux.

        // But calling subscribe will not cause it to emit, allowing us to add multiple subscriptions without missing anything at the beginning.
        publish.subscribe(System.out::println);
        // It is only when we call connect will the flux start emitting.
        publish.connect();
    }

    private void creatingFluxes()
    {
        List<String> stringList = Arrays.asList("horatio", "harper");
        Flux<String> fluxFromList = Flux.fromIterable(stringList);
        Flux<String> fluxFromStream = Flux.fromStream(stringList.stream());
        fluxFromStream.subscribe(System.out::println);
    }

    private void creatingMono()
    {
        // A method called Mono.fromCallable is observed, and this method expects that the supplier method returns a type Mono, which is non-blocking.
        Mono<String> mono = Mono.fromCallable(() -> alphabet(14));
        // elastic() is considered as the default Scheduler
        mono.log().subscribeOn(Schedulers.elastic()).subscribe(System.out::println);
    }

    private void backPressureSubscriber()
    {
        // In the above example, the subscriber is telling the producer to push every single element at once.
        // This could end up becoming overwhelming for the subscriber, consuming all of its resources.
        // Back pressure is when a downstream can tell an upstream to send it fewer data in order to prevent it from being overwhelmed.
        // We can modify our Subscriber implementation to apply back pressure.
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
