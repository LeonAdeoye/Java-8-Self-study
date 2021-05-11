package com.leon.latch;

import java.util.concurrent.CountDownLatch;

import static java.lang.Thread.sleep;

public class WorkbenchServiceVerifier extends BaseServiceVerifier
{

    public WorkbenchServiceVerifier(String serviceName, CountDownLatch latch)
    {
        super(serviceName, latch);
    }

    public void verifyService()
    {
        try
        {
            sleep(200);
        }
        catch(InterruptedException ie)
        {
            ie.printStackTrace();
        }
        finally
        {
            System.out.println(this.getServiceName() + " is up!");
        }
    }
}
