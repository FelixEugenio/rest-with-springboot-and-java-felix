package pt.com.felix.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pt.com.felix.models.Greeting;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingsController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greetings")
    public Greeting greetings(
            @RequestParam(value = "name",defaultValue = "word") String name
    ) {
       return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
