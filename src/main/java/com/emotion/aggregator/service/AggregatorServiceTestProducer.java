package com.emotion.aggregator.service;

import com.emotion.aggregator.models.ReceivedEmotionVoice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;
import java.util.Objects;

@Slf4j
public class AggregatorServiceTestProducer {

    @Autowired
    Environment environment;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(List<ReceivedEmotionVoice> emotions){
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate
                .send(Objects.requireNonNull(environment.getProperty("kafka.topic")), "someCrypto", "{\n" +
                        "\"type\": \"0\",\n" +
                        "\"timeStamp\": null,\n" +
                        "\"neutrality\": 0.056,\n" +
                        "\"happiness\": 0.0,\n" +
                        "\"sadness\": 0.078,\n" +
                        "\"anger\": 0.0,\n" +
                        "\"fear\": 0.0999,\n" +
                        "\"emotion\": 0\n" +
                        "}");

        future.addCallback(
                new ListenableFutureCallback<>() {

                    @Override
                    public void onFailure(Throwable e) {
                        log.error("Something went wrong", e);
                    }

                    @Override
                    public void onSuccess(SendResult<String, String> result) {
                        log.info("Success");
                    }
                });

    }
}
