package futs;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop
{
    public static Double getPrice(String product)
    {
        try
        {
            System.out.println("Calculating the price of " + product);
            Thread.sleep(1000);
        }
        catch(InterruptedException ie)
        {
            ie.printStackTrace();
        }
        finally
        {
            return new Random().nextDouble();
        }
    }

    public static Future<Double> getPriceAsynch(String product)
    {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();

        new Thread(() -> {
            try
            {
                double price = getPrice(product);
                futurePrice.complete(price);
            }
            catch(Exception e)
            {
                futurePrice.completeExceptionally(e);
            }

        }).start();

        return futurePrice;
    }

    public static void main() throws Exception
    {
        System.out.println("Price returned by future: " + getPriceAsynch("book").get());

        // Using completable futures allows you to take advantage of the Java 8 stream, lambda functions compared to simple Futures.
        System.out.println("Price returned by future simplified: " + CompletableFuture.supplyAsync(() -> getPrice("stamp")).thenApplyAsync((result) -> result * 1000000).get());
    }
}
