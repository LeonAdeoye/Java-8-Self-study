package com.leon;

import com.leon.collections.LinkedListImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application
{
    public static void main(String[] args) throws Exception
    {
        SpringApplication.run(Application.class, args);
//        new BehaviourParamMain().main();
//        new StreamsMain().main();
//        new CollectMain().main();
//        new OptionalMain().main();
//        new CollectionsMain().main();
//        new GofMain().main();
//        new JacksonMain().main();
//        new ReactorMain().main();
//        ProofOfWork.main();
//        MultiThreadMain.main();
//        MapExample.main();
//        ApplicationStarter.verifyServices();
//        BlockingQueueExample.start();
//        CyclicBarrierExample.run();
//        Shop.main();
//        new RingBufferMain().main();
//        new ChronicleMapper().main();
//        BinarySearch.main();
//        BubbleSort.main();
//        MergeSort.main();
        LinkedListImpl.main();
    }
}
