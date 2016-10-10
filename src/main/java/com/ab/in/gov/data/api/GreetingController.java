package com.ab.in.gov.data.api;

/**
 * Created by abhil on 10-10-2016.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.apache.log4j.spi.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.jdbc.core.JdbcTemplate;

@RestController
public class GreetingController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Value("${train}")
    private String trainString;

    @CrossOrigin(origins = "*")
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @CrossOrigin(origins = "*")
    @RequestMapping("/Train")
    public List<Train> getTrains() {
        return Arrays.asList(trainString.split("\\|"))
                .stream()
                .map(train -> {
                    String[] trainFields = train.split(",");
                    return new Train(trainFields[0], trainFields[1]);
                })
                .collect(Collectors.toList());
    }
}