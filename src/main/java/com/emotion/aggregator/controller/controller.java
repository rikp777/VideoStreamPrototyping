package com.emotion.aggregator.controller;

import com.emotion.aggregator.models.Emotion;
import com.emotion.aggregator.service.AggregatorServiceTestProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/kafka")
public class controller {

    @Autowired
    Environment environment;

    @Autowired
    AggregatorServiceTestProducer producer;

    @RequestMapping(value="/json")
    public String getResult(){
        List<Emotion> cryptoCurrencyList = Stream
                .of(Emotion.builder()
                        .anger(new BigDecimal(0.92))
                        .happiness(new BigDecimal(0.01))
                        .neutrality(new BigDecimal(0.10))
                        .sadness(new BigDecimal(0.25))
                        .fear(new BigDecimal(0.25))
                        .build()
                )
                .collect(Collectors.toList());

        producer.sendMessage(cryptoCurrencyList);
        return environment.getProperty("message.response");
    }
}
