package com.leon;

import com.leon.algos.BinarySearch;
import com.leon.algos.BubbleSort;
import com.leon.algos.MergeSort;
import com.leon.behaviourparam.BehaviourParamMain;
import com.leon.bitcoin.ProofOfWork;
import com.leon.blockQ.BlockingQueueExample;
import com.leon.chronicleMap.ChronicleMapper;
import com.leon.collect.CollectMain;
import com.leon.collections.CollectionsMain;
import com.leon.cyclicBarrier.CyclicBarrierExample;
import com.leon.jackson.JacksonMain;
import com.leon.latch.ApplicationStarter;
import com.leon.multithread.MultiThreadMain;
import com.leon.optional.OptionalMain;
import com.leon.reactor.ReactorMain;
import com.leon.ring.RingBufferMain;
import com.leon.streams.StreamsMain;
import com.leon.gof.GofMain;
import futs.Shop;
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
        MergeSort.main();

    }
}
