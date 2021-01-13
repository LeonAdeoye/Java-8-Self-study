package com.leon.reactor;

import reactor.core.publisher.*;
import org.reactivestreams.*;

public class SampleSubscriber<T> extends BaseSubscriber<T>
{
    public void hookOnSubscribe(Subscription subscription)
    {
        System.out.println("\nSubscribed using BaseSubscriber extended sample subscriber class.");
        request(1);
    }
    public void hookOnNext(T value)
    {
        System.out.println("hookOnNext: " + value);
        request(1);
    }
}
