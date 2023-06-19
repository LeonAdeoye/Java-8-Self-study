package com.leon;

import com.leon.amps.Publisher;
import com.leon.amps.SOWSubscriber;
import com.leon.amps.Subscriber;
import com.leon.mongo.MongoSaver;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner
{
    public static void main(String[] args) throws Exception
    {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        SOWSubscriber.main();
    }
}
