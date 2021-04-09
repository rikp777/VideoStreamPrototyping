package com.emotion.aggregator.service;

import com.emotion.aggregator.models.Emotion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;

@Slf4j
public class AggregatorServiceTestProducer {

    @Autowired
    Environment environment;

    @Autowired
    private KafkaTemplate<String, List<Emotion>> kafkaTemplate;


    public void sendMessage(List<Emotion> cryptoCurrencies){
        ListenableFuture<SendResult<String, List<Emotion>>> future = kafkaTemplate
                .send(environment.getProperty("kafka.topic"), "someCrypto", cryptoCurrencies);

        future.addCallback(
                new ListenableFutureCallback<SendResult<String,List<Emotion>>>() {

                    @Override
                    public void onFailure(Throwable e) {
                        log.error("Something went wrong", e);
                    }

                    @Override
                    public void onSuccess(SendResult<String, List<Emotion>> result) {
                        log.info("Success");
                    }
                });

    }
}
