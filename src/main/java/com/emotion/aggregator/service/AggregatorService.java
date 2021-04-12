package com.emotion.aggregator.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Slf4j
public class AggregatorService {

   @Autowired
   Environment env;

   @KafkaListener(id = "consumer", topics = {"${kafka.topic}"} )
   public void onMessage(ConsumerRecord<?, ?> record) {

      System.out.println("The list of values is : " + record.value());

      Emotion convertedEmotionData = new Emotion();
      convertedEmotionData.setHappiness(record.value().happiness());
      convertedEmotionData.setAnger(record.value().anger());
      convertedEmotionData.setNeutrality(record.value().neutrality());
      convertedEmotionData.setFear(record.value().fear());
      convertedEmotionData.setSadness(record.value().sadness());
   }
}





