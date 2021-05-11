package com.leon.latch;

import java.util.concurrent.CountDownLatch;

public abstract class BaseServiceVerifier implements Runnable
{
    private CountDownLatch latch;
    private String serviceName;
    private boolean serviceUpFlag;

    public BaseServiceVerifier(String serviceName, CountDownLatch latch)
    {
        this.latch = latch;
        this.serviceName = serviceName;
        this.serviceUpFlag = false;
    }

    public String getServiceName()
    {
        return serviceName;
    }

    public boolean isServiceUpFlag()
    {
        return serviceUpFlag;
    }

    @Override
    public void run()
    {
        try
        {
            verifyService();
            this.serviceUpFlag = true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            this.serviceUpFlag = false;
        }
        finally
        {
            latch.countDown();
        }
    }

    @Override
    public String toString()
    {
        return "BaseServiceVerifier{" +
                "serviceName='" + serviceName + '\'' +
                ", serviceUpFlag=" + serviceUpFlag +
                '}';
    }

    public abstract void verifyService();
}
