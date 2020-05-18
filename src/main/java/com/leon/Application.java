package com.leon;

import com.leon.behaviourparam.BehaviourParamMain;
import com.leon.collect.CollectMain;
import com.leon.collections.CollectionsMain;
import com.leon.optional.OptionalMain;
import com.leon.streams.StreamsMain;
import com.leon.gof.GofMain;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application
{
    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
        new BehaviourParamMain().main();
        new StreamsMain().main();
        new CollectMain().main();
        new OptionalMain().main();
        new CollectionsMain().main();
        new GofMain().main();
    }
}