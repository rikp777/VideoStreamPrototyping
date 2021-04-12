package com.emotion.aggregator.controller;

import com.emotion.aggregator.models.Emotion;
import com.emotion.aggregator.models.ReceivedEmotionVoice;
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
        List<ReceivedEmotionVoice> cryptoCurrencyList = Stream
                .of(ReceivedEmotionVoice.builder()
                        .anger((0.92d))
                        .happiness((0.01d))
                        .neutrality((0.10d))
                        .sadness((0.25d))
                        .fear((0.25d))
                        .emotion(0)
                        .type("test")
                        .build()
                )
                .collect(Collectors.toList());

        producer.sendMessage(cryptoCurrencyList);
        return environment.getProperty("message.response");
    }
}
