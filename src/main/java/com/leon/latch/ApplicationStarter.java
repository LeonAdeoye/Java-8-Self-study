package com.leon.latch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ApplicationStarter
{
    private static CountDownLatch latch;
    private static List<BaseServiceVerifier> listOfVerifiers;

    public static void verifyServices()
    {
        latch = new CountDownLatch(2);
        listOfVerifiers = new ArrayList<>();
        listOfVerifiers.add(new WorkbenchServiceVerifier("query-service", latch));
        listOfVerifiers.add(new WorkbenchServiceVerifier("log-service", latch));
        Executor executor = Executors.newFixedThreadPool(listOfVerifiers.size());
        listOfVerifiers.forEach(sv -> executor.execute(sv));

        try
        {
            latch.await();
        }
        catch(InterruptedException ie)
        {
            ie.printStackTrace();
        }

        listOfVerifiers.forEach(System.out::println);
        System.out.println("Done verifying!");

    }
}
